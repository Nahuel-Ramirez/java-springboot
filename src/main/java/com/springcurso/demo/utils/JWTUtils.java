package com.springcurso.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * @author Mahesh
 */
@Component
public class JWTUtils {
	@Value("${security.jwt.secret}")
	private String key;

	@Value("${security.jwt.issuer}")
	private String issuer;

	@Value("${security.jwt.ttlMillis}")
	private long ttlMillis;

	private final Logger log = LoggerFactory.getLogger(JWTUtils.class);

	/**
	 * Create a new token.
	 *
	 * @param id
	 * @param subject
	 * @return
	 */
	public String create(String id, String subject) {

		// The JWT signature algorithm used to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// sign JWT with our ApiKey secret
		byte[] apiKeySecretBytes = Base64.getDecoder().decode(key);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	/**
	 * Method to validate and read the JWT
	 *
	 * @param jwt
	 * @return
	 */
	public String getValue(String jwt) {
		// This line will throw an exception if it is not a signed JWS (as
		// expected)
		
		byte[] decodedKey = Base64.getDecoder().decode(key);
		Claims claims = Jwts.parser().setSigningKey(decodedKey).parseClaimsJws(jwt).getBody();
		return claims.getSubject();
		
//        Claims claims = Jwts.parser().setSigningKey(Base64.getDecoder().decode(key))
//                .parseClaimsJws(jwt).getBody();
//
//        return claims.getSubject();
	}

	/**
	 * Method to validate and read the JWT
	 *
	 * @param jwt
	 * @return
	 */
	public String getKey(String jwt) {
		// This line will throw an exception if it is not a signed JWS (as
		// expected)
		
		byte[] decodedKey = Base64.getDecoder().decode(key);
		System.out.println(decodedKey + "JWT ENCODED");
		Claims claims = Jwts.parser().setSigningKey(decodedKey).parseClaimsJws(jwt).getBody();
		return claims.getId();
		
//        Claims claims = Jwts.parser().setSigningKey(Base64.getDecoder().decode(key))
//                .parseClaimsJws(jwt).getBody();
//
//        return claims.getId();
	}
}
