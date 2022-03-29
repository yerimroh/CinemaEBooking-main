package com.example.CinemaEBooking.model.securityConfig;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)  
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
		return new LoginSuccessHandler();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests()
			.antMatchers("/", "/login", "/registration", "/process_register", "/registrationConfirmation", "/reset_password", "/new_password").permitAll()
            .antMatchers("/css/**", "/assets/**", "/thumbnail/**").permitAll() // load css
			.antMatchers("/user/**").hasAuthority("CUSTOMER") // url with /user/ can only be accessed by registered user
			.antMatchers("/admin/**").hasAuthority("ADMIN") // url with /admin/ can only be accessed by admin
			.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.successHandler(myAuthenticationSuccessHandler())
					.failureUrl("/login?error=true")
					.permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/forbidden")
			.and()
				.logout()
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID", "remember-me")
				.invalidateHttpSession(true);
    
	}

    

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**");
}




}

