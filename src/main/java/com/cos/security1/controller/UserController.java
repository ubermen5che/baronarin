package com.cos.security1.controller;

import com.cos.security1.config.auth.UserDetailsImpl;
import com.cos.security1.domain.Article;
import com.cos.security1.domain.Copyright;
import com.cos.security1.domain.User;
import com.cos.security1.repository.*;
import com.cos.security1.service.MailSenderService;
import com.cos.security1.service.UserService;
import com.google.api.client.json.Json;
import com.google.firebase.auth.FirebaseToken;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired//스프링부트가 미리 생성한 객체를 자동연결
    private ArticleRepository articleRepository;

    @Autowired
    private CopyrightRepository copyrightRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private UserService userService;

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

        User user = userRepository.findByEmail(username);

        List<Article> articleList = articleRepository.findAll();


        List<Article> ongoing = new Vector<Article>();
        List<Article> completed = new Vector<Article>();

        for (Article arti : articleList) {
            if (user.getEmail().equals(arti.getPeople1_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed.add(arti);
                else
                    ongoing.add(arti);
            }

            if (user.getEmail().equals(arti.getPeople2_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed.add(arti);
                else
                    ongoing.add(arti);
            }

            if (user.getEmail().equals(arti.getPeople3_email())) {
                if (arti.getSign_count() == arti.getPeople_size())
                    completed.add(arti);
                else
                    ongoing.add(arti);
            }
        }

        model.addAttribute("name", user.getRealName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("signname", user.getSignName());

        List<Copyright> copyrightList = copyrightRepository.findAll();

        List<Copyright> ongoing2 = new Vector<Copyright>();
        List<Copyright> completed2 = new Vector<Copyright>();

        for (Copyright arti : copyrightList) {
            if (user.getEmail().equals(arti.getPeople1_email())) {
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
    public String adminpage(Model model) {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal != null && principal.getRole().equals("ROLE_ADMIN"))) {
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
    public String editor() {
        return "user/customerEditor";
    }

    //SecurityConfig 작성후엔 스프링 시큐리티가 자동으로 안잡아줌
    @RequestMapping(value = {"/loginForm"}, method = RequestMethod.GET)
    public String loginform(HttpServletRequest req) {
        return "loginForm";
    }

    @PostMapping("/findUserId")
    public String findUserId() {
        return "redirect:/joinForm";

    }

    @RequestMapping(value = {"/joinForm"}, method = RequestMethod.GET)
    public String joinForm(Model model) {
        return "joinFormRetry";
    }

    //아이디 중복체크 RedirectAttributes는 redirect: 리턴 시 쓸 수 있음
    @RequestMapping(value = {"/dupname"}, method = RequestMethod.POST)
    public String dupname(String checkname, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        //이메일 형식인지 검사
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(checkname);
        if (m.matches()) {
            err = true;
        }

        //이메일 형식일 경우
        if (err)
            //해당 아이디가 없다면,
            //빈 값이 아닐 경우
            if (checkname.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "") != null && userRepository.findByEmail(checkname) == null) {
                System.out.println("해당이름 사용가능 : " + checkname.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "") + ", " + request.getAttribute("checkName"));
                redirectAttributes.addFlashAttribute("checkSuccess", 1);
            } else {
                redirectAttributes.addFlashAttribute("checkSuccess", -1);
            }
        else {
            redirectAttributes.addFlashAttribute("checkSuccess", -2);
        }
        redirectAttributes.addFlashAttribute("checkName", checkname);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @ResponseBody
    @RequestMapping(value = "/idCheck", method = RequestMethod.POST)
    public int IdCheck(@RequestBody String memberId) throws Exception {

        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(memberId);

        if (m.matches()) {
            err = true;
        }

        int count = -1;

        if (err) {
            if (userRepository.findByEmail(memberId) == null) {
                count = 0;
            } else {
                count = 1;
            }
        }
        return count;
    }

    //휴대폰 번호 중복 검사
    @PostMapping("/phoneNumberCheck")
    @ResponseBody
    public JSONObject phoneNumberCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject res = new JSONObject();
        String phoneNumber = request.getParameter("phoneNumber");

        if(userRepository.findByPhoneNumber(phoneNumber) == null) {
            System.out.println(phoneNumber);
            res.put("res", true);
            return res;
        } else{
            res.put("res", false);
            return res;
        }
    }

    @PostMapping("/join")//GetMapping이 post지원 안해준다해서
    @ResponseBody
    public JSONObject join(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //String test="평문";
        JSONObject jsonRes = new JSONObject();
        Boolean result = true;

        System.out.println("user = " + user);
        user.setRole("ROLE_USER");
        //userRepository.save(user);// 시큐리티로 로그인 불가=>패스워드 암호화가 안됨
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();

        PublicKey publickey = keyPair.getPublic();
        PrivateKey privatekey = keyPair.getPrivate();

        user.setPrivateKey(Base64.getEncoder().encodeToString(privatekey.getEncoded()));
        user.setPublicKey(Base64.getEncoder().encodeToString(publickey.getEncoded()));

        //임의의 authKey 생성 & 이메일 발송
        System.out.println("user.getEmail() = " + user.getEmail());
        String authKey = mailSenderService.sendAuthMail(user.getEmail());
        user.setAuthKey(authKey);
        user.setAuthStatus("N");
        userRepository.save(user);

        jsonRes.put("res", result);

        System.out.println("jsonRes = " + jsonRes);

        return jsonRes;
    }

    //map <authKey : authkey(RandomNumber)>
    @RequestMapping(value = {"/signUpConfirm"}, method = RequestMethod.GET)
    public String signUpConfirm(@RequestParam Map<String, String> map){
        Boolean res;

        //email, authKey 가 일치할경우 authStatus 업데이트
        System.out.println("map = " + map.get("email"));
        res = userService.updateAuthStatus(map.get("email"));

        if (res == true){
            return "redirect:/loginForm";
        }

        return "redirect:/";
    }
}

