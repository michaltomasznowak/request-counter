package com.empik.requestcounter.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * michal.nowak created on 21/01/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserDto {
    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private OffsetDateTime createdAt;
    private double calculations;
}
