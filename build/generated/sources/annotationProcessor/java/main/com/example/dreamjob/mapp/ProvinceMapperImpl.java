package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.ProvinceDTO;
import com.example.dreamjob.entity.ProvinceEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ProvinceMapperImpl implements ProvinceMapper {

    @Override
    public ProvinceDTO toProvinceDto(ProvinceEntity provinceEntity) {

        ProvinceDTO.ProvinceDTOBuilder provinceDTO = ProvinceDTO.builder();

        if ( provinceEntity != null ) {
            provinceDTO.provinceName( provinceEntity.getProvinceName() );
        }

        return provinceDTO.build();
    }

    @Override
    public List<ProvinceDTO> toProvinceDtoList(List<ProvinceEntity> provinceEntities) {
        if ( provinceEntities == null ) {
            return new ArrayList<ProvinceDTO>();
        }

        List<ProvinceDTO> list = new ArrayList<ProvinceDTO>( provinceEntities.size() );
        for ( ProvinceEntity provinceEntity : provinceEntities ) {
            list.add( toProvinceDto( provinceEntity ) );
        }

        return list;
    }
}
