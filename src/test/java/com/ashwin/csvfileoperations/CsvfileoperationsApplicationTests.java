package com.ashwin.csvfileoperations;

import com.ashwin.csvfileoperations.domain.User;
import com.ashwin.csvfileoperations.repository.UserRepository;
import com.ashwin.csvfileoperations.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CsvfileoperationsApplicationTests {

	@Autowired
	UserServiceImpl userService;

	@Autowired
    UserRepository userRepository;

	@DisplayName("Unit Test for Empty Users")
	@Test
	public void getUsersEmptyTest() {
		assertEquals(0,userService.getUsers().size());
	}

    @DisplayName("Unit Test for Users")
    @Test
    public void getUsersTest() {

	    User user = new User();
        user.setEmail("x.y@z.com");
        user.setFirstname("john");
        user.setLastname("wick");
	    userRepository.save(user);
        assertEquals(1,userService.getUsers().size());
    }
}

