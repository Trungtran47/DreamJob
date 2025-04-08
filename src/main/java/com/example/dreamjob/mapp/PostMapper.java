package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.PostDTO;
import com.example.dreamjob.dto.response.PostWithCompanyDTO;
import com.example.dreamjob.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CompanyMapper.class},nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PostMapper {
//    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    PostDTO toDTO(PostEntity postEntity);
    PostWithCompanyDTO toWithCompanyDTO(PostEntity postEntity);
    List<PostWithCompanyDTO> toWithCompanyDTOs(List<PostEntity> postEntities);

}
