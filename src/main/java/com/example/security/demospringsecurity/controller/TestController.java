package com.example.security.demospringsecurity.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/hello")
	public String getHello(){
		return "hello";
	}
	
	@GetMapping("/hello2")
	public String printUserDetails(@AuthenticationPrincipal final UserDetails details){
		Collection<? extends GrantedAuthority> authorities = details.getAuthorities();
		authorities.forEach(System.out::println);
		return details.getUsername();
	}
	
}
