package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.UserDTO;
import com.example.dreamjob.dto.response.UserLogin;
import com.example.dreamjob.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class); // Đổi từ PostMapper sang UserMapper
    UserDTO UserToUserDTO(UserEntity user);
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "cv", target = "cv")
    @Mapping(source = "experience", target = "experience")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "desiredJob", target = "desiredJob")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "avatar", target = "avatar")
    UserLogin UserEntityToUserLogin(UserEntity user);
    List<UserDTO> UsersToUserDTOs(List<UserEntity> users);
}
