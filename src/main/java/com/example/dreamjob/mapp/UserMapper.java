package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.UserDTO;
import com.example.dreamjob.dto.response.UserCv;
import com.example.dreamjob.dto.response.UserLogin;
import com.example.dreamjob.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper {
    UserDTO UserToUserDTO(UserEntity user);
    UserLogin UserEntityToUserLogin(UserEntity user);
    UserCv UserEntityToUserCv(UserEntity user);
    List<UserDTO> UsersToUserDTOs(List<UserEntity> users);
}
