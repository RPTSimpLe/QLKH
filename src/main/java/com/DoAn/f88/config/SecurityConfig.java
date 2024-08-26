package com.DoAn.f88.config;

import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error500.LogicException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.NoOpAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Enable CORS
				.authorizeHttpRequests(http -> http
				.requestMatchers("/admin**").hasAnyAuthority("ADMIN")
				.requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
								.requestMatchers(				"/employee/**").hasAnyAuthority( "E_MANAGER")
				.requestMatchers("/login","/index.js","/auth/**","/css/**","/img/**","/fonts/**","/js/**","/scss/**","/api/api.js").permitAll()
				.requestMatchers("/","/api/v1/**").permitAll()
				.anyRequest().authenticated()
				).logout(httpLogout -> httpLogout
						.logoutUrl("/logout")
						.logoutSuccessUrl("http://localhost:3000/login"))
//				.logoutSuccessUrl("/"))
				.formLogin(httpLogin ->
			    httpLogin.loginPage("/login")
						.usernameParameter("username")
						.passwordParameter("password")
						.permitAll()
			        .failureHandler(authenticationFailureHandler())
			        .permitAll()
			        .successHandler((request, response, authentication) -> {
						String redirectUrl = null;
						for (GrantedAuthority authority : authentication.getAuthorities()) {
							if (authority.getAuthority().equals("ADMIN")) {
								redirectUrl = "http://localhost:8080/admin";
								break;
							}
						}
						redirectUrl = redirectUrl != null ? redirectUrl : "http://localhost:8080/";

						response.setContentType("application/json");
						PrintWriter out = response.getWriter();
						out.print(redirectUrl);
						out.flush();
			        }))
				.rememberMe(
						rememberMe -> rememberMe.rememberMeServices(rememberMeServices(userDetailsService)
						))
				.userDetailsService(userDetailsService)
				.httpBasic()
				.and()
				.build();
	}

	@Bean
	RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
	RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
	TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("myKey", userDetailsService,
		encodingAlgorithm);
	rememberMe.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
	return rememberMe;
	}

	@Bean
	public LayoutDialect layoutDialect() {
	  return new LayoutDialect();
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(
					HttpServletRequest request,
					HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print(exception.getMessage());
				out.flush();
			}
		};
	}

	@Bean
	UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:3000");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
