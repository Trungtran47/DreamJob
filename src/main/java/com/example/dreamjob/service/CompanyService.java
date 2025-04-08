package com.example.dreamjob.service;

import com.example.dreamjob.dto.CompanyDTO;
import com.example.dreamjob.entity.CompanyEntity;
import org.springframework.web.multipart.MultipartFile;

import java.lang.invoke.MutableCallSite;
import java.util.List;

public interface CompanyService {
    CompanyEntity addCompany(Long id,CompanyEntity companyEntity,MultipartFile image);
    CompanyEntity updateCompany(Long id,Long userId, CompanyEntity companyEntity,MultipartFile image);
    CompanyDTO getCompanyByUserId(Long userId);
    List<CompanyDTO> getAllCompanies();
}
