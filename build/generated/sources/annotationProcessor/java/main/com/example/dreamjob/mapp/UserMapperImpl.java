package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.UserDTO;
import com.example.dreamjob.dto.response.UserCv;
import com.example.dreamjob.dto.response.UserLogin;
import com.example.dreamjob.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO UserToUserDTO(UserEntity user) {

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        if ( user != null ) {
            userDTO.userId( user.getUserId() );
            userDTO.username( user.getUsername() );
            userDTO.password( user.getPassword() );
            userDTO.email( user.getEmail() );
            userDTO.fullName( user.getFullName() );
            userDTO.phone( user.getPhone() );
            userDTO.cv( user.getCv() );
            userDTO.experience( user.getExperience() );
            userDTO.location( user.getLocation() );
            userDTO.desiredJob( user.getDesiredJob() );
            userDTO.roles( user.getRoles() );
            userDTO.avatar( user.getAvatar() );
        }

        return userDTO.build();
    }

    @Override
    public UserLogin UserEntityToUserLogin(UserEntity user) {

        UserLogin.UserLoginBuilder userLogin = UserLogin.builder();

        if ( user != null ) {
            userLogin.userId( user.getUserId() );
            userLogin.username( user.getUsername() );
            userLogin.email( user.getEmail() );
            userLogin.fullName( user.getFullName() );
            userLogin.phone( user.getPhone() );
            userLogin.cv( user.getCv() );
            userLogin.experience( user.getExperience() );
            userLogin.location( user.getLocation() );
            userLogin.desiredJob( user.getDesiredJob() );
            userLogin.roles( user.getRoles() );
            userLogin.avatar( user.getAvatar() );
        }

        return userLogin.build();
    }

    @Override
    public UserCv UserEntityToUserCv(UserEntity user) {

        UserCv.UserCvBuilder userCv = UserCv.builder();

        if ( user != null ) {
            if ( user.getUserId() != null ) {
                userCv.userId( user.getUserId().intValue() );
            }
            userCv.cv( user.getCv() );
        }

        return userCv.build();
    }

    @Override
    public List<UserDTO> UsersToUserDTOs(List<UserEntity> users) {
        if ( users == null ) {
            return new ArrayList<UserDTO>();
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( UserEntity userEntity : users ) {
            list.add( UserToUserDTO( userEntity ) );
        }

        return list;
    }
}
