package com.auth.detailes.confg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 
 * @author Yassine HASSI  [ SIS Consultants ]
 * Email: yassine.ismail.hassi@gmail.com 
 *
 */

@Configuration
public class CorsConfig {
	
	private static final List<String> URL_FRONT_END = Arrays.asList(
		    "http://localhost:4200","http://localhost:5000",
			"http://127.0.0.1:4200","http://127.0.0.1:5000"
	);
	
	/**
	 * Configuration cross origin
	 * @return FilterRegistrationBean
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.setAllowedOrigins(URL_FRONT_END);
	    config.setAllowedMethods(Collections.singletonList("*"));
	    config.setAllowedHeaders(Collections.singletonList("*"));
		config.setExposedHeaders(Collections.singletonList("Content-Disposition"));
	    source.registerCorsConfiguration("/**", config);
	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	}

	@Bean
	public  BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

	@Bean
	public Gson gson(){
		return new Gson();
	}
}
