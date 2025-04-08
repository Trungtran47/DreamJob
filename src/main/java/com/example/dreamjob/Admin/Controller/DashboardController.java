package com.example.dreamjob.Admin.Controller;

import com.example.dreamjob.Admin.DTO.DashboardDTO;
import com.example.dreamjob.Admin.Service.DashboardService;
import com.example.dreamjob.dto.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class DashboardController {
   @Autowired
    private DashboardService dashboardService;

   @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard() {
       try {
           DashboardDTO dashboardDTO = dashboardService.dashboardDTO();
           return ResponseEntity.status(HttpStatus.OK).body(dashboardDTO);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
   }
}
