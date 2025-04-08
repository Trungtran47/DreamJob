package com.example.dreamjob.Admin.Controller;

import org.springframework.ui.Model;
import com.example.dreamjob.dto.UserDTO;
import com.example.dreamjob.dto.response.UserLogin;
import com.example.dreamjob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String redirectToLogin() {
        return "redirect:/pages/sign-in.html";  // Chuyển hướng đến trang login
    }
    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO, Model model) {
        try {
            UserLogin isAuthenticated = userService.LoginAdmin(userDTO);
            if (isAuthenticated != null) {
                return "redirect:/pages/dashboard.html";
            }
        } catch (RuntimeException e) {
            // Nếu có lỗi trong quá trình đăng nhập (sai email/mật khẩu), hiển thị thông báo lỗi
            model.addAttribute("error", e.getMessage());
        }

        // Trả về trang đăng nhập nếu đăng nhập thất bại
        return "/pages/sign-in.html";
    }


}
