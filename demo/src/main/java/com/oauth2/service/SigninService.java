package com.oauth2.service;

import com.oauth2.controller.Response;
import com.oauth2.entity.Token;
import com.oauth2.entity.User;

public interface SigninService {

    /**
     * Sign out application
     */
    Response<String> signOut(String userId);

    /**
     * Sign up service
     */

    Token sigIn(String code);

    /**
     * Check user sign up in application
     */
    boolean isSignUp(String idUser);

    /**
     * Sign up user in database
     */
    void signUpUser(User user);


}
