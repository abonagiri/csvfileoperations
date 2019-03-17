package com.ashwin.csvfileoperations.util;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.groups.Default;
import java.util.Set;
import java.util.TreeSet;

public final class Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

    private Validator() throws IllegalAccessException {
        throw new IllegalAccessException("Util class not not be instantiated");
    }

    private static final ResourceBundleLocator RESOURCE_BUNDLE_LOCATOR = new PlatformResourceBundleLocator(
            "ValidationMessage"
    );

    private static final javax.validation.Validator VALIDATOR = Validation
            .byDefaultProvider()
            .configure().messageInterpolator(new ResourceBundleMessageInterpolator(RESOURCE_BUNDLE_LOCATOR))
            .buildValidatorFactory().getValidator();


    public static <T> void validate(final T classType) {

        final Set<String> errorMessages = new TreeSet<>();
        final Set<ConstraintViolation<T>> constraintValidators =
                VALIDATOR.validate(classType, Default.class);

        if(!constraintValidators.isEmpty()){
            for(final ConstraintViolation<T> constraintViolation: constraintValidators){
                errorMessages.add(constraintViolation.getMessage());
            }
            throw new ValidationException(errorMessages.toString());
        }
    }

}
