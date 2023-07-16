package com.lawencon.lmssanto.service.impl;

import java.security.KeyPair;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lawencon.lmssanto.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{

	private final KeyPair key = Keys.keyPairFor(SignatureAlgorithm.RS256);
	
	@Override
	public String generateJwt(final Map<String, Object> claims) {
		final String jwt = Jwts.builder()
				.setClaims(claims)
				.signWith(key.getPrivate())
				.compact();
		
		return jwt;
	}

	@Override
	public Map<String, Object> parseJwt(final String jwt) {
		return Jwts.parserBuilder()
				.setSigningKey(key.getPublic())
				.build()
				.parseClaimsJws(jwt)
				.getBody();
	}

}
