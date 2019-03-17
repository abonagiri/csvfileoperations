package com.ashwin.csvfileoperations.service;

//Here we are using Strategy Pattern, Basically we autowire
// interface and Spring will resolve the implementation at run time based on Component name

import com.ashwin.csvfileoperations.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public List<User> process(MultipartFile multipartFile);

    public User getUserById(Integer id);
}
