package com.example.ecommerce.service;

import com.example.ecommerce.dto.RefreshTokenRequest;
import com.example.ecommerce.dto.TokenRefreshResponse;
import com.example.ecommerce.entity.RefreshToken;
import com.example.ecommerce.exception.TokenExpiredException;
import com.example.ecommerce.exception.TokenNotFoundException;
import com.example.ecommerce.repository.RefreshTokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private JwtProvider jwtProvider;

    public RefreshToken createRefreshToken(String email){
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setUser(userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("email not found")));
        refreshToken.setExpiredDate(LocalDateTime.now().plusHours(8));
        refreshToken.setToken(generateRefreshToken());
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
    public String generateRefreshToken(){
        return UUID.randomUUID().toString();
    }
    public TokenRefreshResponse checkExpiration(String token) throws TokenExpiredException {
        RefreshToken refreshToken=refreshTokenRepository.findByToken(token).orElseThrow(()->new TokenNotFoundException("token not found"));
        if(refreshToken.getExpiredDate().isBefore(LocalDateTime.now())){
            throw new TokenExpiredException("refresh token expired , please sign in");
        }
        String newRefreshToken=generateRefreshToken();
        refreshToken.setToken(newRefreshToken);
        refreshToken.setExpiredDate(LocalDateTime.now().plusHours(8));
        refreshTokenRepository.save(refreshToken);
        String newJwtToken=jwtProvider.generateToken(refreshToken.getUser().getEmail());
        return new TokenRefreshResponse(newJwtToken,newRefreshToken);
    }




}
