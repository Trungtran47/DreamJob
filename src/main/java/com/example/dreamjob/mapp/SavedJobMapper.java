package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.request.SavedJobRequest;

import com.example.dreamjob.dto.response.SavedJobResponse;
import com.example.dreamjob.entity.SavedJobEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring",nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SavedJobMapper {
    SavedJobRequest toSavedJobRequest(SavedJobEntity savedJobEntity);
    SavedJobResponse toSavedJobResponse(SavedJobEntity savedJobEntity);
}
