package com.serendipity.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class BaseProperties {
    private String tokenKey = "Token";
    private String jwtTokenKey = "Jwt-Token";
    private String jwtSecret = "serendipity666";
    private String jwtPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKGlMP7CtGKPLQa95rjAFN4aXmvi+ty9b7l5AlUXofBY7cB4fcrJtgpQ8DfNEXZqwKmuybTwsIif9a96BY4W+0upWpqwn6yrG6WyL5GLPLK91lEsLL9Egu69XTdwIXXVSmJhw5dmDQDm3yMQ/zErDCY749JG6jog7V70H0bwqWFVAgMBAAECgYB92rIlCM+NlVBHWX/UBKm6q+ksMtODHnNogxEP05CgI30pSCcYxqhXq+D8cUvRWRichJxDpsqr9W9Cqsy8bqREbZb/AnqG45vWA15AZ01Mw5I+TQx0gYMAoRVMIiokoSIzipWLiciO7QfU6v+tEtMzgpT70sSZUqsqTGooLBV62QJBANPA5M7O1yX6C9RWsKX+TsZuApqMdN8rrx+rhcVMGpc15Fe2UMJw11FLt7V0OCLrmlvOf/Oyo61ywg5b2sWR3EcCQQDDa+luDf69TGsfKGZfDyZNcd67eX2wur71VRK62LGcELdUfUk2ETd5Ocj2J8PooPN6ft5Fm9kUyOJQxlbqNo+DAkEAx3Zq0VfLGA3KONEUArhjMc0MjNIDgPjgND/YeTmvzoHIPssQlae/Wues+7Km8lXmB9ajdkXuogJ4QxhJIlO8HwJAYYuW/EleE412lxnqZ56/Rr8FJ91P4QyGTx2E+OE7XqR1bmYPSj6zyv4OEtjskEzu3AFB38sYQ6i2Vw15yvUrEwJBANC9GMYUm8f2/6PA5kK+h/d30DdhFjxFyn849eudbnOK5KzFagiK3xLFBVjryXenE1ZHyU2kqnpI2U1467sI0TA=";
    private String jwtPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChpTD+wrRijy0Gvea4wBTeGl5r4vrcvW+5eQJVF6HwWO3AeH3KybYKUPA3zRF2asCprsm08LCIn/WvegWOFvtLqVqasJ+sqxulsi+RizyyvdZRLCy/RILuvV03cCF11UpiYcOXZg0A5t8jEP8xKwwmO+PSRuo6IO1e9B9G8KlhVQIDAQAB";
    private String swaggerBasePath = "/";
    private String swaggerToken = "";
    private boolean corsEnable = true;
    private String corsMethods = "GET,POST,PUT,DELETE,OPTIONS";
    private String corsOrigin = "*";
    private String corsHeaders = "Token,Jwt-Token,content-type,access-token,accesstoken";


    public String getTokenKey() {
        return this.tokenKey;
    }

    public String getJwtTokenKey() {
        return this.jwtTokenKey;
    }

    public String getJwtSecret() {
        return this.jwtSecret;
    }

    public String getJwtPrivateKey() {
        return this.jwtPrivateKey;
    }

    public String getJwtPublicKey() {
        return this.jwtPublicKey;
    }

    public String getSwaggerBasePath() {
        return this.swaggerBasePath;
    }

    public String getSwaggerToken() {
        return this.swaggerToken;
    }

    public boolean isCorsEnable() {
        return this.corsEnable;
    }

    public String getCorsMethods() {
        return this.corsMethods;
    }

    public String getCorsOrigin() {
        return this.corsOrigin;
    }

    public String getCorsHeaders() {
        return this.corsHeaders;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public void setJwtTokenKey(String jwtTokenKey) {
        this.jwtTokenKey = jwtTokenKey;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public void setJwtPrivateKey(String jwtPrivateKey) {
        this.jwtPrivateKey = jwtPrivateKey;
    }

    public void setJwtPublicKey(String jwtPublicKey) {
        this.jwtPublicKey = jwtPublicKey;
    }

    public void setSwaggerBasePath(String swaggerBasePath) {
        this.swaggerBasePath = swaggerBasePath;
    }

    public void setSwaggerToken(String swaggerToken) {
        this.swaggerToken = swaggerToken;
    }

    public void setCorsEnable(boolean corsEnable) {
        this.corsEnable = corsEnable;
    }

    public void setCorsMethods(String corsMethods) {
        this.corsMethods = corsMethods;
    }

    public void setCorsOrigin(String corsOrigin) {
        this.corsOrigin = corsOrigin;
    }

    public void setCorsHeaders(String corsHeaders) {
        this.corsHeaders = corsHeaders;
    }
}
