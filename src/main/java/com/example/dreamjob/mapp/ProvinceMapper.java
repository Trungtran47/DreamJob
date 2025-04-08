package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.ProvinceDTO;
import com.example.dreamjob.entity.ProvinceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ProvinceMapper {
    ProvinceDTO toProvinceDto(ProvinceEntity provinceEntity);
    List<ProvinceDTO> toProvinceDtoList(List<ProvinceEntity> provinceEntities);
}
