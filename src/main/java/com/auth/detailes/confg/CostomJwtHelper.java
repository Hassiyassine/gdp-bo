package com.auth.detailes.confg;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.business.repositories.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author 	: Yassine HASSI
 * @version : 1.0.0
 * Email	: yassine.ismail.hassi@gmail.com
 * Date	    : Apr 25, 2020
 */

@Component
public class CostomJwtHelper{

 
	@Value("${jwt.auth.app}")
	private String APP;

	@Value("${jwt.auth.secret_key}")
	private String SECRET_KEY;

	@Value("${jwt.auth.expires_in}")
	private int DATE_END;

	private SignatureAlgorithm ALGORITHM_SECRET = SignatureAlgorithm.HS256;
	@Autowired
	private  UserRepository repository;

	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public String generateToken(String username) throws InvalidKeySpecException, NoSuchAlgorithmException {
		Optional<User> auth = this.repository.findByUserName(username);
		return Jwts.builder()
				.setIssuer(APP)
				.setSubject(username)
				.setIssuedAt(new Date())
				.claim("firstname",auth.get().getFirstName())
				.claim("lastname",auth.get().getLastName())
				.claim("id",auth.get().getId())
				.claim("permissions",auth.get().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setExpiration(generateExpirationDate()).signWith(ALGORITHM_SECRET, SECRET_KEY).compact();
	}

	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + DATE_END * 1000);
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		Date expireDate = getExpirationDate(token);
		return expireDate.before(new Date());
	}

	private Date getExpirationDate(String token) {
		Date expireDate;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expireDate = claims.getExpiration();
		} catch (Exception e) {
			expireDate = null;
		}
		return expireDate;
	}

	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}

	public String getToken(HttpServletRequest request) {

		String authHeader = getAuthHeaderFromHeader(request);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}

	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

}
