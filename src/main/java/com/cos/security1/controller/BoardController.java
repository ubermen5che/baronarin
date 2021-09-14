package com.cos.security1.controller;

import com.cos.security1.config.auth.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/user/boardUpload")
    public String boardUpload()
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(userDetails!=null && userDetails.getRole().equals("ROLE_ADMIN"))
        {
            return "admin/boardUpload";
        }

        return "redirect:/";
    }
}
