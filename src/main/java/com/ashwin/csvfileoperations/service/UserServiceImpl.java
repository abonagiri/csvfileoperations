package com.ashwin.csvfileoperations.service;

import com.ashwin.csvfileoperations.domain.User;
import com.ashwin.csvfileoperations.exception.ServiceException;
import com.ashwin.csvfileoperations.exception.ValidationException;
import com.ashwin.csvfileoperations.repository.UserRepository;
import com.ashwin.csvfileoperations.util.ValidationHelper;
import com.ashwin.csvfileoperations.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userImpl")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    ValidationHelper validationHelper;

    @Override
    public List<User> process(MultipartFile file) {
        validationHelper.validateFile(file);

        try {
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes,"UTF-8");
            String[] rows = completeData.split("[\r\n]+");
            //String[] columns = rows[0].split(","); We can print columns
            List<User> users = new ArrayList<>();
            for(int i=1;i<rows.length;i++){
                String[] row = rows[i].split(",");
                    User user = new User();
                    user.setFirstname(row[0].trim());
                    user.setLastname(row[1].trim());
                    user.setEmail(row[2].trim());
                    users.add(user);
                }

            users.stream().forEach(user -> Validator.validate(user));
            userRepository.saveAll(users);
            }catch(IOException e){
                log.error("Error in Processing Data:",e);
                throw new ServiceException("Error Processing Data");
        }
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(null!=user){
            return user.get();
        }else{
            throw new ValidationException("User not Found");
        }
    }
}
