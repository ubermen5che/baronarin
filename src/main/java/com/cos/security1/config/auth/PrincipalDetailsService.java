package com.cos.security1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security1.domain.User;
import com.cos.security1.repository.UserRepository;



//시큐리티 설정에서 
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IOC되어있는 loadUserByUsername가 자동실행


@Service//메모리에 서비스로 띄울것, IOC등록
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//<input type="text" name="username" placeholder="Username"/><br/>에서 name="username"과
		//파라미터 이름이 다르면 동작 안함, username으로 쓸것
		String email = username;

		User userEntity = userRepository.findByEmail(email);//jpa query method
		//유저 이름으로 찾아봄
		System.out.println("userEntity = " + userEntity);
		if(userEntity != null && userEntity.getAuthStatus().equals("Y"))//아이디가 존재하고 Auth가 유효하면
		{
			return new UserDetailsImpl(userEntity);
		} else{
			return new UserDetailsImpl(new User());
		}
	}

}
