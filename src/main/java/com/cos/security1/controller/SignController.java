package com.cos.security1.controller;

import com.cos.security1.UserRsa;
import com.cos.security1.config.auth.UserDetailsImpl;
import com.cos.security1.domain.*;
import com.cos.security1.common.Imagetest;
import com.cos.security1.repository.*;
import com.cos.security1.service.FileService;
import com.cos.security1.service.MailSenderService;
import com.cos.security1.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SignController {

    @Autowired
    private UserRepository userRepository;

    @Autowired//스프링부트가 미리 생성한 객체를 자동연결
    private ArticleRepository articleRepository;

    @Autowired
    private CopyrightRepository copyrightRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/sign")
    public String userSign(MessageForm message, String title, Timestamp create_time) throws Exception {
        System.out.println("서명 합성하기");
        //유저 정보 가져오기
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Article tempArt = articleRepository.findByPapername(title);


        if (tempArt != null && tempArt.getCreateDate().toString().equals(create_time.toString()))//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {
            System.out.println("계약서 찾음");
            String sign = null;

            System.out.println("원문 : " + tempArt.getPlain_text());


            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            String time1 = format1.format(time);

            String one = "1";

            if (tempArt.getPeople1_name().equals(principal.getRealname()) && tempArt.getPeople1_email().equals(principal.getUsername()) && !one.equals(tempArt.getPeople1_sign())) {
                sign = UserRsa.sign(tempArt.getPlain_text(), principal.getPrivateKey());
                tempArt.setPeople1_encrypt(sign);
                //System.out.println("사인1 : "+sign);
                System.out.println("사인1확인 결과 : " + UserRsa.verifySignarue(tempArt.getPlain_text(), sign, principal.getPublicKey()));
                tempArt.setPeople1_sign("1");
                tempArt.setSign_count(tempArt.getSign_count() + 1);
                tempArt.setPeople1_time(time1);
                //articleRepository.deleteById(tempArt.getId());
                articleRepository.save(tempArt);
            } else if (tempArt.getPeople2_name().equals(principal.getRealname()) && tempArt.getPeople2_email().equals(principal.getUsername()) && !one.equals(tempArt.getPeople2_sign())) {
                sign = UserRsa.sign(tempArt.getPlain_text(), principal.getPrivateKey());
                tempArt.setPeople2_encrypt(sign);
                //System.out.println("사인2 : "+sign);
                System.out.println("사인2확인 결과 : " + UserRsa.verifySignarue(tempArt.getPlain_text(), sign, principal.getPublicKey()));
                tempArt.setPeople2_sign("1");
                tempArt.setSign_count(tempArt.getSign_count() + 1);
                tempArt.setPeople2_time(time1);
                //articleRepository.deleteById(tempArt.getId());
                articleRepository.save(tempArt);
            } else if (tempArt.getPeople3_name().equals(principal.getRealname()) && tempArt.getPeople3_email().equals(principal.getUsername()) && !one.equals(tempArt.getPeople3_sign())) {
                sign = UserRsa.sign(tempArt.getPlain_text(), principal.getPrivateKey());
                tempArt.setPeople3_encrypt(sign);
                //System.out.println("사인3 : "+sign);
                System.out.println("사인3확인 결과 : " + UserRsa.verifySignarue(tempArt.getPlain_text(), sign, principal.getPublicKey()));
                tempArt.setPeople3_sign("1");
                tempArt.setSign_count(tempArt.getSign_count() + 1);
                tempArt.setPeople3_time(time1);
                //articleRepository.deleteById(tempArt.getId());
                articleRepository.save(tempArt);
            }

            //모두가 싸인을 완료했을 때
            boolean signCheck = false;

            System.out.println("계약 인원수 : " + tempArt.getPeople_size());
            switch (tempArt.getPeople_size()) {

                case 1:
                    if (one.equals(tempArt.getPeople1_sign())) {
                        System.out.println("1인 싸인 완료");
                        signCheck = true;
                    }
                    break;
                case 2:
                    System.out.println("싸인여부 1 : " + tempArt.getPeople1_sign() + "  싸인 여부 2 : " + tempArt.getPeople2_sign());
                    if (one.equals(tempArt.getPeople1_sign()) && one.equals(tempArt.getPeople2_sign())) {
                        System.out.println("2인 싸인 완료");
                        signCheck = true;
                    }
                    break;
                case 3:
                    if (one.equals(tempArt.getPeople1_sign()) && one.equals(tempArt.getPeople2_sign()) && one.equals(tempArt.getPeople3_sign())) {
                        System.out.println("3인 싸인 완료");
                        signCheck = true;
                    }
            }

            if (signCheck) {
                //봐야할 파일 주소와 계약서 자료 넘기기
                Imagetest.makeSignPage(fileService.getUpDownloadDir(), tempArt);
                mailSenderService.gmailSendArticle(tempArt, tempArt.getPeople1_email(), fileService.getUpResultDir() + File.separator + tempArt.getSer_fileName());
                System.out.println("사인과 이미지 합성완료");
                if (tempArt.getPeople_size() >= 2) {
                    mailSenderService.gmailSendArticle(tempArt, tempArt.getPeople2_email(), "result" + File.separator + tempArt.getSer_fileName());
                }

                if (tempArt.getPeople_size() == 3) {
                    mailSenderService.gmailSendArticle(tempArt, tempArt.getPeople3_email(), "result" + File.separator + tempArt.getSer_fileName());
                }


                //완료된 계약서 파일 해쉬
                InputStream imageStream = new FileInputStream(fileService.getUpResultDir() + File.separator + tempArt.getSer_fileName());//
                byte[] imageByteArray = IOUtils.toByteArray(imageStream);
                imageStream.close();

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(imageByteArray);


                StringBuilder builder = new StringBuilder();
                for (byte b : md.digest()) {
                    builder.append(String.format("%02x", b));
                }
                tempArt.setResult_hash("" + builder.toString());

                System.out.println("해쉬값 : " + tempArt.getResult_hash());
                articleRepository.save(tempArt);
            }

        }
        return "redirect:/user/myPage";//홈페이지로 보냄
    }

    //전자서명 만들기
    @PostMapping("/user/makeSign")
    public String makeSign(MessageForm message, @RequestParam("uploadFile") MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception
    {
        //db저장
        Article savedArticle = message.toEntity();

        System.out.println("첫번째 사인 좌표 x : "+message.getPer1()[0]+", y : "+message.getPer1()[1]);

        if(message.getPer1()!=null && message.getPer1().length==2)
        {
            savedArticle.setSign1_xpos(Integer.parseInt( message.getPer1()[0].replaceAll("[^0-9]", "")));
            savedArticle.setSign1_ypos(Integer.parseInt( message.getPer1()[1].replaceAll("[^0-9]", "")));
        }
        if(message.getPer2()!=null && message.getPer2().length==2)
        {
            savedArticle.setSign2_xpos(Integer.parseInt( message.getPer2()[0].replaceAll("[^0-9]", "")));
            savedArticle.setSign2_ypos(Integer.parseInt( message.getPer2()[1].replaceAll("[^0-9]", "")));
        }
        if(message.getPer3()!=null && message.getPer3().length==2)
        {
            savedArticle.setSign3_xpos(Integer.parseInt( message.getPer3()[0].replaceAll("[^0-9]", "")));
            savedArticle.setSign3_ypos(Integer.parseInt( message.getPer3()[1].replaceAll("[^0-9]", "")));
        }

        savedArticle.setSign_count(0);
        savedArticle.setPlain_text("계약서 원문");
        savedArticle.setFile_size(file.getSize());
        savedArticle.setOrig_name(file.getOriginalFilename());
        //서버에 저장된 파일이름 저장
        savedArticle.setSer_fileName(fileService.fileUpload(file, null));
        //경로저장
        savedArticle.setFile_path(fileService.getUpDownloadDir()+File.separator+savedArticle.getSer_fileName());

        articleRepository.save(savedArticle);

        savedArticle.setUniquenum(savedArticle.getCreateDate().toString().replaceAll("[^0-9]", ""));
        //id_계약서이름으로 계약서 이름 지정
        String tempString = savedArticle.getPapername();
        savedArticle.setPapername(savedArticle.getId().toString()+"_"+tempString);

        //db에 존재하는 유저인지 판단
        boolean isValidUser=true;

        switch(savedArticle.getPeople_size())
        {
            case 3:
                User user3 = userRepository.findByEmail(savedArticle.getPeople3_email());
                if(user3==null)
                {
                    isValidUser=false;
                    break;
                }
                savedArticle.setPeople3_signname(user3.getSignName());
                System.out.println("사인 이름3 : "+user3.getSignName());
            case 2:
                User user2 = userRepository.findByEmail(savedArticle.getPeople2_email());
                if(user2==null)
                {
                    isValidUser=false;
                    break;
                }
                savedArticle.setPeople2_signname(user2.getSignName());
                System.out.println("사인 이름2 : "+user2.getSignName());
            case 1:
                User user = userRepository.findByEmail(savedArticle.getPeople1_email());
                if(user==null)
                {
                    isValidUser=false;
                    break;
                }
                savedArticle.setPeople1_signname(user.getSignName());
                System.out.println("사인 이름1 : "+user.getSignName());
        }

        if(isValidUser)
        {
            mailSenderService.gmailSendArticle(savedArticle,savedArticle.getPeople1_email(),null);

            if(savedArticle.getPeople_size()>=2)
            {
                mailSenderService.gmailSendArticle(savedArticle,savedArticle.getPeople2_email(),null);
            }

            if(savedArticle.getPeople_size()==3)
            {
                mailSenderService.gmailSendArticle(savedArticle,savedArticle.getPeople3_email(),null);
            }
            articleRepository.save(savedArticle);
        }
        else
        {
            if(isValidUser==false)
                redirectAttributes.addFlashAttribute("isValidUser",-1);

            articleRepository.deleteById(savedArticle.getId());
            String referer = request.getHeader("Referer");
            return "redirect:"+referer;
        }

        return "redirect:/";//홈페이지로 보냄
    }

    @PostMapping("/user/makeSign2")
    public String makeSign2(MessageForm message, @RequestParam("uploadFile") MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception
    {
        //db저장
        Copyright savedCopyright = message.toCopyright();

        System.out.println("첫번째 사인 좌표 x : "+message.getPer1()[0]+", y : "+message.getPer1()[1]);

        if(message.getPer1()!=null && message.getPer1().length==2)
        {
            savedCopyright.setSign1_xpos(Integer.parseInt( message.getPer1()[0].replaceAll("[^0-9]", "")));
            savedCopyright.setSign1_ypos(Integer.parseInt( message.getPer1()[1].replaceAll("[^0-9]", "")));
        }

        savedCopyright.setSign_count(0);
        savedCopyright.setPlain_text("계약서 원문");
        savedCopyright.setFile_size(file.getSize());
        savedCopyright.setOrig_name(file.getOriginalFilename());
        //서버에 저장된 파일이름 저장
        savedCopyright.setSer_fileName(fileService.fileUpload(file, null));
        //경로저장
        savedCopyright.setFile_path(fileService.getUpDownloadDir()+File.separator+savedCopyright.getSer_fileName());

        User user = userRepository.findByEmail(savedCopyright.getPeople1_email());
        if(user!=null)
        {
            savedCopyright.setPeople1_signname(user.getSignName());
        }
        copyrightRepository.save(savedCopyright);
        //System.out.println("계약서 생성 시간1 : "+savedArticle.getCreateDate().toString() );

        savedCopyright.setUniquenum(savedCopyright.getCreateDate().toString().replaceAll("[^0-9]", ""));
        //id_계약서이름으로 계약서 이름 지정
        String tempString = savedCopyright.getPapername();
        savedCopyright.setPapername(savedCopyright.getId().toString()+"_"+tempString);

        mailSenderService.gmailSendCopyright(savedCopyright,savedCopyright.getPeople1_email(),null);
        copyrightRepository.save(savedCopyright);

        return "redirect:/";//홈페이지로 보냄
    }


    @ResponseBody//페이지로 응답할 것임을 명시
    @PostMapping("/upload/sign")
    public Map<String, String> uploadSign(@RequestParam(value="file", required=true) MultipartFile file)
    {
        System.out.println("파일 크기 : "+ file.getSize());

        UserDetailsImpl principal= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user =  userService.findUser(principal.getUsername());

        System.out.println("user.getSignCount() = " + user.getSignCount());
        
        //sign등록 이력이 없는 경우 최초 등록 사인이름은 email_sign1.jpg로 한다.
        String userSignName = principal.getUsername() + "_sign" + user.getSignCount() + ".jpg";

        fileService.fileUpload(file, userSignName);
        UserSign userSign = new UserSign();
        userSign.setUser(user);
        userSign.setSignFileName(userSignName);
        userSign.setIsNFT("F");
        userService.saveUserSign(userSign);
        user.addUserSign(userSign);
        System.out.println("user.getSignCount() + 1 = " + user.getSignCount() + 1);
        user.setSignCount(user.getSignCount() + 1);
        userService.saveUser(user);
        Map<String,String> resMap = new HashMap<>();
        resMap.put("result", "true");

        return resMap;
    }


    @ResponseBody//페이지로 응답할 것임을 명시
    @PostMapping("/user/changesign")
    public int changesign(@RequestParam(value="file", required=true) MultipartFile file, @RequestParam(required=false) String title, @RequestParam(required=false) String create_time)
    {
        System.out.println("파일 크기 : "+ file.getSize());
        System.out.println("title = " + title);
        System.out.println("create_time = " + create_time);
        int result=0;

        UserDetailsImpl principal= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Article tempArt = articleRepository.findByPapername(title);

        User tempUser =  userRepository.findByEmail(principal.getUsername());

        if (tempUser != null ) {
            System.out.println("서명파일 : " + tempUser.getSignName());
            if (tempUser.getSignName() != null) {
                File deleteFile = new File(fileService.getUpDownloadDir() + File.separator + "userSign" + File.separator + principal.getSignname());

                if(deleteFile.delete())
                {
                    System.out.println("서명파일 삭제완료");

                }
            }

            fileService.fileUpload(file, principal.getUsername()+"_sign.png");//tempUser.getSignname());

            //article db도 변경시켜야함
            if(tempArt!=null && tempArt.getCreateDate().toString().equals(create_time.toString()))
            {

                if(tempArt.getPeople1_name().equals(principal.getRealname()))
                {
                    tempArt.setPeople1_signname(principal.getUsername()+"_sign.png");
                }

                else if(tempArt.getPeople2_name().equals(principal.getRealname()))
                {
                    tempArt.setPeople2_signname(principal.getUsername()+"_sign.png");
                }

                else if(tempArt.getPeople3_name().equals(principal.getRealname()))
                {
                    tempArt.setPeople3_signname(principal.getUsername()+"_sign.png");
                }
                articleRepository.save(tempArt);
            }
            tempUser.setSignName(principal.getUsername()+"_sign.png");
            userRepository.save(tempUser);
        }
        return result;
    }

    @ResponseBody//페이지로 응답할 것임을 명시
    @PostMapping("/user/changesign2")
    public int changesign2(@RequestParam(value="file", required=true) MultipartFile file, @RequestParam(required=false) String title, @RequestParam(required=false) String create_time)
    {
        System.out.println("파일 이름 : "+ file.getSize());
        int result=0;

        UserDetailsImpl principal= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Copyright tempArt = copyrightRepository.findByPapername(title);

        User tempUser =  userRepository.findByEmail(principal.getUsername());

        if (tempUser != null ) {
            System.out.println("서명파일 : " + tempUser.getSignName());
            if (tempUser.getSignName() != null) {
                File deleteFile = new File(fileService.getUpDownloadDir() + File.separator + "userSign" + File.separator + principal.getSignname());

                if(deleteFile.delete())
                {
                    System.out.println("서명파일 삭제완료");
                }
            }

            fileService.fileUpload(file, principal.getUsername()+"_sign.png");//tempUser.getSignname());

            //article db도 변경시켜야함
            if(tempArt!=null && tempArt.getCreateDate().toString().equals(create_time.toString()))
            {

                if(tempArt.getPeople1_name().equals(principal.getRealname()))
                {
                    tempArt.setPeople1_signname(principal.getUsername()+"_sign.png");
                }
                copyrightRepository.save(tempArt);
            }
            tempUser.setSignName(principal.getUsername()+"_sign.png");
            userRepository.save(tempUser);
        }

        return result;
    }

    @ResponseBody//페이지로 응답할 것임을 명시
    @GetMapping("/user/checksign")
    public int checkSign()
    {
        UserDetailsImpl principal= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User tempUser =  userRepository.findByEmail(principal.getUsername());

        if (tempUser != null && tempUser.getSignName() != null && tempUser.getSignName().length() >= 1) {
            File file =new File("input"+File.separator + "userSign"+File.separator+tempUser.getSignName());
            System.out.println("서명파일 확인 : " + file.getAbsolutePath());

            if(file.exists())
            {
                return 1;
            }
        }
        return -1;
    }

    @PostMapping("/user/sign2")
    public String userSign2(MessageForm message, String title, Timestamp create_time ) throws Exception
    {
        System.out.println("서명 합성하기");
        //유저 정보 가져오기
        UserDetailsImpl principal= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Copyright searchedCopyright = copyrightRepository.findByPapername(title);


        if(searchedCopyright!=null && searchedCopyright.getCreateDate().toString().equals(create_time.toString()))//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {
            System.out.println("계약서 찾음");
            String sign=null;

            System.out.println("원문 : " +searchedCopyright.getPlain_text());


            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            String time1 = format1.format(time);

            String one="1";

            if(searchedCopyright.getPeople1_name().equals(principal.getRealname()) && searchedCopyright.getPeople1_email().equals(principal.getUsername())  && !one.equals(searchedCopyright.getPeople1_sign()))
            {
                sign = UserRsa.sign(searchedCopyright.getPlain_text(), principal.getPrivateKey());
                searchedCopyright.setPeople1_encrypt(sign);
                //System.out.println("사인1 : "+sign);
                System.out.println("사인1확인 결과 : "+ UserRsa.verifySignarue(searchedCopyright.getPlain_text(),sign, principal.getPublicKey()));
                searchedCopyright.setPeople1_sign("1");
                searchedCopyright.setSign_count(searchedCopyright.getSign_count()+1);
                searchedCopyright.setPeople1_time(time1);
                //articleRepository.deleteById(tempArt.getId());
                copyrightRepository.save(searchedCopyright);
            }

            //모두가 싸인을 완료했을 때
            boolean signCheck = false;

            System.out.println("계약 인원수 : "+searchedCopyright.getPeople_size());

            if(one.equals(searchedCopyright.getPeople1_sign()))
            {
                System.out.println("1인 싸인 완료");
                signCheck = true;

            }

            if(signCheck)
            {
                //봐야할 파일 주소와 계약서 자료 넘기기
                Imagetest.makeSignPage2(fileService.getUpDownloadDir(), searchedCopyright);
                mailSenderService.gmailSendCopyright(searchedCopyright,searchedCopyright.getPeople1_email(), fileService.getUpResultDir()+File.separator+searchedCopyright.getSer_fileName());
                System.out.println("사인과 이미지 합성완료");

                //완료된 계약서 파일 해쉬
                InputStream imageStream = new FileInputStream(fileService.getUpResultDir() +File.separator + searchedCopyright.getSer_fileName());//
                byte[] imageByteArray = IOUtils.toByteArray(imageStream);
                imageStream.close();

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(imageByteArray);

                StringBuilder builder = new StringBuilder();
                for (byte b: md.digest()) {
                    builder.append(String.format("%02x", b));
                }
                searchedCopyright.setResult_hash(""+builder.toString());

                System.out.println("해쉬값 : "+searchedCopyright.getResult_hash());
                copyrightRepository.save(searchedCopyright);
            }

        }
        return "redirect:/user/myPage";//홈페이지로 보냄
    }

    //input파일안 계약서 다운로드
    @PostMapping("/user/signedDownload")
    public void signedDownload(String title,Timestamp create_time , HttpServletResponse response)
    {
        Article tempArt = articleRepository.findByPapername(title);

        if(tempArt!=null && tempArt.getCreateDate().toString().equals(create_time.toString()))//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {
            BufferedOutputStream out = null;
            InputStream in = null;

            System.out.println("서명 이전 다운로드 : "+tempArt.getOrig_name());
            try {
                //한글제목은 encoding해서 보내줘야함
                String encordedFilename = URLEncoder.encode(tempArt.getOrig_name(),"UTF-8").replace("+", "%20");
                response.setHeader("Content-Disposition",   "attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename);
                response.setContentType("image/*");
                //response.setHeader("Content-Disposition", "inline;filename="+tempArt.getSer_fileName());
                File file = new File(fileService.getUpDownloadDir()+File.separator+tempArt.getSer_fileName());//upDownloadDir로 바꿀 것
                if(file.exists()) {
                    in = new FileInputStream(file);
                    out = new BufferedOutputStream(response.getOutputStream());
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(out != null) { out.flush();}
                    if(out != null) { out.close();}
                    if(out != null) { in.close();}
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    @PostMapping("/user/signedDownload2")
    public void signedDownload2(String title,Timestamp create_time , HttpServletResponse response)
    {
        Copyright tempArt = copyrightRepository.findByPapername(title);

        if(tempArt!=null && tempArt.getCreateDate().toString().equals(create_time.toString()))//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {
            BufferedOutputStream out = null;
            InputStream in = null;

            System.out.println("서명 이전 다운로드 : "+tempArt.getOrig_name());
            try {
                //한글제목은 encoding해서 보내줘야함
                String encordedFilename = URLEncoder.encode(tempArt.getOrig_name(),"UTF-8").replace("+", "%20");
                response.setHeader("Content-Disposition",   "attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename);
                response.setContentType("image/*");
                //response.setHeader("Content-Disposition", "inline;filename="+tempArt.getSer_fileName());
                File file = new File(fileService.getUpDownloadDir()+File.separator+tempArt.getSer_fileName());//upDownloadDir로 바꿀 것
                if(file.exists()) {
                    in = new FileInputStream(file);
                    out = new BufferedOutputStream(response.getOutputStream());
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(out != null) { out.flush();}
                    if(out != null) { out.close();}
                    if(out != null) { in.close();}
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/user/signUpload")
    public String mypage(Model model) {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findUser(principal.getUsername());
        List<UserSign> userSings = user.getUserSigns();
        List<String> signURLs = new ArrayList<>();

        for (int i = 0; i < userSings.size(); i++){
            signURLs.add("/sign/" + userSings.get(i).getSignFileName());
        }

        System.out.println("signURLs = " + signURLs);

        model.addAttribute("signUrls", signURLs);

        return "user/signUpload";
    }

    @PostMapping(value="/upload/signNFT")
    public String uploadNFT(HttpServletRequest request){

        String[] ajaxMsg = request.getParameterValues("chkBoxArr");
        for(String msg : ajaxMsg){
            System.out.println("msg = " + msg);
        }

        return "redirect:list";
    }

    @GetMapping(path = "/sign/{id}")
    public void setImageFileById(@PathVariable(name = "id") String id, HttpServletResponse response) throws IOException {
        Resource resource = fileService.loadFile("userSign", id);
        // 파일 정보를 찾고
        StringBuilder sb = new StringBuilder("file:///" + fileService.getUpDownloadDir() + "/userSign/");
        // 파일이 실제로 저장되어 있는 경로에
        String fileName = resource.getFilename();
        sb.append(fileName);
        // 파일 이름을 더해

        URL fileUrl = new URL(sb.toString());
        // file URL을 생성하고

        IOUtils.copy(fileUrl.openStream(), response.getOutputStream());
        // IOUtils.copy는 input에서 output으로 encoding 맞춰서 복사하는 메소드다
        // openStream으로 fileUrl의 통로( 입력 스트림 )를 열고 respons의 outputStream에 복사하면 끝
    }
    //사인 완료된 result 파일안의 계약서 다운로드
    @PostMapping("/user/completesignedDownload")
    public void completesignedDownload(String title,Timestamp create_time , HttpServletResponse response)
    {
        Article tempArt = articleRepository.findByPapername(title);


        if(tempArt!=null && tempArt.getCreateDate().toString().equals(create_time.toString()))//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {

            BufferedOutputStream out = null;
            InputStream in = null;

            System.out.println("서명 이전 다운로드 : "+tempArt.getOrig_name());
            try {
                //한글제목은 encoding해서 보내줘야함
                String encordedFilename = URLEncoder.encode(tempArt.getOrig_name(),"UTF-8").replace("+", "%20");
                response.setHeader("Content-Disposition",   "attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename);
                response.setContentType("image/*");
                //response.setHeader("Content-Disposition", "inline;filename="+tempArt.getSer_fileName());
                File file = new File(fileService.getUpResultDir()+File.separator+tempArt.getSer_fileName());//upDownloadDir로 바꿀 것
                if(file.exists()) {
                    in = new FileInputStream(file);
                    out = new BufferedOutputStream(response.getOutputStream());
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(out != null) { out.flush();}
                    if(out != null) { out.close();}
                    if(out != null) { in.close();}
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @PostMapping("/user/completesignedDownload2")
    public void completesignedDownload2(String title,Timestamp create_time , HttpServletResponse response)
    {
        Copyright tempArt = copyrightRepository.findByPapername(title);

        if(tempArt!=null && tempArt.getCreateDate().toString().equals(create_time.toString()))//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {

            BufferedOutputStream out = null;
            InputStream in = null;

            System.out.println("서명 이전 다운로드 : "+tempArt.getOrig_name());
            try {
                //한글제목은 encoding해서 보내줘야함
                String encordedFilename = URLEncoder.encode(tempArt.getOrig_name(),"UTF-8").replace("+", "%20");
                response.setHeader("Content-Disposition",   "attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename);
                response.setContentType("image/*");
                //response.setHeader("Content-Disposition", "inline;filename="+tempArt.getSer_fileName());
                File file = new File(fileService.getUpResultDir()+File.separator+tempArt.getSer_fileName());//upDownloadDir로 바꿀 것
                if(file.exists()) {
                    in = new FileInputStream(file);
                    out = new BufferedOutputStream(response.getOutputStream());
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(out != null) { out.flush();}
                    if(out != null) { out.close();}
                    if(out != null) { in.close();}
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
