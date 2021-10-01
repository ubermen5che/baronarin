package com.cos.security1.controller;

import com.cos.security1.config.auth.UserDetailsImpl;
import com.cos.security1.domain.Post;
import com.cos.security1.repository.PostRepository;
import com.cos.security1.service.FileService;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class BoardController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FileService fileServiceImpl;

    //자료실
    @GetMapping("/board")
    public String board(@PageableDefault(size=5, sort="id",direction = Sort.Direction.DESC) Pageable pageable, ModelMap model,
                        @RequestParam(required = false, defaultValue = "-1")String postType)
    {
        Page<Post> pagelist = postRepository.findAll(pageable);

        if(postType!=null && postType!="-1")
        {
            switch(Integer.parseInt(postType))
            {
                case 1:
                    pagelist = postRepository.findByPostTypeContaining("근로", pageable);
                    break;
                case 2:
                    pagelist = postRepository.findByPostTypeContaining("구매", pageable);
                    break;
                case 3:
                    pagelist = postRepository.findByPostTypeContaining("가맹", pageable);
                    break;
                case 4:
                    pagelist = postRepository.findByPostTypeContaining("보험", pageable);
                    break;
                case 5:
                    pagelist = postRepository.findByPostTypeContaining("금융", pageable);
                    break;
                case 6:
                    pagelist = postRepository.findByPostTypeContaining("렌탈", pageable);
                    break;
                case 7:
                    pagelist = postRepository.findByPostTypeContaining("기타", pageable);
            }
        }

        int pageNumber=pagelist.getPageable().getPageNumber(); //현재페이지
        int totalPages=pagelist.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..
        int pageBlock = 5; //블럭의 수 1, 2, 3, 4, 5
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*5+1=6
        int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.

        endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;
        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("postlist", pagelist);
        return "boardForm";
    }

    @PostMapping("/boardUp")
    public String boardUp(String postType, String special, String title,
                          @RequestParam("hwp_file") MultipartFile hwp_file, @RequestParam("pdf_file") MultipartFile pdf_file)
    {
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
        Date time = new Date();
        String time1 = format1.format(time);
        String servername = time1 + UUID.randomUUID().toString().replaceAll("-", "");

        Post post = new Post(postType, special, title, servername+".pdf", servername+".hwp", pdf_file.getOriginalFilename(), hwp_file.getOriginalFilename());
        postRepository.save(post);

        fileServiceImpl.fileBoardUpload(pdf_file, servername + ".pdf");
        fileServiceImpl.fileBoardUpload(hwp_file, servername+".hwp");

        return "redirect:/board";
    }

    @GetMapping(value = "/boardDown/{id}")
    public ResponseEntity<InputStreamResource> getBoard(@PathVariable("id") String id) throws FileNotFoundException, UnsupportedEncodingException {

        Post temppost;
        System.out.println("파일명 : "+id );

        if(id==null)
            return null;

        String encordedFilename;
        String filePath;

        if(id.contains(".hwp"))
        {
            temppost = postRepository.findByServerhwp(id);
            filePath = fileServiceImpl.getUpBoard() + File.separator +temppost.getServerhwp();
            encordedFilename = URLEncoder.encode(temppost.getHwpLink(),"UTF-8").replace("+", "%20");
        }
        else
        {
            temppost = postRepository.findByServerpdf(id);
            filePath = fileServiceImpl.getUpBoard() +File.separator +temppost.getServerpdf();
            encordedFilename = URLEncoder.encode(temppost.getPdfLink(),"UTF-8").replace("+", "%20");
        }

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
