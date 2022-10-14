package com.lu.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lu.edu.entity.ScySecurity;
import com.lu.edu.entity.ScyTeacher;
import com.lu.edu.entity.dto.TeacherDto;
import com.lu.edu.entity.vo.TeacherVo;
import com.lu.edu.mapper.ScySecurityMapper;
import com.lu.edu.mapper.ScyTeacherMapper;
import com.lu.edu.utils.result.CommonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mybatis自动生成
 * @since 2022-10-13
 */
@RestController
@RequestMapping("/edu")
public class ScyTeacherController {

    @Autowired
    private ScySecurityMapper scySecurityMapper;
    @Autowired
    private ScyTeacherMapper scyTeacherMapper;

    /** 
     * @Description: 获取老师和其他工作人员
     * @Author: 雨同我
     * @DateTime: 2022/10/13 15:43
    */
    @PostMapping("/teacher")
    public CommonResult getTeacher(@RequestBody TeacherDto teacherDto){
        if (teacherDto.getCode()==1){
            ScyTeacher scyTeacher = scyTeacherMapper.selectOne(new LambdaQueryWrapper<ScyTeacher>()
                    .eq(ScyTeacher::getUsername, teacherDto.getUsername()));
            TeacherVo teacherVo = new TeacherVo();
            BeanUtils.copyProperties(scyTeacher,teacherVo);
            return CommonResult.success(teacherVo);
        }else {
            ScySecurity scySecurity = scySecurityMapper.selectOne(new LambdaQueryWrapper<ScySecurity>()
                    .eq(ScySecurity::getUsername, teacherDto.getUsername()));
            TeacherVo teacherVo = new TeacherVo();
            BeanUtils.copyProperties(scySecurity,teacherVo);
            return CommonResult.success(teacherVo);
        }
    }
}

