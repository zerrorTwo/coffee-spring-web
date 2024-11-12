package com.nghia.coffee_spring_web.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nghia.coffee_spring_web.domain.User;
import com.nghia.coffee_spring_web.service.UploadService;
import com.nghia.coffee_spring_web.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/user")
    public String getShowUserPage(Model model) {
        List<User> users = this.userService.findAllUser();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult bindingResult, @RequestParam("userFile") MultipartFile file) {

        // Validate
        if (bindingResult.hasErrors()) {
            return "/admin/user/create";
        }
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));

        this.userService.handleSaveAUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        user.setPassword("");
        model.addAttribute("newUser", user);
        return "admin/user/update";
    }

    @PostMapping(value = "/admin/user/update")
    public String updateUserPage(@ModelAttribute("newUser") @Valid User newUser,
            BindingResult bindingResult) {
        // validate
        if (bindingResult.hasErrors()) {
            return "admin/user/update";
        }

        User existingUser = this.userService.getUserById(newUser.getId());

        if (existingUser != null) {
            // Update Email (nếu có)
            existingUser.setEmail(newUser.getEmail() != null ? newUser.getEmail() : existingUser.getEmail());

            // Nếu `password` rỗng
            if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
                newUser.setPassword(existingUser.getPassword()); // Giữ lại mật khẩu cũ
            } else {
                existingUser.setPassword(newUser.getPassword()); // Cập nhật mới (nếu có)
            }

            existingUser.setFullName(newUser.getFullName());
            existingUser.setAddress(newUser.getAddress());
            existingUser.setPhone(newUser.getPhone());

            this.userService.handleSaveAUser(existingUser);
        } else {
            return "redirect:/admin/user";
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("newUser", new User());
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @PostMapping(value = "/admin/user/delete")
    public String deleteUserPage(Model model, @ModelAttribute("newUser") User user) {
        this.userService.deleteAUser(user.getId());
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }
}
