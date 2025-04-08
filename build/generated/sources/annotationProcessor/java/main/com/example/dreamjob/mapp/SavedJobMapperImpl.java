package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.request.SavedJobRequest;
import com.example.dreamjob.dto.response.SavedJobResponse;
import com.example.dreamjob.entity.SavedJobEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SavedJobMapperImpl implements SavedJobMapper {

    @Override
    public SavedJobRequest toSavedJobRequest(SavedJobEntity savedJobEntity) {

        SavedJobRequest.SavedJobRequestBuilder savedJobRequest = SavedJobRequest.builder();

        if ( savedJobEntity != null ) {
            savedJobRequest.id( savedJobEntity.getId() );
            savedJobRequest.userId( savedJobEntity.getUserId() );
        }

        return savedJobRequest.build();
    }

    @Override
    public SavedJobResponse toSavedJobResponse(SavedJobEntity savedJobEntity) {

        SavedJobResponse.SavedJobResponseBuilder savedJobResponse = SavedJobResponse.builder();

        if ( savedJobEntity != null ) {
            savedJobResponse.id( savedJobEntity.getId() );
            savedJobResponse.userId( savedJobEntity.getUserId() );
        }

        return savedJobResponse.build();
    }
}
