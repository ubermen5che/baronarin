package com.cos.security1.controller;

import com.cos.security1.config.auth.UserDetailsImpl;
import com.cos.security1.domain.CustomerCenter;
import com.cos.security1.repository.*;
import com.cos.security1.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class CustomerCenterController {

    @Autowired
    private CustomerCenterRepository customerCenterRepository;

    @Autowired
    private FileService fileServiceImpl;

    //게시글 찾기
    @GetMapping("/customerCenter")
    public String editSearch(@PageableDefault(size=5, sort="id",direction = Sort.Direction.DESC) Pageable pageable, ModelMap model)
    {
        Page<CustomerCenter> pagelist = customerCenterRepository.findAll(pageable);

        int pageNumber=pagelist.getPageable().getPageNumber(); //현재페이지
        int totalPages=pagelist.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..
        int pageBlock = 5; //블럭의 수 1, 2, 3, 4, 5
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*5+1=6
        int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.

        endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;
        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("postlist", pagelist);

        return "customerCenter";
    }

    @GetMapping(value = "/customerDown/{id}")
    public ResponseEntity<InputStreamResource> getCustomer(@PathVariable("id") String id) throws FileNotFoundException, UnsupportedEncodingException {

        CustomerCenter customerCenter = customerCenterRepository.findById(Long.parseLong(id));
        System.out.println("파일명 : "+ customerCenter.getFilename() );
        String encordedFilename = URLEncoder.encode(customerCenter.getFilename(),"UTF-8").replace("+", "%20");
        String filePath = fileServiceImpl.getUpCustomerCenter() +File.separator + customerCenter.getServer_filename();
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment;filename=" +encordedFilename);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        if(id.indexOf(".pdf")!=-1)
        {
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        }
        else
        {
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }

    }

    @ResponseBody
    @RequestMapping(value="/checkCustomerPass", method= {RequestMethod.GET})
    public String checkCustomerPass(@RequestParam("id") String id, @RequestParam("pass") String pass)
    {
        //System.out.println("id : "+id + " , pass : "+pass);
        CustomerCenter customerCenter = customerCenterRepository.findById(Long.parseLong(id));
        String success="failed";

        if(customerCenter.getSecretpassword()!=null && customerCenter.getSecretpassword().equals(pass))
        {
            success="success";
            return success;
        }

        return success;
    }

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
                File delfile = new File(fileServiceImpl.getUpCustomerCenter() + File.separator + cos.getServer_filename());
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
            fileServiceImpl.fileCustomerCenterUpload(file, query.getServer_filename());
        }
        else
            query = new CustomerCenter( title, textbody, principal.getUsername(),principal.getRealname(), null, secret, secretpassword, null,null);

        customerCenterRepository.save(query);

        System.out.println(query.getId()+"\n"+ title+"\n"+textbody);

        redirect.addAttribute("customerType",query.getId());

        return "redirect:/customerCenter";
    }
}
