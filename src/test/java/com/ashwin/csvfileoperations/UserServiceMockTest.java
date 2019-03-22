package com.ashwin.csvfileoperations;

import com.ashwin.csvfileoperations.domain.User;
import com.ashwin.csvfileoperations.repository.UserRepository;
import com.ashwin.csvfileoperations.service.UserService;
import com.ashwin.csvfileoperations.service.UserServiceImpl;
import com.ashwin.csvfileoperations.util.ValidationHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;

@SpringBootTest
public class UserServiceMockTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private ValidationHelper validationHelper = new ValidationHelper();


    @BeforeEach
    void setMockOutput() {
       final User user = new User();
        user.setEmail("x.y@z.com");
        user.setFirstname("john");
        user.setLastname("wick");
        Mockito.when(userService.getUsers()).thenReturn(new ArrayList<User>(){
            {
                add(user);
        }});

        MockMultipartFile file = new MockMultipartFile("file", "Book3.csv", null,
                "FirstName,LastName,Email\nJohn,travolta,john.t@yahoo.com".getBytes());

        Mockito.when(userService.process(file)).thenReturn(new ArrayList<User>(){
            {
                add(user);
            }});
    }


    @DisplayName(" Mock userService getUsers()")
    @Test
    void testGet() {
        Assertions.assertEquals(1, userService.getUsers().size());
    }

    @DisplayName(" Mock userService process()")
    @Test
    void testProcess(){
        MockMultipartFile file = new MockMultipartFile("file", "Book3.csv", null, "testcsv".getBytes());
        Assertions.assertEquals(1, userService.process(file).size());
    }
}

