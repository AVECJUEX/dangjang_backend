package com.semi.dangjang.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.semi.dangjang.member.service.MemberServiceImpl;

import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MemberServiceImpl memberService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// �������
		http.csrf().disable().authorizeRequests().mvcMatchers("/**", "/member/**", "/item/**", "/images/**", "/fileupload/**").permitAll()
				.mvcMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated();

		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.userDetailsService(memberService) .passwordEncoder(passwordEncoder());
		 */
		auth.inMemoryAuthentication().withUser("postman").password("{noop}postman").roles("USER");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}

}