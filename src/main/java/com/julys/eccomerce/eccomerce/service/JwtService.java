package com.julys.eccomerce.eccomerce.service;

import java.security.Key;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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

public class JwtService {

  private static final String PRIVATE_KEY = "acacac";

  private static final int EXPIRATION_TIME = 86400000;

  public String extractUsername(String jwt) {

    return null;
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

    final Claims claims = extractAllClaims(token);

    return claimsResolver.apply(claims);

  }

  private Claims extractAllClaims(String jwt) {
    return Jwts.parser().keyLocator(new Locator<Key>() {

      @Override
      public Key locate(Header header) {
        if (header instanceof JwsHeader) {
          return getSignatureVerificationKey(((JwsHeader) header).getAlgorithm());

        }

        return getDecriptionKey((JweHeader) header);
      }

    }).build().parseSignedClaims(jwt).getPayload();
  }

  private Key getDecriptionKey(JweHeader header) {
    try {
      byte[] keyBytes = Decoders.BASE64.decode(PRIVATE_KEY);

      SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

      return secretKey;

    } catch (IllegalArgumentException | SignatureException | WeakKeyException e) {
      // TODO: handle exception

      e.printStackTrace();
      return null;

    }
  }

  private Key getSignatureVerificationKey(String algorithm) {
    try {
      byte[] keyBytes = Decoders.BASE64.decode(PRIVATE_KEY);
      SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

      return secretKey;
    } catch (IllegalArgumentException e) {
      // TODO: handle exception
      e.printStackTrace();
      return null;
    }
  }
}