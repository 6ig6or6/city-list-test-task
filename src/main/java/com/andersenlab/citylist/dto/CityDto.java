package com.andersenlab.citylist.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityDto {

    @Min(value = 1, message = "Id value should be more than 0")
    private Long id;

    @Size(min = 2, max = 50, message = "City name must be between {min} and {max} characters")
    private String name;

    @Pattern(regexp = "^(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "Invalid photo URL")
    private String photo;
}
