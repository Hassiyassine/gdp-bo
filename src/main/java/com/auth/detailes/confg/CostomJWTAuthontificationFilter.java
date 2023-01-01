package com.auth.detailes.confg;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth.detailes.service.auth.CustomUserDetailService;


/**
 * @author : Yassine HASSI
 * @version : 1.0.0
 * Email : yassine.ismail.hassi@gmail.com
 * Date : Apr 25, 2020
 */


public class CostomJWTAuthontificationFilter extends OncePerRequestFilter {

	private CustomUserDetailService customUserDetailService;
	private CostomJwtHelper jwt;
	
	public CostomJWTAuthontificationFilter(CustomUserDetailService customUserDetailService,CostomJwtHelper jwt) {
		this.customUserDetailService=customUserDetailService;
		this.jwt=jwt;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String TOKEN = jwt.getToken(request);

		if (StringUtils.hasText(TOKEN)) {
			String USER_NAME = jwt.getUsernameFromToken(TOKEN);

			if (null!=USER_NAME) {
				UserDetails user = customUserDetailService.loadUserByUsername(USER_NAME);

				if (jwt.validateToken(TOKEN, user)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							user, null, user.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}

		}

		filterChain.doFilter(request, response);

	}

}
