package com.binx.web.vo;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.binx.core.enums.ResultCode;
import com.binx.core.exception.BusinessException;
import com.binx.utils.CommonUtils;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther X .
 */
public class Token {
    private String id;
    private String name;
    private String role;
    private Map<String, Object> claims;

    public Token() {
        this.claims = new HashMap();
    }

    public Token(Object id, String name) {
        this(id, name, (String)null);
    }

    public Token(Object id, String name, String role) {
        if (id != null) {
            this.id = String.valueOf(id);
        }

        this.name = name;
        this.role = role;
        this.claims = new HashMap();
    }

    public <T> T getIdValue(Class<T> clazz) {
        return CommonUtils.isEmpty(this.id) ? null : Convert.convert(clazz, this.id);
    }

    public Integer getIdAsInteger() {
        return (Integer)this.getIdValue(Integer.class);
    }

    public <T> T getClaimValue(String key, Class<T> clazz) {
        if (this.claims != null && this.claims.containsKey(key)) {
            Object value = this.claims.get(key);
            return value == null ? null : Convert.convert(clazz, value);
        } else {
            return null;
        }
    }

    public void setClaimValue(String key, Object value) {
        if (this.claims.containsKey(key)) {
            this.claims.replace(key, value);
        } else {
            this.claims.put(key, value);
        }

    }

    private JWTCreator.Builder build(Date expiresAt) {
        JWTCreator.Builder builder = JWT.create();
        if (expiresAt != null) {
            builder.withExpiresAt(expiresAt);
        }

        builder.withClaim("id", this.id);
        builder.withClaim("name", this.name);
        builder.withClaim("role", this.role);
        builder.withClaim("claims", this.claims);
        return builder;
    }

    private static Token verify(DecodedJWT jwt) {
        Date expiresAt = jwt.getExpiresAt();
        if (expiresAt != null && (new Date()).after(expiresAt)) {
            throw new BusinessException(ResultCode.UN_AUTHORIZED);
        } else {
            Map<String, Claim> map = jwt.getClaims();
            Token token = new Token();
            if (map.get("id") != null) {
                token.setId(((Claim)map.get("id")).asString());
            }

            if (map.get("name") != null) {
                token.setName(((Claim)map.get("name")).asString());
            }

            if (map.get("role") != null) {
                token.setRole(((Claim)map.get("role")).asString());
            }

            if (map.get("claims") != null) {
                Map<String, Object> claims = ((Claim)map.get("claims")).asMap();
                token.setClaims(claims);
            }

            return token;
        }
    }

    public String jwt(String secret) {
        return this.jwt(secret, (Date)null);
    }

    public String jwt(String secret, Date expiresAt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTCreator.Builder builder = this.build(expiresAt);
            return builder.sign(algorithm);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static Token verify(String jwtToken, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(jwtToken);
            return verify(jwt);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public String jwtRsa(String privateKey, Date expiresAt) {
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            byte[] bytes = Base64.decode(privateKey.getBytes());
            EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)factory.generatePrivate(keySpec);
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey)null, rsaPrivateKey);
            JWTCreator.Builder builder = this.build(expiresAt);
            return builder.sign(algorithm);
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public static Token verifyRsa(String jwtToken, String publicKey) {
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            byte[] bytes = Base64.decode(publicKey.getBytes());
            EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            RSAPublicKey rsaPublicKey = (RSAPublicKey)factory.generatePublic(keySpec);
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, (RSAPrivateKey)null);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(jwtToken);
            return verify(jwt);
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public Map<String, Object> getClaims() {
        return this.claims;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setClaims(Map<String, Object> claims) {
        this.claims = claims;
    }

    public String toString() {
        return "Token(id=" + this.getId() + ", name=" + this.getName() + ", role=" + this.getRole() + ", claims=" + this.getClaims() + ")";
    }
}
