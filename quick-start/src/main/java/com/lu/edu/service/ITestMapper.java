package com.lu.edu.service;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/** 
 * @Description: mapstruct的示例
 * @Author: 雨同我
 * @DateTime: 2023/12/18 16:47
 * @param: null: 
 * @return: 
*/


@Mapper
public interface ITestMapper {
    ITestMapper INSTANCT = Mappers.getMapper(ITestMapper.class);
//    UserEntity parseEntity(UserVo userVo);
//    UserVo parseUserVo(UserEntity userEntity);
//
//
//    @Mapping(target = "userNick", source = "username")
//    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd")
//    @Mapping(target = "age", source = "age", numberFormat = "#0.00")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "userVerified", defaultValue = "成功")
//    UserEntity diyParseEntity(UserDto userDto);
    
}
