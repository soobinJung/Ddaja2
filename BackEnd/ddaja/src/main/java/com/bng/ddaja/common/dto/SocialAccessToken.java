package com.bng.ddaja.common.dto;

import java.util.Date;

import com.bng.ddaja.common.enums.TokenType;
import com.bng.ddaja.common.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialAccessToken {
    private String accessToken;
    private String refreshToken;
    private TokenType tokenType;
    private int expireTime;
    private int refreshExpireTime;
    private Date expire;
    private Date refreshExpire;

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
        this.expire = DateUtil.addSeconds(new Date(), expireTime);
    }

    public void setRefreshExpireTime(int refreshExpireTime) {
        this.refreshExpireTime = refreshExpireTime;
        this.refreshExpire = DateUtil.addSeconds(new Date(), refreshExpireTime);
    }
}
