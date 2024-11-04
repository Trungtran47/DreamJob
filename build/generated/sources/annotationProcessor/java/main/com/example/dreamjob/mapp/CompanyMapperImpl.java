package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.CompanyDTO;
import com.example.dreamjob.entity.CompanyEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-05T01:54:04+0700",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDTO toCompanyDto(CompanyEntity companyEntity) {
        if ( companyEntity == null ) {
            return null;
        }

        CompanyDTO.CompanyDTOBuilder companyDTO = CompanyDTO.builder();

        return companyDTO.build();
    }
}
