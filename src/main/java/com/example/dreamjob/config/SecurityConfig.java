package com.example.dreamjob.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.SerializationFeature;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // Để không chuyển đổi LocalDateTime thành timestamp
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Bật CORS với cấu hình
                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/DreamJob/**").permitAll() // Cho phép tất cả với đường dẫn này
//                        .requestMatchers("/DreamJob/Admin/**").hasRole("ADMIN") // Chỉ người dùng có vai trò ADMIN mới được phép
                        .anyRequest().permitAll() // Các yêu cầu còn lại phải được xác thực
                )
                .formLogin(login -> login
                        .loginPage("/admin/login") // Trang login tùy chỉnh
                        .defaultSuccessUrl("/DreamJob/Admin/dashboard", true) // Chuyển hướng sau khi login thành công
                        .permitAll() // Cho phép mọi người truy cập trang login
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout") // URL xử lý logout
                        .logoutSuccessUrl("/DreamJob") // Chuyển hướng sau khi logout
                        .permitAll() // Cho phép mọi người truy cập logout
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Thay thế allowedOrigins bằng allowedOriginPatterns
        configuration.setAllowedOriginPatterns(List.of("*")); // Cho phép tất cả nguồn
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true); // Đặt true để cho phép gửi thông tin xác thực
        configuration.setAllowedHeaders(List.of("*")); // Cho phép tất cả header

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Áp dụng cho tất cả các endpoint
        return source;
    }
}
