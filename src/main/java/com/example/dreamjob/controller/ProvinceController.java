package com.example.dreamjob.controller;

import com.example.dreamjob.dto.ProvinceDTO;
import com.example.dreamjob.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/getAll")
    public ResponseEntity<?>  getAllProvince() {
        try {
            List<ProvinceDTO> provinceDTOS = provinceService.getAllProvinces();
            return ResponseEntity.ok(provinceDTOS);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
