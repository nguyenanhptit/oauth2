package com.oauth2.common.until;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth2.entity.User;
import lombok.AllArgsConstructor;
import org.apache.http.ParseException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Component
@AllArgsConstructor
public class GoogleUtils {

    private Environment environment;

    public String getToken(final String code) throws IOException {
        String link = environment.getProperty("google.link.get.token");
        return Request.Post(link).bodyForm(Form.form().add("code", code)
                .add("client_id", environment.getProperty("google.app.id"))
                .add("client_secret", environment.getProperty("google.app.secret"))
                .add("redirect_uri", environment.getProperty("google.redirect.uri"))
                .add("grant_type", "authorization_code").build()).execute().returnContent().asString();
    }

    public String getAccessToken(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("access_token");
        return node.textValue();
    }

    /**
     * Get access token for call API service of google
     */
    public String getRefreshToken(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("refresh_token");
        return node.textValue();
    }

    /**
     * String getId Access Token form response
     */

    public String getIdToken(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("id_token");
        return node.textValue();
    }

    public User getUserInfo(final String accessToken) throws IOException, ParseException {
        User user = new User();
        String link = environment.getProperty("google.link.get.user_info") + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String id = mapper.readTree(response).get("id").textValue();
            user.setId(id);
        } catch (NullPointerException ex) {
            user.setId(null);
        }
        try {
            user.setEmail(mapper.readTree(response).get("email").textValue());
        } catch (NullPointerException ex) {
            user.setEmail(null);
        }
        try {
            user.setVerifiedEmail(mapper.readTree(response).get("verified_email").asBoolean());
        } catch (NullPointerException ex) {
            user.setVerifiedEmail(false);
        }
        try {
            user.setName(mapper.readTree(response).get("name").textValue());
        } catch (NullPointerException ex) {
            user.setName("");
        }
//        try {
//            user.setGivenName(mapper.readTree(response).get("given_name").textValue());
//        } catch (NullPointerException ex) {
//            user.setGivenName("");
//        }
//        try {
//            user.setFamilyName(mapper.readTree(response).get("family_name").textValue());
//        } catch (NullPointerException ex) {
//            user.setFamilyName("");
//        }
//        try {
//            user.setLink(mapper.readTree(response).get("link").textValue());
//        } catch (NullPointerException ex) {
//            user.setLink("");
//        }
        try {
            user.setPicture(mapper.readTree(response).get("picture").textValue());
        } catch (NullPointerException ex) {
            user.setPicture("");
        }
        try {
            user.setSkype(mapper.readTree(response).get("skype").textValue());
        } catch (NullPointerException ex) {
            user.setSkype("");
        }
        try {
            user.setHd(mapper.readTree(response).get("hd").textValue());
        } catch (NullPointerException ex) {
            user.setHd("");
        }
//        try {
//            user.setLocale(mapper.readTree(response).get("locale").textValue());
//        } catch (NullPointerException ex) {
//            user.setLocale("");
//        }
//        try {
//            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(mapper.readTree(response).get("date").textValue());
//            user.setDateOfBirth(date);
//        } catch (NullPointerException ex) {
//
//        }
        return user;
    }

}
