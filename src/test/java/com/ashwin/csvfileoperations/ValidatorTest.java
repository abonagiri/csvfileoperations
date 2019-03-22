package com.ashwin.csvfileoperations;

import com.ashwin.csvfileoperations.domain.User;
import com.ashwin.csvfileoperations.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidatorTest {

    @Test
    public void validateUserEmail(){

        User user = new User();
        user.setEmail("x.y@.com");
        user.setFirstname("john");
        user.setLastname("wick");

        ValidationException thrown =
                Assertions.assertThrows(ValidationException.class,
                        () -> Validator.validate(user),
                        "Expected validate() to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Please provide a valid email address"));
    }

    @Test
    public void validateUserFirstNamel(){

        User user = new User();
        user.setEmail("x.y@z.com");
        user.setFirstname("");
        user.setLastname("wick");

        ValidationException thrown =
                Assertions.assertThrows(ValidationException.class,
                        () -> Validator.validate(user),
                        "Expected validate() to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("FirstName is mandatory"));
    }

    @Test
    public void validateUserLastName(){

        User user = new User();
        user.setEmail("x.y@z.com");
        user.setFirstname("john");
        user.setLastname("");

        ValidationException thrown =
                Assertions.assertThrows(ValidationException.class,
                        () -> Validator.validate(user),
                        "Expected validate() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("LastName is mandatory"));
    }
}
