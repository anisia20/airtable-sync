package com.gomdoc.component;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import lombok.extern.jbosslog.JBossLog;

@Service
@JBossLog
public class JwtCmd {

	private final static String jwtSecretKey = "gomdoc_jwt_123!@#";
	private final static String jwtSecreKeyLocation = "/private_key.pem";

	private Key getHmacKey() {
		Key key = null;
		try {
			key = new HmacKey(jwtSecretKey.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return key;
	}
	
	private Key getHmacKey(String keyString) {
		Key key = null;
		try {
			key = new HmacKey(keyString.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return key;
	}

	public String generateToken(String jwtkey, float expireMin, String id, String jwtIss, String jwtAudience ) {
		JwtClaims claims = new JwtClaims();
		claims.setExpirationTimeMinutesInTheFuture(expireMin);
		claims.setSubject(id);
		claims.setIssuer(jwtIss);
		claims.setAudience(jwtAudience);
		
		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims.toJson());
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
		jws.setKey(getHmacKey(jwtkey));
		jws.setDoKeyValidation(false); // relaxes the key length requirement

		String jwt = null;
		try {
			jwt = jws.getCompactSerialization();
		} catch (JoseException e) {
			log.warn("do not make JWT TOKEN e="+ e);
		}

		return jwt;
	}
	
	public String generateToken(String jwtKey, String... roles) {
		JwtClaims claims = new JwtClaims();
		claims.setExpirationTimeMinutesInTheFuture(60);
		claims.setSubject("kbs");
		claims.setIssuer("api.signalstudio.io");
		claims.setAudience("using-jwt");
		
		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims.toJson());
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
		jws.setKey(getHmacKey(jwtKey));
		jws.setDoKeyValidation(false); // relaxes the key length requirement

		String jwt = null;
		try {
			jwt = jws.getCompactSerialization();
		} catch (JoseException e) {
			log.warn("do not make JWT TOKEN e"+ e);
		}

		return jwt;
	}
	
	public String generateToken() {
		
		JwtClaims claims = new JwtClaims();
		claims.setExpirationTimeMinutesInTheFuture(5);
		claims.setSubject("kbs");
		claims.setIssuer("api2.livere.com");
		claims.setAudience("kbs");
		claims.setClaim(Claims.groups.name(), Arrays.asList("User"));
		
		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims.toJson());
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
		jws.setKey(readPrivateKey(jwtSecreKeyLocation));
		jws.setHeader("typ", "JWT");
//		jws.setDoKeyValidation(false); // relaxes the key length requirement

		String jwt = null;
		try {
			jwt = jws.getCompactSerialization();
		} catch (JoseException e) {
			log.warn("do not make JWT TOKEN e"+ e);
		}

		return jwt;
	}
	
	public JwtClaims getPayload(String token, String jwtKey) {
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(30)
				.setRequireSubject()
				.setExpectedIssuer("the issuer")
				.setExpectedAudience("the audience")
				.setVerificationKey(getHmacKey(jwtKey))
				.setRelaxVerificationKeyValidation() // relaxes key length requirement
				.build();

		JwtClaims processedClaims = null;
		try {
			processedClaims = jwtConsumer.processToClaims(token);
		} catch (InvalidJwtException e) {
			log.warn("do not parse JWT TOKEN e"+ e);
		}
		return processedClaims;
	}

	public JwtClaims getPayload(String token) {
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(30)
				.setRequireSubject()
				.setExpectedIssuer("the issuer")
				.setExpectedAudience("the audience")
				.setVerificationKey(getHmacKey())
				.setRelaxVerificationKeyValidation() // relaxes key length requirement
				.build();

		JwtClaims processedClaims = null;
		try {
			processedClaims = jwtConsumer.processToClaims(token);
		} catch (InvalidJwtException e) {
			log.warn("do not parse JWT TOKEN e"+ e);
		}
		return processedClaims;
	}

	public String getSubject(String token) {
		try {
			return getPayload(token).getSubject();
		} catch (MalformedClaimException e) {
			log.warn("do not parse JWT TOKEN e", e);
			return null;
		}
	}

	public Boolean isTokenRefresh(String token) {
		JwtClaims map = getPayload(token);
		if (map == null)
			return true;

		NumericDate expiration = null;
		try {
			expiration = map.getExpirationTime();
		} catch (MalformedClaimException e) {
		}
		if (expiration == null)
			return true;

		return expiration.isBefore(NumericDate.now());
	}
	
    public JwtClaims getAllClaimsFromToken(String token) {
        return getPayload(token);
    }
    
    public NumericDate getExpirationDateFromToken(String token) {
        JwtClaims map = getPayload(token);
        if (map == null) return null;

        try {
			return map.getExpirationTime();
		} catch (MalformedClaimException e) {
			return null;
		}
    }
    
    public Boolean isTokenExpired(String token) {
    	NumericDate expiration = getExpirationDateFromToken(token);
        if (expiration == null) return true;

        return expiration.isBefore(NumericDate.now());
    }
    
    public PrivateKey readPrivateKey(final String pemResName) {
        InputStream contentIS = JwtCmd.class.getResourceAsStream(pemResName);
        byte[] tmp = new byte[4096];
        int length;
        PrivateKey pk;
		try {
			length = contentIS.read(tmp);
			pk = decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
		} catch (IOException e) {
			log.warn("do not readPrivateKey JWT TOKEN e"+ e);
			return null;
		}
        return pk;
    }
    
    public static PrivateKey decodePrivateKey(final String pemEncoded) {
        byte[] encodedBytes = toEncodedBytes(pemEncoded);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory kf;
        PrivateKey pk;
		try {
			kf = KeyFactory.getInstance("RSA");
			pk = kf.generatePrivate(keySpec);
		} catch (InvalidKeySpecException e) {
			log.warn("do not generatePrivate JWT TOKEN e"+ e);
			return null;
		} catch (NoSuchAlgorithmException e) {
			log.warn("do not decodePrivateKey JWT TOKEN e"+ e);
			return null;
		}
        return pk;
    }
    
    private static byte[] toEncodedBytes(final String pemEncoded) {
        final String normalizedPem = removeBeginEnd(pemEncoded);
        return Base64.getDecoder().decode(normalizedPem);
    }
    
    private static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }
    
}
