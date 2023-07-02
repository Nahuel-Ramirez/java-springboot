package com.springcurso.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class Base64Util {
	@Value("${security.jwt.secret}")
	private String key;

	@Value("${security.jwt.issuer}")
	private String issuer;

	@Value("${security.jwt.ttlMillis}")
	private long ttlMillis;

	private final Logger log = LoggerFactory.getLogger(Base64Util.class);

	public String create(String id, String subject) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] apiKeySecretBytes = Base64.getEncoder().encode(key.getBytes(StandardCharsets.UTF_8));

		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, new String(apiKeySecretBytes, StandardCharsets.UTF_8));

		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	public String getValue(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(new String(Base64.getEncoder().encode(key.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8))
				.parseClaimsJws(jwt).getBody();

		return claims.getSubject();
	}

	public String getKey(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(new String(Base64.getEncoder().encode(key.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8))
				.parseClaimsJws(jwt).getBody();

		return claims.getId();
	}
}
