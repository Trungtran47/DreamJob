package com.example.dreamjob.serviceIplm;

import com.example.dreamjob.dto.ProvinceDTO;
import com.example.dreamjob.entity.ProvinceEntity;
import com.example.dreamjob.mapp.ProvinceMapper;
import com.example.dreamjob.repository.ProvinceRepository;
import com.example.dreamjob.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceIplm  implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public List<ProvinceDTO> getAllProvinces() {
        try {
            List<ProvinceEntity> provinceDTO = provinceRepository.findAll();
            return provinceMapper.toProvinceDtoList(provinceDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
