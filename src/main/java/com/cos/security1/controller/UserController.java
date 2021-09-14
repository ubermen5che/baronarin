package com.cos.security1.controller;

import com.cos.security1.config.auth.UserDetailsImpl;
import com.cos.security1.domain.Article;
import com.cos.security1.domain.Copyright;
import com.cos.security1.domain.User;
import com.cos.security1.repository.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Vector;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired//스프링부트가 미리 생성한 객체를 자동연결
    private ArticleRepository articleRepository;

    @Autowired
    private CopyrightRepository copyrightRepository;

    @GetMapping("/user")
    public String userMain() {
        return "user/userForm";
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @GetMapping("/user/serial")
    public JSONObject searchSerial(@RequestParam("serial") String serial, Model model) {
        JSONObject res = new JSONObject();


        System.out.println("serial : " + serial);
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Article article = articleRepository.findByUniquenum(serial);
        int result = 0;

        if (article != null) {
            if (article.getPeople1_email().equals(principal.getUsername()))
                result = 1;

            else if (article.getPeople2_email().equals(principal.getUsername()))
                result = 2;

            else if (article.getPeople3_email().equals(principal.getUsername()))
                result = 3;
        }
        System.out.println("result : " + result);
        if (result != 0) {

            res.put("paper", article.getOrig_papername());
            res.put("filename", article.getOrig_name());
            switch (result) {
                case 3:
                    res.put("per3", article.getPeople3_name());
                case 2:
                    res.put("per2", article.getPeople2_name());
                case 1:
                    res.put("per1", article.getPeople1_name());
            }

            return res;
        }


        return null;
    }

    //저작권 등록
    @GetMapping("/user/copyright")
    public String copyright() {
        return "user/copyright";
    }

    //전자서명 작성하는 곳으로 가기
    @GetMapping("/user/DocumentPage")
    public String documentPage() {
        return "user/DocumentPage";
    }

    //마이페이지
    @SuppressWarnings("unused")
    @GetMapping("/user/myPage")
    public String mypage(Model model) {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Article> articleList = articleRepository.findAll();
        List<Article> ongoing = new Vector<Article>();
        List<Article> completed = new Vector<Article>();

        for (Article arti : articleList) {
            if (principal.getUsername().equals(arti.getPeople1_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed.add(arti);
                else
                    ongoing.add(arti);
            }

            if (principal.getUsername().equals(arti.getPeople2_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed.add(arti);

                else
                    ongoing.add(arti);
            }

            if (principal.getUsername().equals(arti.getPeople3_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed.add(arti);
                else
                    ongoing.add(arti);
            }
        }

        model.addAttribute("name", principal.getRealname());
        model.addAttribute("email", principal.getUsername());
        model.addAttribute("signname", principal.getSignname());

        List<Copyright> copyrightList = copyrightRepository.findAll();

        List<Copyright> ongoing2 = new Vector<Copyright>();
        List<Copyright> completed2 = new Vector<Copyright>();

        for (Copyright arti : copyrightList) {
            if (principal.getUsername().equals(arti.getPeople1_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed2.add(arti);
                else
                    ongoing2.add(arti);
            }
        }

        if (ongoing != null) {
            model.addAttribute("ongoing", ongoing);
        }
        if (completed != null) {
            model.addAttribute("completed", completed);
        }
        if (ongoing2 != null) {
            model.addAttribute("ongoing2", ongoing2);
        }
        if (completed2 != null) {
            model.addAttribute("completed2", completed2);
        }

        return "user/myPage";
    }

    @SuppressWarnings("unused")
    @PostMapping("/user/adminmyPage")
    public String adminmypage(Model model, String username) {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal != null && principal.getRole().equals("ROLE_ADMIN"))) {
            return "redirect:/";
        }

        User user = userRepository.findByUsername(username);

        List<Article> articleList = articleRepository.findAll();


        List<Article> ongoing = new Vector<Article>();
        List<Article> completed = new Vector<Article>();

        for (Article arti : articleList) {
            if (user.getUsername().equals(arti.getPeople1_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed.add(arti);
                else
                    ongoing.add(arti);
            }

            if (user.getUsername().equals(arti.getPeople2_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed.add(arti);
                else
                    ongoing.add(arti);
            }

            if (user.getUsername().equals(arti.getPeople3_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed.add(arti);
                else
                    ongoing.add(arti);
            }
        }

        model.addAttribute("name", user.getRealname());
        model.addAttribute("email", user.getUsername());
        model.addAttribute("signname", user.getSignname());

        List<Copyright> copyrightList = copyrightRepository.findAll();

        List<Copyright> ongoing2 = new Vector<Copyright>();
        List<Copyright> completed2 = new Vector<Copyright>();

        for (Copyright arti : copyrightList) {
            if (user.getUsername().equals(arti.getPeople1_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed2.add(arti);
                else
                    ongoing2.add(arti);
            }
        }

        if (ongoing != null) {
            model.addAttribute("ongoing", ongoing);
        }
        if (completed != null) {
            model.addAttribute("completed", completed);
        }


        if (ongoing2 != null) {
            model.addAttribute("ongoing2", ongoing2);
        }
        if (completed2 != null) {
            model.addAttribute("completed2", completed2);
        }
        return "user/myPage";
    }

    //마이페이지
    @SuppressWarnings("unused")
    @GetMapping("/user/adminPage")
    public String adminpage(Model model)
    {
        UserDetailsImpl principal= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(principal!=null && principal.getRole().equals("ROLE_ADMIN"))) {
            return "redirect:/";
        }
        List<User> userList = userRepository.findAll();

        model.addAttribute("name", principal.getRealname());
        model.addAttribute("email", principal.getUsername());
        model.addAttribute("signname", principal.getSignname());
        model.addAttribute("userList", userList);

        return "admin/adminPage";
    }

    @GetMapping("/user/editor")
    public String editor()
    {
        return "user/customerEditor";
    }
}

