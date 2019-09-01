package com.oauth2.service;

import com.oauth2.entity.Token;
import com.oauth2.entity.User;

public interface GoogleService {
    Token getToken(String code);

    String getAccessTokenFormGoogle(String response);

    String getRefreshTokenFormGoogle(String response);

    User getUserFormGoogle(String accessToken);

    String getTokenFormGoogle(String code);

    String getIdTokenFromGoogle(String response);
}
