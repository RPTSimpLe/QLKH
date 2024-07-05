package com.DoAn.f88.controller;

import com.DoAn.f88.utils.AppStringUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
public class AuthController {
	
	@GetMapping("login")
	public String login(
			@RequestParam(value = "error", defaultValue = "false") Boolean error,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "redirectUrl", required = false) String redirectUrl,
			Model model,
			HttpServletResponse servletResponse
			) throws IOException {
		model.addAttribute("error", error);
		model.addAttribute("message", message);

		SecurityContext context = SecurityContextHolder.getContext();

		Authentication authentication = context.getAuthentication();
		if (!authentication.getName().equals("anonymousUser")) {
			if (AppStringUtils.hasText(redirectUrl)) {
				servletResponse.sendRedirect(redirectUrl);
			}
			servletResponse.sendRedirect("/");
		}
		
		return "login";
	}
}
