package com.netcracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Integer id;
    private String title;
    private String firstName;
    private String lastName;
    private Date modifiedWhen;
    private Integer typeId;
    private String typeCaption;

}
