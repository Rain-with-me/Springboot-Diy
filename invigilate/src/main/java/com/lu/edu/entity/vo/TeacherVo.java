package com.lu.edu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: 雨同我
 * @Description:
 * @DateTime: 2022/10/13 15:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVo implements Serializable {
    private String username;
    private String seat;
}
