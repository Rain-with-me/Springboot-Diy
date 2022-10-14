package com.lu.edu.entity.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: 雨同我
 * @Description:
 * @DateTime: 2022/10/13 15:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto implements Serializable {
    private Integer code;
    private String username;
}
