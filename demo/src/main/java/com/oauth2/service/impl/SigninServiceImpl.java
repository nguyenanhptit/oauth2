package com.oauth2.service.impl;

import com.oauth2.common.InvalidRequestException;
import com.oauth2.controller.Response;
import com.oauth2.entity.Token;
import com.oauth2.entity.User;
import com.oauth2.repository.TokenRepository;
import com.oauth2.repository.UserRepository;
import com.oauth2.service.GoogleService;
import com.oauth2.service.SigninService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@AllArgsConstructor
public class SigninServiceImpl implements SigninService {

    private UserRepository iUserRepository;
    private GoogleService iGoogleService;
    private TokenRepository tokenRepository;
    private Environment env;

    /**
     * Sign up user to application
     */
    @Override


    public Token sigIn(String code) {
        Token token = iGoogleService.getToken(code);
        if (token != null && token.getUser() != null && token.getIdToken() != null &&
                token.getRefreshToken() != null) {
            try {
                Token tokenOfUserSignUp = tokenRepository.findTokenByUserId(token.getUser().getId());
                if (tokenOfUserSignUp != null) {
                    tokenRepository.removeTokenById(tokenOfUserSignUp.getId());
                }
                String idUser = token.getUser().getId();
                if (!isSignUp(idUser)) {
                    signUpUser(token.getUser());
                }
                String idToken = createJwtToken(idUser);
                token.setIdToken(idToken);
                token.setTime(new Date());
                tokenRepository.save(token);
                return token;
            } catch (InvalidRequestException ex) {
                throw new InvalidRequestException(ex.getMessage());
            }
        }
        return null;
    }

//    private boolean verifyToken(Token token) {
//    }

    /**
     * Sign out application
     *
     * @return
     */
    @Override
    public Response<String> signOut(String userId) {
        try {
            Token token = tokenRepository.findTokenByUserId(userId);
            if (token == null) {
                return new Response<>(HttpStatus.BAD_REQUEST.value());
            } else {
                tokenRepository.removeTokenById(token.getId());
                return new Response<>(HttpStatus.OK.value());
            }
        } catch (Exception ex) {
            return new Response<>(HttpStatus.NOT_FOUND.value(), ex.toString());
        }
    }

    /**
     * Check user sign up by check is already have information in database
     */
    @Override
    public boolean isSignUp(String idUser) {
        return (iUserRepository.existsById(idUser));
    }

    /**
     * Sign up user to application
     */
    @Override
    public void signUpUser(User user) {
        iUserRepository.save(user);
    }

    /**
     * Verify token
     */

//    private boolean verifyToken(Token token) {
//        String accessToken = token.getAccessToken();
//        if (accessToken != null) {
//            User user = token.getUser();
//            return verifyUserNTQ(user);
//        } else {
//            return false;
//        }
//    }


//    /**
//     * Verify email of ntq
//     */
//    private boolean verifyUserNTQ(User user) {
//        try {
//            String suffixEmail = user.getHd();
//            return (isNTQMail(suffixEmail));
//        } catch (Exception e) {
//            return false;
//        }
//
//    }

//    /**
//     * Verify mail ntq with mail gmail
//     */
//    private boolean isNTQMail(String userEmail) {
//        return (GoogleLink.NTQ_EMAIL_FORM.equalsIgnoreCase(userEmail));
//    }

    private String createJwtToken(String userId) {
        return  Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("JWT_EXPIRATIONTIME"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("JWT_SECRET"))
                .compact();
    }
}