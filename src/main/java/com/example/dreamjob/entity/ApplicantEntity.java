//package com.example.dreamjob.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Setter
//@Getter
//@Table(name="apllicant")
//public class ApplicantEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long applicant_id;
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "user_id")
//    private UserEntity user;
//
//
//    private String Cv;
//    private String Experience;
//    private String Location;
//    private String Desired_job;
//    private String image_logo;
//
//
//}
