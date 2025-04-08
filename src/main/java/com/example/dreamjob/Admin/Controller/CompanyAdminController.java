package com.example.dreamjob.Admin.Controller;

import com.example.dreamjob.dto.CompanyDTO;
import com.example.dreamjob.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/company")
public class CompanyAdminController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            List<CompanyDTO> companyDTOS = companyService.getAllCompanies();
            return ResponseEntity.status(HttpStatus.OK).body(companyDTOS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
