package com.saransh.controller;

import com.saransh.entity.User;
import com.saransh.service.UserService;
import com.saransh.utils.SortUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, trimmer);
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User()); 
        return "login"; 
    }

    @PostMapping("/login")
    public String loginProcess(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request) {
    	
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser == null || 
            !existingUser.getUsername().equals(user.getUsername()) || 
            !existingUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("loginError", "Invalid username or password");
            return "login"; 
        }
        request.getSession().setAttribute("loggedInUser", existingUser);
        
        if(existingUser.getRole().equals("admin")) return "redirect:/admin/user/list";
        else
        return "redirect:/customer/list";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); 
        return "redirect:/";
    }

    @GetMapping("/user/list")
    public String userList(@RequestParam(name = "sortBy", required = false) String sortBy, Model model) {
        List<User> users;

        if (sortBy != null) {
            int sort = Integer.parseInt(sortBy);
            users = userService.getUsers(sort);
        } else {
            users = userService.getUsers(SortUtils.SortByLastName.getValue());
        }

        model.addAttribute("users", users);
        return "user-list";
    }
    
    @GetMapping("/user/search")
    public String searchUser(@RequestParam(required = false) String search, Model model) {
        if (search != null) {
            model.addAttribute("search", search);
            model.addAttribute("users", userService.searchUser(search));
            return "user-list";
        }
        return "redirect:/admin/user/list";
    }
    
    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }
    
    @PostMapping("/sign-up")
    public String registerUser(
            @Valid @ModelAttribute("user") User user,
            @RequestParam("verificationCode") String verificationCode,
            Model model,
            BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Please fix the errors in the form.");
            return "sign-up";
        }
        
        User admin= userService.getUser(1);

        if (!verificationCode.equals(admin.getCode())) {
            model.addAttribute("error", "Invalid verification code!");
            return "sign-up"; 
        }
        
        user.setRole("user");
        user.setCode("");
        
        userService.addUser(user);

        return "redirect:/admin/login";
    }



    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/user/list";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
