package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.BlogDTO;
import com.example.dreamjob.entity.BlogEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface BlogMapper {
    @Mapping(source = "user.userId", target = "user")
    BlogDTO toBlogDto(BlogEntity blogEntity);
    @Mapping(source = "user.userId", target = "user")
    List<BlogDTO> toBlogDtos(List<BlogEntity> blogEntities);

}
