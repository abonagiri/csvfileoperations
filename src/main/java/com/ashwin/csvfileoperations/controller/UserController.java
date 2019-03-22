package com.ashwin.csvfileoperations.controller;

import com.ashwin.csvfileoperations.domain.User;
import com.ashwin.csvfileoperations.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/csv")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userImpl")
    UserService csvService;
    
    /*
     * When user clicks on upload button handleFileUpload method is called. 
     */

    @PostMapping(value = "/upload")
    public @ResponseBody List<User> handleFileUpload(@RequestParam(value="file") MultipartFile file) {
        return csvService.process(file);
    }

    /*@GetMapping(value = "/get/{id}")
    public @ResponseBody User getUser(@PathVariable("id") Long userID) {
        return csvService.getUserById(userID);
    }*/
    
    /*
     * Returns list of users from h2 database while loading the page.
     */

    @GetMapping(value = "/get")
    public @ResponseBody List<User> getUsers() {
        return csvService.getUsers();
    }

}
