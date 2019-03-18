package com.ashwin.csvfileoperations.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Email(message="{user.email.notempty}") // Hibernate Email format which can allow intranet email address which dont have domains
@Pattern(regexp=".+@.+\\..+", message="{user.email.notempty}") // Pattern to check valid email string format with domain
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE }) // Annotation on method,field,annotation level
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@ReportAsSingleViolation // This is to return only one message
public @interface CustomEmailValidator {
    String message() default "Please provide a valid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
