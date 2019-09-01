package com.oauth2.repository;

import com.oauth2.entity.Token;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
public interface TokenRepository extends Repository<Token, String> {
    void save(Token token);

    Token findTokenByIdToken(String id);

    Token findTokenByUserId(String idUser);

    @Transactional
    void removeTokenById(Long id);

}
