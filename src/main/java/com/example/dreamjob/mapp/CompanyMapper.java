package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.CompanyDTO;
import com.example.dreamjob.entity.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
//,nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
@Mapper(componentModel = "spring",nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CompanyMapper {
//    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
    @Mapping(source = "user.userId", target = "user")
    CompanyDTO toCompanyDto(CompanyEntity companyEntity);
    @Mapping(source = "user.userId", target = "user")
    List<CompanyDTO> toCompanyDtoList(List<CompanyEntity> companyEntities);
}
