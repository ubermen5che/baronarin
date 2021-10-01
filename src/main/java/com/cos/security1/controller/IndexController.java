package com.cos.security1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Sort;

import com.cos.security1.domain.CustomerCenter;
import com.cos.security1.domain.Post;
import com.cos.security1.service.impl.FileServiceImpl;
import com.cos.security1.domain.User;
import com.cos.security1.repository.PostRepository;
import com.cos.security1.repository.CustomerCenterRepository;
import com.cos.security1.repository.UserRepository;
//import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;


//RSA
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.commons.codec.binary.Base64;
import java.util.Base64;
import java.util.Date;
import java.nio.charset.*;



//인증이 안된 사용자들이 출입할 경로를 /auth/** 허용
//그냥 주소가 /이면 index.jsp허용

// /js /css /image 허

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
    private FileServiceImpl fileServiceImpl;
	
	//localhost8090
	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)//@GetMapping({"","/"})
	public String index()
	{
		//Mustache 기본폴더 src/main/resources
		//뷰리졸버 설정 templates(prefix), mustache(suffix) 생략가능 
		return "index";//src/main/resources/templates/index.mustache
	}

	@GetMapping("/popup")
	public String popUp()
	{
		return "popup";
	}

	@GetMapping("/admin")
	public @ResponseBody String admin()
	{
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager()
	{
		return "manager";
	}

	@GetMapping(value = "/image/{imagename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)//, produces = MediaType.IMAGE_JPEG_VALUE)//전송하는 스트림이 이미지임을 명시
	public ResponseEntity<byte[]> userSearch(@PathVariable("imagename") String imagename) throws IOException
	{ 
		System.out.println("서버에서 보낼 파일 경로 : "+ fileServiceImpl.getUpDownloadDir() +File.separator + imagename);//
		
		InputStream imageStream = new FileInputStream(fileServiceImpl.getUpDownloadDir() +File.separator + imagename);//
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/pdfdown/{id}")
	public ResponseEntity<byte[]> getPdf(@PathVariable("id") String id) throws IOException {
		System.out.println("서버에서 보낼 파일 경로 : "+ fileServiceImpl.getUpDownloadDir() +File.separator + id);//
	    final String filePath = fileServiceImpl.getUpDownloadDir() +File.separator +id;
	    final byte[] pdfBytes = Files.readAllBytes(Paths.get(filePath));
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    headers.setContentDispositionFormData("attachment", null);
	    headers.setCacheControl("no-cache");
	    
	    ResponseEntity<byte[]> file = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	    return file;
	}
	
	@GetMapping(value = "/pdfview/{id}")
    public ResponseEntity<InputStreamResource> getTermsConditions(@PathVariable("id") String id) throws FileNotFoundException {

		System.out.println("파일명 : "+id );
		
        String filePath = fileServiceImpl.getUpDownloadDir() +File.separator +id;
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();      
        headers.add("content-disposition", "inline;filename=" +id);
        
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
	
	//서명 결과물 보기
	@GetMapping(value = "/pdfreview/{id}")
    public ResponseEntity<InputStreamResource> getResult(@PathVariable("id") String id) throws FileNotFoundException {

		System.out.println("파일명 : "+id );
		
        String filePath = fileServiceImpl.getUpResultDir() +File.separator +id;
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();      
        headers.add("content-disposition", "inline;filename=" +id);
        
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

	@GetMapping(value = "/showsign/{id}")
    public ResponseEntity<InputStreamResource> getSign(@PathVariable("id") String id) throws FileNotFoundException {

		System.out.println("파일명 : "+id );
		
        String filePath = fileServiceImpl.getUpDownloadDir() +File.separator+"userSign"+File.separator +id;
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();      
        headers.add("content-disposition", "inline;filename=" +id);
        
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

	//SecurityConfig 작성후엔 스프링 시큐리티가 자동으로 안잡아줌
    @RequestMapping(value = {"/loginForm"}, method = RequestMethod.GET)
	public String loginform(HttpServletRequest req)
	{
		return "loginForm";
	}
	
	@PostMapping("/findUserId")
	public String findUserId()
	{
		return "redirect:/joinForm";
		
	}
	
	@RequestMapping(value = {"/joinForm"}, method = RequestMethod.GET)
	public String joinForm(Model model)
	{
		return "joinFormRetry";
	}

	//아이디 중복체크 RedirectAttributes는 redirect: 리턴 시 쓸 수 있음
	@RequestMapping(value = {"/dupname"}, method = RequestMethod.POST)
	public String dupname(String checkname, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
		//이메일 형식인지 검사
		boolean err = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(checkname);
		if(m.matches()) { err = true; }
		
		//이메일 형식일 경우
		if(err)
				
			//해당 아이디가 없다면,
			//빈 값이 아닐 경우
			if(checkname.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "")!=null && userRepository.findByUsername(checkname)==null)
				{
				System.out.println("해당이름 사용가능 : "+checkname.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "")+", "+request.getAttribute("checkName"));
				
				redirectAttributes.addFlashAttribute("checkSuccess",1);  
				}
			else
				{

				redirectAttributes.addFlashAttribute("checkSuccess",-1); 
				}
		else
		{
			redirectAttributes.addFlashAttribute("checkSuccess",-2); 
		}
		redirectAttributes.addFlashAttribute("checkName",checkname);
		String referer = request.getHeader("Referer");
		return "redirect:"+referer;
	}
	
	 @ResponseBody
	    @RequestMapping(value="/idCheck", method=RequestMethod.POST)
	    public int IdCheck(@RequestBody String memberId) throws Exception {
		
			boolean err = false;
			String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(memberId);
			
			if (m.matches()) {
				err = true;
			}

			int count = -1;
			
			if(err)
			{
				if (userRepository.findByUsername(memberId) == null) {
					count = 0;
				}
				else {
					count = 1;
				}
			}
			return count; 
	    }

	@PostMapping("/join")//GetMapping이 post지원 안해준다해서
	public String join(User user, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//String test="평문";
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
			
			userRepository.save(user);//회원가입됨

			return "redirect:/loginForm";//redirect는 loginForm함수 호출
	}
}
