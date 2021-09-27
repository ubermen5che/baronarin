package com.cos.security1.controller;

import com.cos.security1.config.auth.UserDetailsImpl;
import com.cos.security1.domain.CustomerCenter;
import com.cos.security1.repository.*;
import com.cos.security1.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class CustomerCenterController {

    @Autowired
    private CustomerCenterRepository customerCenterRepository;

    @Autowired
    private FileService fileService;

    @PostMapping("/user/customerShowpost")
    public String showPost(@RequestParam("id") String id, Model model)
    {
        UserDetailsImpl principal= (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CustomerCenter customerCenter = customerCenterRepository.findById(Long.parseLong(id));

        //파일 존재할 때
        if(customerCenter.getFilename()!=null)
        {
            model.addAttribute("filename", customerCenter.getFilename());
        }
        //답변이 있을 시
        if(customerCenter.getAdmincomment()!=null)
        {
            model.addAttribute("admin_comment", customerCenter.getAdmincomment());
        }
        model.addAttribute("post_id", customerCenter.getId());
        model.addAttribute("create_time", customerCenter.getCreateDate());
        model.addAttribute("user_realname", customerCenter.getRealname());
        model.addAttribute("title", customerCenter.getTitle());
        model.addAttribute("textbody", customerCenter.getTextbody());
        return "user/customerShowpost";
    }

    @PostMapping("/user/deleteShowpost")
    public String deletepost(@RequestParam("id") String id, Model model)
    {
        UserDetailsImpl principal= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal!=null && principal.getRole().equals("ROLE_ADMIN"))
        {
            CustomerCenter cos = customerCenterRepository.findById(Long.parseLong(id));

            if(cos.getServer_filename() != null && cos.getServer_filename().length() >= 1)
            {
                File delfile = new File(fileService.getUpCustomerCenter() + File.separator + cos.getServer_filename());
                delfile.delete();
                System.out.println("게시글 파일 삭제");
            }
            System.out.println("삭제할 게시글 id : "+id);
            customerCenterRepository.deleteById(Long.parseLong(id));
            //파일 존재할 때
            System.out.println("게시글 삭제");
        }

        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/user/customerComment")
    public int customerComment(@RequestParam(value="text", required=false) String text,@RequestParam("id") String id, RedirectAttributes redirectAttributes)
    {
        int result=0;

        if(id!=null)
        {
            redirectAttributes.addAttribute("admin_comment",text);

            CustomerCenter customerCenter = customerCenterRepository.findById(Long.parseLong(id));
            customerCenter.setAdmincomment(text);
            customerCenterRepository.save(customerCenter);
            System.out.println("text : "+text+"\nid : "+id);
            result=1;
        }

        return result;
    }

    @PostMapping("/user/editUpload")
    public String editUpload(Model model, String title, String textbody, String secret, String secretpassword, @RequestParam(value = "uploadfile",required = false) MultipartFile file, String checkfile, RedirectAttributes redirect)
    {
        UserDetailsImpl principal= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerCenter query;
        if(checkfile.equals("yes"))
        {
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
            Date time = new Date();
            String time1 = format1.format(time);
            String servername = time1 + UUID.randomUUID().toString().replaceAll("-", "");
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            query = new CustomerCenter( title, textbody, principal.getUsername(), principal.getRealname(),null, secret, secretpassword, file.getOriginalFilename(),servername + "."+ extension);
            fileService.fileCustomerCenterUpload(file, query.getServer_filename());
        }
        else
            query = new CustomerCenter( title, textbody, principal.getUsername(),principal.getRealname(), null, secret, secretpassword, null,null);

        customerCenterRepository.save(query);

        System.out.println(query.getId()+"\n"+ title+"\n"+textbody);

        redirect.addAttribute("customerType",query.getId());

        return "redirect:/customerCenter";
    }
}
