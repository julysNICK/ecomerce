package com.julys.eccomerce.eccomerce.service;

import java.security.Key;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.Jwts.KEY;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import lombok.var;

@Component
public class JwtService {

  private static final String PRIVATE_KEY = "fhMCgW2pur8g1FkFq0k5WdMUtUuPttUdC6p6lVVI1JP05IyBcNXxJih819ujFXcmOLi3AG0xS+iOrvL5K03RwlVkAzqr7DrSPdUxshI+H3U9PSiY6Poi6tPj/Njyrm7TNmZqlbjLQ4yICWlApWhX2kXqlmzmfCfBseU4S01juwUiMTuPxWhHhXKBOlesA01qF+zMj3v/Z+bewe0f20yjR9lp5MqV8UG9lrlHtCUmLJE8nn/GGipru8WrFGS1995p4FmRBiNgSqMjzyLZMFXqf1vL90Modxw8h3A37+cTTgJJNYdWrTnTIXS3xszPeiXRkimwv/OvdsNNM9ZG8Tx/h3O6AwObb/paKLHl4QQQG5c=";

  private static final int EXPIRATION_TIME = 86400000;

  public String extractUsername(String jwt) {

    return extractClaim(jwt, Claims::getSubject);

  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

    final Claims claims = extractAllClaims(token);

    return claimsResolver.apply(claims);

  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails) {
    return Jwts.builder().claims(extraClaims).subject(userDetails.getUsername())
        .issuedAt(new java.util.Date(System.currentTimeMillis()))
        .expiration(new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(getSignatureVerificationKey()).compact();
  }

  private Claims extractAllClaims(String jwt) {
    return Jwts.parser().keyLocator(new Locator<Key>() {

      @Override
      public Key locate(Header header) {
        if (header instanceof JwsHeader) {
          return getSignatureVerificationKey();

        }

        return getDecriptionKey((JweHeader) header);
      }

    }).build().parseSignedClaims(jwt).getPayload();
  }

  public Boolean isTokenValid(String jwt, UserDetails userDetails) {
    final String username = extractUsername(jwt);

    return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
  }

  private boolean isTokenExpired(String jwt) {
    return extractExpiration(jwt).before(new java.util.Date());
  }

  private Date extractExpiration(String jwt) {
    return extractClaim(jwt, Claims::getExpiration);
  }

  private Key getDecriptionKey(JweHeader header) {
    try {
      byte[] keyBytes = Decoders.BASE64.decode(PRIVATE_KEY);

      SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

      return secretKey;

    } catch (IllegalArgumentException | SignatureException | WeakKeyException e) {

      e.printStackTrace();
      return null;

    }
  }

  private Key getSignatureVerificationKey() {
    try {
      byte[] keyBytes = Decoders.BASE64.decode(PRIVATE_KEY);
      SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

      return secretKey;
    } catch (IllegalArgumentException e) {
      System.out.println("JWT token compact of handler are invalid.");
      e.printStackTrace();
      return null;
    }
  }
}