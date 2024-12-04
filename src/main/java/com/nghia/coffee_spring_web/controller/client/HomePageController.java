package com.nghia.coffee_spring_web.controller.client;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghia.coffee_spring_web.domain.Order;
import com.nghia.coffee_spring_web.domain.Product;
import com.nghia.coffee_spring_web.domain.User;
import com.nghia.coffee_spring_web.domain.dto.RegisterDTO;
import com.nghia.coffee_spring_web.service.EmailService;
import com.nghia.coffee_spring_web.service.OrderService;
import com.nghia.coffee_spring_web.service.ProductService;
import com.nghia.coffee_spring_web.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;
    private final EmailService emailService;

    public HomePageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder,
            OrderService orderService, EmailService emailService) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        // List<Product> products = this.productService.findAllProducts();
        Pageable pageable = PageRequest.of(0, 8);
        Page<Product> prs = this.productService.findAllProducts(pageable);
        List<Product> products = prs.getContent();
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
            BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        // Lưu thông tin tạm thời vào session
        HttpSession session = request.getSession(true);
        session.setAttribute("tempUser", registerDTO);
        session.setAttribute("registrationTime", System.currentTimeMillis());

        // Gửi email xác thực
        sendVerificationEmail(registerDTO.getEmail(), registerDTO.getFirstName());

        model.addAttribute("registerSuccess", true);
        model.addAttribute("email", registerDTO.getEmail());
        return "client/auth/register";
    }

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("email") String email, HttpSession session) {
        try {
            if (email == null || email.isEmpty()) {
                return "redirect:/login?verifyError=true";
            }

            RegisterDTO tempUserDTO = (RegisterDTO) session.getAttribute("tempUser");
            Long registrationTime = (Long) session.getAttribute("registrationTime");

            if (tempUserDTO == null || registrationTime == null ||
                    System.currentTimeMillis() - registrationTime > 30 * 60 * 1000) {
                return "redirect:/login?verifyError=expired";
            }

            if (tempUserDTO.getEmail().equals(email)) {
                // Tạo và lưu User mới
                User user = new User();
                user.setEmail(tempUserDTO.getEmail());
                user.setPassword(passwordEncoder.encode(tempUserDTO.getPassword()));
                user.setFullName(tempUserDTO.getFirstName() + " " + tempUserDTO.getLastName());
                user.setRole(userService.getRoleByName("USER"));
                user.setConfirmEmail(true);

                userService.handleSaveAUser(user);

                // Xóa thông tin tạm thời
                session.removeAttribute("tempUser");
                session.removeAttribute("registrationTime");

                return "redirect:/login?verifySuccess=true";
            }

            return "redirect:/login?verifyError=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login?verifyError=true";
        }
    }

    private void sendVerificationEmail(String email, String firstName) {
        String confirmationLink = "http://localhost:8080/verify-email?email=" + email;
        try {
            emailService.sendEmail(
                    email,
                    "Xác nhận đăng ký tài khoản",
                    "Chào " + firstName + ",\n\n" +
                            "Vui lòng nhấn vào liên kết sau để xác nhận email của bạn:\n\n" +
                            "<a href='" + confirmationLink + "'>Xác nhận email</a>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "client/auth/login";
    }

    @GetMapping("/access-deny")
    public String getDenyPage() {
        return "client/auth/deny";
    }

    @GetMapping("/order-history")
    public String getOrderHistoryPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        List<Order> orders = this.orderService.fetchOrderByUser(currentUser);
        model.addAttribute("orders", orders);

        return "client/cart/order-history";
    }

}
