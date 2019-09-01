package com.oauth2.controller;

import com.oauth2.common.InvalidRequestException;
import com.oauth2.common.until.GoogleUtils;
import com.oauth2.entity.Token;
import com.oauth2.service.SigninService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@AllArgsConstructor
@RestController
public class TokenController {
    private GoogleUtils googleUntils;
    private SigninService signinService;

    @RequestMapping(value = "/auth/google", method = RequestMethod.GET)
    public String getToken(String code) throws IOException {

        try {
            Token token = signinService.sigIn(code);
            String idToken = token.getIdToken();
            return ("redirect:http://localhost:4200/login?idToken="+ idToken );
        }catch (InvalidRequestException ex) {
            return ("redirect:http://localhost:4200/login?status="+ "mailInvalid");
        }catch (Exception ex) {
            return ("redirect:http://localhost:4200/login?status"+ "fale");
        }
    }
}
