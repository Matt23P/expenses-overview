package com.example.eoapi.Service;

import com.example.eoapi.DTO.UserDTO;
import com.example.eoapi.Entity.User;
import com.example.eoapi.Repository.UserRepository;
import com.example.eoapi.Request.CreateUserRequest;
import com.example.eoapi.Request.LoginRequest;
import com.example.eoapi.Response.LoginResponse;
import com.example.eoapi.Response.StatusMessage;
import com.example.eoapi.Utils.Currencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String PWD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,32}$";
    private static final String EMAIL_REGEX = "^(([^<>()[\\\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    private static final String USERNAME_REGEX = "^[a-zA-Z0-9](?:[a-zA-Z0-9 ]*[a-zA-Z0-9])?$";

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public StatusMessage signUpUser(CreateUserRequest request) {
        if (!userRepository.existsUserByEmail(request.getEmail())) {
            if(!userRepository.existsUserByUsername(request.getUsername())) {
                String pwd = request.getPassword();
                String email = request.getEmail();
                String username = request.getUsername();
                String userCurrency = request.getUserCurrency();

                List<String> error = validateUserData(pwd, email, username, userCurrency);
                if (!error.isEmpty()) {
                    return new StatusMessage(false, error);
                }

                User user = new User(email, this.passwordEncoder.encode(pwd), username, LocalDate.now(), userCurrency);

                try {
                    userRepository.save(user);
                } catch (JpaSystemException exception) {
                    logger.log(Level.WARNING, "Couldn't create new user: " + exception.getMessage());
                    return new StatusMessage(false, Collections.singletonList("Error while creating user - please try again later."));
                }
                return new StatusMessage(true, Collections.singletonList(""));
            } else {
                logger.log(Level.INFO, "User with username {" + request.getUsername() + "} already exists.");
                return new StatusMessage(false, Collections.singletonList("User with given username already exists."));
            }
        } else {
            logger.log(Level.INFO, "User with email {" + request.getEmail() + "} already exists.");
            return new StatusMessage(false, Collections.singletonList("User with given email already exists."));
        }
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        String userEmail = request.getEmail();
        if (userRepository.existsUserByEmail(userEmail)) {
            try {
                User user = userRepository.getUserByEmail(userEmail);
                if(this.passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    UserDTO userDTO = new UserDTO(
                            user.getUserId(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getCreationDate(),
                            user.getUserCurrency()
                    );
                    return new LoginResponse(userDTO, true, "");
                } else {
                    return new LoginResponse(null, false, "Wrong email or password.");
                }
            } catch (JpaSystemException jpaEx) {
                logger.log(Level.WARNING, "Could not get user by email {" + userEmail + "}: " + jpaEx.getMessage());
                return new LoginResponse(null, false, "Error while login process - please try again later.");
            }
        } else {
            return new LoginResponse(null, false, "Wrong email or password.");
        }
    }

    private List<String> validateUserData(String pwd, String email, String username, String currency) {
        List<String> errors = new ArrayList<>();
        Pattern patternUsername = Pattern.compile(USERNAME_REGEX);
        Pattern patternEmail = Pattern.compile(EMAIL_REGEX);
        Pattern patternPwd = Pattern.compile(PWD_REGEX);
        Matcher matcher = patternEmail.matcher(email);
        if (!matcher.matches()) {
            errors.add("Email does not match example@email.com.");
        }

        matcher = patternUsername.matcher(username);
        if (!matcher.matches()) {
            errors.add("Username must not be longer than 32 chars, cannot start or end with" +
                    " space and cannot contain any special chars.");
        }

        matcher = patternPwd.matcher(pwd);
        if (!matcher.matches()) {
            errors.add("Password must be 8-32 chars long, must contain at least one small letter, capital letter, number and special char.");
        }

        boolean occured = false;
        for (Currencies currencies1 : Currencies.values()) {
            if (currencies1.name().equals(currency)) {
                occured = true;
                break;
            }
        }

        if(!occured) {
            errors.add("Sorry we do not handle this currency yet.");
        }

        logger.log(Level.INFO, "Errors encountered during user data validation: " + errors);
        return errors;
    }
}
