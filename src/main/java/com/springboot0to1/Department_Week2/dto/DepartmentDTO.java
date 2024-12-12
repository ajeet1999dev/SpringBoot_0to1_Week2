package com.springboot0to1.Department_Week2.dto;

import com.springboot0to1.Department_Week2.customAnotation.PasswordValidation;
import com.springboot0to1.Department_Week2.customAnotation.PrimeNumberValidation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {


    private Long id;

    @NotEmpty(message = "title cannot be Empty")
    @NonNull
    private String title;

    @AssertTrue(message = "isActive should be true")
    private Boolean isActive;

    @PastOrPresent(message = "Created date cannot be future")
    private LocalDate createdAt;

    @PrimeNumberValidation(message = "titleNumber should be in prime number format")
    private Integer tileNumber;

    @PasswordValidation(message = "password should conatin atleast one uppercase,lowercase,special character and passowrkd contain atleast 10 digits")
    private String titlePassword;
}
