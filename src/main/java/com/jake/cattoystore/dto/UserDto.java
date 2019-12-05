package com.jake.cattoystore.dto;

import com.github.dozermapper.core.Mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    @Mapping("id")
    private Long id;

    @Mapping("name")
    private String name;

    @Mapping("email")
    private String email;

    @Mapping("password")
    private String password;
}
