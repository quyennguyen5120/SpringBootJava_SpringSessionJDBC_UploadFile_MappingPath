package com.example.thuchanh_4_1_2022.Controller;

import com.example.thuchanh_4_1_2022.Domain.User;
import com.example.thuchanh_4_1_2022.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class Login {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String redirectLogin(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/checkLogin")
    public String login(User user, HttpSession session){
        User p = new User();
        p =userService.findById(user.getUsername(), user.getPassword());
        if(p == null){
            return "redirect:/login/login";
        }
        else if(p.getAdmin() == true){
            session.setAttribute("user", p);
            return "redirect:/admin/index";
        }
        else{
            session.setAttribute("user", p);
            return "redirect:/user/index";
        }
    }
}
