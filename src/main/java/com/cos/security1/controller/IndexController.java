package com.cos.security1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.auth.AbstractFirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cos.security1.service.impl.FileServiceImpl;
import com.cos.security1.domain.User;
import com.cos.security1.repository.UserRepository;
//import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;


//RSA
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.commons.codec.binary.Base64;
import java.util.Base64;
import java.nio.charset.*;



//인증이 안된 사용자들이 출입할 경로를 /auth/** 허용
//그냥 주소가 /이면 index.jsp허용

// /js /css /image 허

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private FileServiceImpl fileServiceImpl;

	@Autowired
	private FirebaseAuth firebaseAuth;
	
	//localhost8090
	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)//@GetMapping({"","/"})
	public String index()
	{
		//Mustache 기본폴더 src/main/resources
		//뷰리졸버 설정 templates(prefix), mustache(suffix) 생략가능 
		return "newindex";//src/main/resources/templates/index.mustache
	}

	@GetMapping("/popup")
	public String popUp()
	{
		return "popup";
	}
	@GetMapping("/ChkTelPopUpPage")
	public String ChkTelPopUpPage()
	{
		return "ChkTelPopUpPage";
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

//	ChkTelPopUpPage에서 AJAX로 요청 : 파이어베이스 로그인 token이 헤더로 날라옴
//	받은 토큰으로 uid를 파이어베이스로 부터 받아온다
	@GetMapping("/fb")
	@ResponseBody
	public JSONObject fb(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		JSONObject res = new JSONObject();
		FirebaseToken decodedToken;
		String header = request.getHeader("Authorization");
		System.out.println(header);
		try{
			decodedToken = firebaseAuth.verifyIdToken(header);
			System.out.println("decodedToken = " + decodedToken);
			if (decodedToken != null){
				res.put("res", "true");
				return res;
			}
		} catch (Exception e) {
//            setUnauthorizedResponse(response, "INVALID_TOKEN");
			res.put("res", "false");
            return res;
        }
		res.put("res", "false");
		return res;
	}
}
