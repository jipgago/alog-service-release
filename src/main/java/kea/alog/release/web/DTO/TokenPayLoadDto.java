package kea.alog.release.web.DTO;

import lombok.Getter;

@Getter
public class TokenPayLoadDto {
    private String userNN;
    private String userEmail;
    private Long userPk;
    private Long iat;
    private Long exp;
}
