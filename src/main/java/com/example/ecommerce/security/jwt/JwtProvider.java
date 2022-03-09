package com.example.ecommerce.security.jwt;

import com.example.ecommerce.security.UserDetailsImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    public static final Logger log=LoggerFactory.getLogger(JwtProvider.class);
    @Value("${secretJwtSign}")
    private String jwtSecret;
    @Value("${jwtExpiration}")
    private int jwtExpiration;



    public String generateToken(Authentication authentication){
        UserDetailsImpl userPrincipals= (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipals.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String jwt){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }

    public boolean validateToken(String jwt){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        }
        catch (JwtException e){
            log.error("Jwt error "+e.getMessage());
        }
        catch (IllegalArgumentException e){
            log.error("Empty or incorrect argument "+e.getMessage());
        }
        return false;
    }




    }
