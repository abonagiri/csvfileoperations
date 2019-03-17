package com.ashwin.csvfileoperations.service;

import com.ashwin.csvfileoperations.domain.User;
import com.ashwin.csvfileoperations.repository.UserRepository;
import com.ashwin.csvfileoperations.util.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("userImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ValidationHelper validationHelper;

    @Override
    public List<User> process(MultipartFile file) {
        validationHelper.validateFile(file);

        try {
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] rows = completeData.split("#");
            String[] columns = rows[0].split(",");

            for(int i=1;i<rows.length;i++){
                String[] row = rows[i].split(",");
                validationHelper.validateRow(row);
                    User user = new User();
                    user.setFirstname(row[0].trim());
                    user.setFirstname(row[1].trim());
                    user.setFirstname(row[2].trim());
                    userRepository.save(user);
                }
            }catch(IOException e){

        }
        return new ArrayList<>();
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }
}
