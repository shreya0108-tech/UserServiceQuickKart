package com.proj.userservice.services;

import com.proj.userservice.exceptions.PasswordNotMatchException;
import com.proj.userservice.exceptions.TokenNotFoundException;
import com.proj.userservice.exceptions.UserNotFoundException;
import com.proj.userservice.models.Token;
import com.proj.userservice.models.User;
import com.proj.userservice.repositories.TokenRepository;
import com.proj.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public User createUser(String name, String email, String pwd)
    {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(pwd));    //setting hashed pwd
        userRepository.save(user);

        //I want to publish this event to the message queue - kafka

        return user;
    }
    public Token login(String email, String pwd) throws UserNotFoundException, PasswordNotMatchException {
        Optional<User> optionalValue = userRepository.findByEmail(email);
        if(optionalValue.isEmpty())
        {
            throw new UserNotFoundException("User Not Found");
        }
        User user = optionalValue.get();
        if(!bCryptPasswordEncoder.matches(pwd,user.getPassword()))
        {
            throw new PasswordNotMatchException("Password doesn't match");
        }
        Token token = new Token();
        token.setUser(user);
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysLater = currentDate.plusDays(30);
        Date thirtyDaysLaterdate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        token.setExpiry_at(thirtyDaysLaterdate);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        Token token1 = tokenRepository.save(token);
        return token1;
    }

    public void logout(String token) throws TokenNotFoundException {
        Optional<Token> savedtoken = tokenRepository.findByValueAndIsDeletedEquals(token,false);
        if(savedtoken.isEmpty())
        {
            throw new TokenNotFoundException("Token Not Found");
        }
        Token tokenval = savedtoken.get();
        tokenval.setDeleted(true);
        tokenRepository.save(tokenval);
        return;
    }

    public User validateToken(String token) throws TokenNotFoundException {
        Optional<Token> token1 = tokenRepository.findByValueAndIsDeletedEqualsAndExpiryAtGreaterThan(token,
                false, new Date());
        if(!token1.isEmpty())
        {
            return token1.get().getUser();
        }
        throw new TokenNotFoundException("Token Not found");
    }
}
