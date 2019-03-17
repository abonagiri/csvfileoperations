package com.ashwin.csvfileoperations.util;

import com.ashwin.csvfileoperations.exception.ValidationException;
import org.springframework.web.multipart.MultipartFile;

public class ValidationHelper {

    public void validateFile(MultipartFile file) throws RuntimeException {

        if(null!=file){
            String fileName = file.getOriginalFilename().toUpperCase();
            boolean extension = !fileName.endsWith(".CSV");
            if (extension) {
                throw new ValidationException("Improper file format");
            }
        }
    }

    public void validateRow(String[] rowData){

    }
}
