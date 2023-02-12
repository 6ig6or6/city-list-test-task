package com.andersenlab.citylist.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CityDto {
    private Long id;
    @Size(min = 2, max = 50)
    private String name;
    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    private String photo;
}
