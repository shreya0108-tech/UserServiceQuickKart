package com.proj.userservice.repositories;

import com.proj.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    public Token save(Token token);
    public Optional<Token> findByValueAndIsDeletedEquals(String value,boolean deleted);
    public Optional<Token> findByValueAndIsDeletedEqualsAndExpiryAtGreaterThan(String value
            ,boolean expiryDate, Date expiryGreaterThanThisValue);
}
