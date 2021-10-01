package com.bludots.app.rgm.password.registration.services.impl.token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class WebToken {
		public String generateToken(String request) {
			Instant now = Instant.now();
			byte[] secret = Base64.getDecoder().decode("WNi3oF3NfduzvwUiOPlnDdUUjIlMcv7fX28ms3udpPM=");
			String jwt = Jwts.builder().setSubject(request).setAudience("video demo")
					.claim("1d20", new Random().nextInt(20) + 1).setIssuedAt(Date.from(now))
					.setExpiration(Date.from(now.plus(10, ChronoUnit.MINUTES))).signWith(Keys.hmacShaKeyFor(secret))
					.compact();
			
			System.out.println(jwt);
			
			@SuppressWarnings({ "unused", "deprecation" })
			Jws<Claims> result = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret)).parseClaimsJws(jwt);
			
		return jwt;
		}
	}

