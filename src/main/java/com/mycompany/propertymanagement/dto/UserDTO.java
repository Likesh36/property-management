package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String ownerName;
    @NotNull(message = "Owner email is mandatory")
    @NotEmpty(message = "Owner email can not be empty")
    @Size(min = 1,max = 50,message = "Owner email should be between 1 to 50 characters in length")
    private String ownerEmail;
    private String phone;
    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    private String password;
    private String houseNumber;
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
