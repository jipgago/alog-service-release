package kea.alog.release.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kea.alog.release.web.DTO.TokenPayLoadDto;

import java.util.Base64;

public class JwtProvider {
    public String getPayload(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getPayload();
    }

    public TokenPayLoadDto getUserInfo(String payload) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String str = new String(decoder.decode(payload));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(str, TokenPayLoadDto.class);
    }
}
