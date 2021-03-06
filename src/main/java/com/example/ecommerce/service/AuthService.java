package com.example.ecommerce.service;


import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.LoginResponse;
import com.example.ecommerce.dto.RegistrationRequest;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.entity.enums.ERole;
import com.example.ecommerce.exception.EmailAlreadyExistException;
import com.example.ecommerce.exception.EmailAndNicknameAlreadyExistException;
import com.example.ecommerce.exception.NicknameAlreadyExistException;
import com.example.ecommerce.exception.UserNotFound;
import com.example.ecommerce.repository.RefreshTokenRepository;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.UserDetailsImpl;
import com.example.ecommerce.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    public static final int  MAX_FAILED_ATTEMPTS=3;
    public static final long LOCK_TIME_DURATION_MIN=2;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    MailService mailService;
    @Autowired
    EmailVerificationTokenService emailVerificationTokenService;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    public User registration(RegistrationRequest req) throws EmailAlreadyExistException, NicknameAlreadyExistException, EmailAndNicknameAlreadyExistException {
        if(userRepository.existsByEmail(req.getEmail())&&!userRepository.existsByNickname(req.getNickname())){
            throw new EmailAlreadyExistException("email already exist");
        }
         if(userRepository.existsByNickname(req.getNickname())&&!userRepository.existsByEmail(req.getEmail())){
            throw new NicknameAlreadyExistException("nickname already exist");
        }
        if(userRepository.existsByNickname(req.getNickname())&&userRepository.existsByEmail(req.getEmail())){
            throw new EmailAndNicknameAlreadyExistException("email and nickname already exist");
        }
        User user = new User(req.getNickname(), req.getEmail(), passwordEncoder.encode(req.getPassword()));
        Set<String> strRoles = req.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByRole(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role == "admin") {
                    Role adminRole = roleRepository.findByRole(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByRole(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        user.setDate(new Date());
        userRepository.save(user);
        return user;


    }


    public LoginResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user=getUser(loginRequest.getEmail());
        if(user.getFailedAttempt()>0){
            resetFailedAttempts(user);
        }
        UserDetailsImpl userPrincipals= (UserDetailsImpl) authentication.getPrincipal();
        String token=jwtProvider.generateToken(userPrincipals.getEmail());
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(userDetails.getEmail());
        return new LoginResponse(token,refreshToken.getToken(),userDetails.getId(),userDetails.getEmail(),roles);
    }

    public User getUser(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new UserNotFound("user not found"));
    }

    @Transactional
    public void logout(Long userId){
        refreshTokenRepository.deleteByUserId(userId);
    }

    public void restorePassword(String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("email invalid"));
        EmailVerificationToken token =emailVerificationTokenService.saveToken(user,emailVerificationTokenService.createToken());
        mailService.send(email,"Please follow to http://localhost:8080/auth/confirmToken?token="+token.getToken()+" to change the password","Password restore");
    }


    public void increaseFailedAttempt(User user){
        int newFailedAttempts=user.getFailedAttempt()+1;
        user.setFailedAttempt(newFailedAttempts);
        userRepository.save(user);
    }
    public void resetFailedAttempts(User user){
        user.setFailedAttempt(0);
        userRepository.save(user);
    }
    public void lock(User user){
        user.setAccountNonLocked(false);
        user.setLockTime(LocalDateTime.now().plusMinutes(LOCK_TIME_DURATION_MIN));
        userRepository.save(user);
    }
    public boolean unlockWhenTimeExpired(User user){
        if(user.getLockTime().isBefore(LocalDateTime.now())){
            user.setAccountNonLocked(true);
            user.setFailedAttempt(0);
            user.setLockTime(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }












}
