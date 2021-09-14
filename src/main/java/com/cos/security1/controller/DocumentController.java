package com.cos.security1.controller;

import com.cos.security1.config.auth.UserDetailsImpl;
import com.cos.security1.domain.Article;
import com.cos.security1.domain.Copyright;
import com.cos.security1.repository.*;
import com.cos.security1.service.FileService;
import com.cos.security1.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.sql.Timestamp;

@Controller
public class DocumentController {

    @Autowired//스프링부트가 미리 생성한 객체를 자동연결
    private ArticleRepository articleRepository;

    @Autowired
    private CopyrightRepository copyrightRepository;

    @Autowired
    private FileService fileService;

    @GetMapping("/user/Documentcomplete")
    public String documentCompleteget()
    {
        return "redirect:/";
    }

    @PostMapping("/user/Documentcomplete")
    public String documentComplete(String title, Timestamp create_time, Model model )
    {
        Article tempArt = articleRepository.findByPapername(title);

        if(tempArt!=null && tempArt.getCreateDate().toString().equals(create_time.toString()))//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {
            if(tempArt.getOrig_name().contains(".pdf"))
                model.addAttribute("is_pdf","yes");

            model.addAttribute("real_paper_name", tempArt.getPapername().substring(tempArt.getPapername().indexOf("_")+1));

            model.addAttribute("paper_name", tempArt.getPapername());
            model.addAttribute("person_num", tempArt.getPeople_size());

            model.addAttribute("file_serverName", tempArt.getSer_fileName());
            model.addAttribute("orig_Name", tempArt.getOrig_name());
            //model.addAttribute("file_serPath", tempArt.getFile_path());
            model.addAttribute("create_date",tempArt.getCreateDate());

            model.addAttribute("person1_name",tempArt.getPeople1_name());
            model.addAttribute("person2_name",tempArt.getPeople2_name());
            model.addAttribute("person3_name",tempArt.getPeople3_name());

            model.addAttribute("person1_issign",tempArt.getPeople1_sign());
            model.addAttribute("person2_issign",tempArt.getPeople2_sign());
            model.addAttribute("person3_issign",tempArt.getPeople3_sign());

            model.addAttribute("person1_signtime",tempArt.getPeople1_time());
            model.addAttribute("person2_signtime",tempArt.getPeople2_time());
            model.addAttribute("person3_signtime",tempArt.getPeople3_time());

            model.addAttribute("person1_email",tempArt.getPeople1_email());
            model.addAttribute("person2_email",tempArt.getPeople2_email());
            model.addAttribute("person3_email",tempArt.getPeople3_email());

            model.addAttribute("uniquenum",tempArt.getUniquenum());
            model.addAttribute("peoplesize", tempArt.getPeople_size());
            model.addAttribute("signcount", tempArt.getSign_count());
            model.addAttribute("filesize", tempArt.getFile_size());
        }

        return "user/Documentcomplete";
    }

    @PostMapping("/user/Documentcomplete2")
    public String documentComplete2(String serialnum, Model model )
    {
        Article tempArt = articleRepository.findByUniquenum(serialnum);
        System.out.println("메인페이지에서 연결");
        if(tempArt!=null)//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {
            if(tempArt.getOrig_name().contains(".pdf"))
                model.addAttribute("is_pdf","yes");

            model.addAttribute("real_paper_name", tempArt.getPapername().substring(tempArt.getPapername().indexOf("_")+1));

            model.addAttribute("paper_name", tempArt.getPapername());
            model.addAttribute("person_num", tempArt.getPeople_size());

            model.addAttribute("file_serverName", tempArt.getSer_fileName());
            model.addAttribute("orig_Name", tempArt.getOrig_name());
            //model.addAttribute("file_serPath", tempArt.getFile_path());
            model.addAttribute("create_date",tempArt.getCreateDate());

            model.addAttribute("person1_name",tempArt.getPeople1_name());
            model.addAttribute("person2_name",tempArt.getPeople2_name());
            model.addAttribute("person3_name",tempArt.getPeople3_name());

            model.addAttribute("person1_issign",tempArt.getPeople1_sign());
            model.addAttribute("person2_issign",tempArt.getPeople2_sign());
            model.addAttribute("person3_issign",tempArt.getPeople3_sign());

            model.addAttribute("person1_signtime",tempArt.getPeople1_time());
            model.addAttribute("person2_signtime",tempArt.getPeople2_time());
            model.addAttribute("person3_signtime",tempArt.getPeople3_time());

            model.addAttribute("person1_email",tempArt.getPeople1_email());
            model.addAttribute("person2_email",tempArt.getPeople2_email());
            model.addAttribute("person3_email",tempArt.getPeople3_email());

            model.addAttribute("uniquenum",tempArt.getUniquenum());
            model.addAttribute("peoplesize", tempArt.getPeople_size());
            model.addAttribute("signcount", tempArt.getSign_count());
            model.addAttribute("filesize", tempArt.getFile_size());
        }

        return "user/Documentcomplete";
    }

    @PostMapping("/user/copyrightcomplete")
    public String copyrightComplete(String serialnum, Model model )
    {
        Copyright tempArt = copyrightRepository.findByUniquenum(serialnum);
        System.out.println("메인페이지에서 연결");
        if(tempArt!=null)//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {
            System.out.println("저작권 모델 추가");
            if(tempArt.getOrig_name().contains(".pdf"))
                model.addAttribute("is_pdf","yes");

            model.addAttribute("real_paper_name", tempArt.getPapername().substring(tempArt.getPapername().indexOf("_")+1));

            model.addAttribute("paper_name", tempArt.getPapername());
            model.addAttribute("person_num", tempArt.getPeople_size());

            model.addAttribute("file_serverName", tempArt.getSer_fileName());
            model.addAttribute("orig_Name", tempArt.getOrig_name());
            //model.addAttribute("file_serPath", tempArt.getFile_path());
            model.addAttribute("create_date",tempArt.getCreateDate());

            model.addAttribute("person1_name",tempArt.getPeople1_name());


            model.addAttribute("person1_issign",tempArt.getPeople1_sign());


            model.addAttribute("person1_signtime",tempArt.getPeople1_time());


            model.addAttribute("person1_email",tempArt.getPeople1_email());


            model.addAttribute("uniquenum",tempArt.getUniquenum());
            model.addAttribute("peoplesize", tempArt.getPeople_size());
            model.addAttribute("signcount", tempArt.getSign_count());
            model.addAttribute("filesize", tempArt.getFile_size());
        }

        return "user/Documentcomplete2";
    }

    @GetMapping("/user/DocumentcheckPage")
    public String docu( )
    {
        return "redirect:/";
    }

    //사인하러 가기
    @PostMapping("/user/DocumentcheckPage")
    public String documentCheckPage( String title, Timestamp create_time , Model model, HttpServletResponse response)//title에 계약서 이름이 담겨져 있음, create_time에 계약서 생성 시간 담김
    {
        //유저 정보 가져오기
        UserDetailsImpl principal= (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //계약서 이름으로 db에서 계약서 정보 찾기
        Article tempArt = articleRepository.findByPapername(title);

        boolean signCheck = false;
        if(tempArt != null)
        {

            String one = "1";
            System.out.println("계약 인원수 : " + tempArt.getPeople_size());
            switch (tempArt.getPeople_size()) {

                case 1:
                    if (one.equals(tempArt.getPeople1_sign())) {
                        System.out.println("1인 싸인 완료");
                        signCheck = true;

                    }
                    break;

                case 2:
                    System.out
                            .println("싸인여부 1 : " + tempArt.getPeople1_sign() + "  싸인 여부 2 : " + tempArt.getPeople2_sign());
                    if (one.equals(tempArt.getPeople1_sign()) && one.equals(tempArt.getPeople2_sign())) {
                        System.out.println("2인 싸인 완료");
                        signCheck = true;
                    }
                    break;

                case 3:
                    if (one.equals(tempArt.getPeople1_sign()) && one.equals(tempArt.getPeople2_sign())
                            && one.equals(tempArt.getPeople3_sign())) {
                        System.out.println("3인 싸인 완료");
                        signCheck = true;
                    }
            }
        }

        //서명을 다 한 상태라면 메인화면으로 전송, 혹은 db 손상되어 데이터 유실 시
        if(tempArt==null || signCheck)
        {
            if(signCheck)
                model.addAttribute("sign_complete","complete");

            else if(tempArt==null)
                model.addAttribute("sign_null","null");

            return "temp";
        }

        if(tempArt!=null && tempArt.getCreateDate().toString().equals(create_time.toString()))//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {
            System.out.println("계약서 찾음");
            boolean foundName = false;

            if(tempArt.getPeople1_name().equals(principal.getRealname()))
                foundName=true;

            else if(tempArt.getPeople2_name().equals(principal.getRealname()))
                foundName=true;

            else if(tempArt.getPeople3_name().equals(principal.getRealname()))
                foundName=true;

            if(foundName)//계약서에 해당 이름이 있다면
            {
                Resource tempResource =null;
                try {
                    //서버에 있는 파일 찾기
                    tempResource = fileServiceImpl.loadFile(tempArt.getSer_fileName());
                    System.out.println("파일 찾기 성공 : " + tempResource.getFilename());
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    System.out.println("파일을 찾을 수 없습니다.");
                }

                if(tempArt.getOrig_name().contains(".pdf"))
                    model.addAttribute("is_pdf","yes");


                model.addAttribute("paper_name", tempArt.getPapername());
                model.addAttribute("person_num", tempArt.getPeople_size());

                model.addAttribute("file_serverName", tempArt.getSer_fileName());
                model.addAttribute("orig_Name", tempArt.getOrig_name());
                //model.addAttribute("file_serPath", tempArt.getFile_path());
                model.addAttribute("create_date",tempArt.getCreateDate());

                model.addAttribute("person1_name",tempArt.getPeople1_name());
                model.addAttribute("person2_name",tempArt.getPeople2_name());
                model.addAttribute("person3_name",tempArt.getPeople3_name());

                model.addAttribute("person1_issign",tempArt.getPeople1_sign());
                model.addAttribute("person2_issign",tempArt.getPeople2_sign());
                model.addAttribute("person3_issign",tempArt.getPeople3_sign());

                return "user/DocumentcheckPage";
            }


        }
        //아니라면 홈페이지로 이동
        return "redirect:/";
    }

    @PostMapping("/user/DocumentcheckPage2")
    public String documentCheckPage2( String title, Timestamp create_time , Model model, HttpServletResponse response)//title에 계약서 이름이 담겨져 있음, create_time에 계약서 생성 시간 담김
    {
        //유저 정보 가져오기
        UserDetailsImpl principal= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //계약서 이름으로 db에서 계약서 정보 찾기
        Copyright tempArt = copyrightRepository.findByPapername(title);

        boolean signCheck = false;
        if(tempArt != null)
        {
            String one = "1";
            System.out.println("계약 인원수 : " + tempArt.getPeople_size());

            if (one.equals(tempArt.getPeople1_sign())) {
                System.out.println("1인 싸인 완료");
                signCheck = true;
            }

        }

        //서명을 다 한 상태라면 메인화면으로 전송, 혹은 db 손상되어 데이터 유실 시
        if(tempArt==null || signCheck)
        {
            if(signCheck)
                model.addAttribute("sign_complete","complete");

            else if(tempArt==null)
                model.addAttribute("sign_null","null");

            return "temp";
        }

        if(tempArt!=null && tempArt.getCreateDate().toString().equals(create_time.toString()))//해당 이름의 계약서가 있고, 계약서 생성시기도 일치할 때
        {
            System.out.println("계약서 찾음");
            boolean foundName = false;

            if(tempArt.getPeople1_name().equals(principal.getRealname()))
                foundName=true;


            if(foundName)//계약서에 해당 이름이 있다면
            {
                Resource tempResource =null;
                try {
                    //서버에 있는 파일 찾기
                    tempResource = fileServiceImpl.loadFile(tempArt.getSer_fileName());
                    System.out.println("파일 찾기 성공 : " + tempResource.getFilename());
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    System.out.println("파일을 찾을 수 없습니다.");
                }

                if(tempArt.getOrig_name().contains(".pdf"))
                    model.addAttribute("is_pdf","yes");


                model.addAttribute("paper_name", tempArt.getPapername());
                model.addAttribute("person_num", tempArt.getPeople_size());

                model.addAttribute("file_serverName", tempArt.getSer_fileName());
                model.addAttribute("orig_Name", tempArt.getOrig_name());
                //model.addAttribute("file_serPath", tempArt.getFile_path());
                model.addAttribute("create_date",tempArt.getCreateDate());

                model.addAttribute("person1_name",tempArt.getPeople1_name());

                model.addAttribute("person1_issign",tempArt.getPeople1_sign());


                return "user/DocumentcheckPage2";
            }
        }
        //아니라면 홈페이지로 이동
        return "redirect:/";
    }
}
