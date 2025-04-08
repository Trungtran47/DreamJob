package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.CompanyDTO;
import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDTO toCompanyDto(CompanyEntity companyEntity) {

        CompanyDTO.CompanyDTOBuilder companyDTO = CompanyDTO.builder();

        if ( companyEntity != null ) {
            companyDTO.user( companyEntityUserUserId( companyEntity ) );
            companyDTO.companyId( companyEntity.getCompanyId() );
            companyDTO.companyName( companyEntity.getCompanyName() );
            companyDTO.companyIntroduce( companyEntity.getCompanyIntroduce() );
            companyDTO.companyLocation( companyEntity.getCompanyLocation() );
            companyDTO.companyWebsite( companyEntity.getCompanyWebsite() );
            companyDTO.companySize( companyEntity.getCompanySize() );
            companyDTO.companyCategory( companyEntity.getCompanyCategory() );
            companyDTO.companyLogo( companyEntity.getCompanyLogo() );
        }

        return companyDTO.build();
    }

    @Override
    public List<CompanyDTO> toCompanyDtoList(List<CompanyEntity> companyEntities) {
        if ( companyEntities == null ) {
            return new ArrayList<CompanyDTO>();
        }

        List<CompanyDTO> list = new ArrayList<CompanyDTO>( companyEntities.size() );
        for ( CompanyEntity companyEntity : companyEntities ) {
            list.add( toCompanyDto( companyEntity ) );
        }

        return list;
    }

    private Long companyEntityUserUserId(CompanyEntity companyEntity) {
        if ( companyEntity == null ) {
            return null;
        }
        UserEntity user = companyEntity.getUser();
        if ( user == null ) {
            return null;
        }
        Long userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
