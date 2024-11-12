package com.nghia.coffee_spring_web.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nghia.coffee_spring_web.domain.Product;
import com.nghia.coffee_spring_web.domain.User;
import com.nghia.coffee_spring_web.domain.dto.RegisterDTO;
import com.nghia.coffee_spring_web.service.ProductService;
import com.nghia.coffee_spring_web.service.UserService;

import jakarta.validation.Valid;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.findAllProduct();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }
        User user = this.userService.registerDTOtoUser(registerDTO);
        String password = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveAUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "client/auth/login";
    }

    @GetMapping("/access-deny")
    public String getDenyPage() {
        return "client/auth/deny";
    }
}
