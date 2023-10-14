package com.pj.userguard.client.field;

import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;

@Getter
@Embeddable
public class TokenAuthSettings {

    @Column(name = "auth_code_seconds_to_live")
    private Long authCodeSecondsToLive;

    @Column(name = "access_token_seconds_to_live")
    private Long accessTokenSecondsToLive;

    @Column(name = "access_token_format")
    private String accessTokenFormat;

    @Column(name = "device_code_seconds_to_live")
    private Long deviceCodeSecondsToLive;

    @Column(name = "reuse_refresh_tokens")
    private Boolean reuseRefreshTokens;

    @Column(name = "refresh_token_seconds_to_live")
    private Long refreshTokenSecondsToLive;

    @Column(name = "token_signature_algorithm")
    private String tokenSignatureAlgorithm;

    protected TokenAuthSettings() {}

    private TokenAuthSettings(Duration authCodeTimeToLive, Duration accessTokenTimeToLive,
                              OAuth2TokenFormat tokenFormat, Duration deviceCodeTimeToLive,
                              boolean reuseRefreshTokens, Duration refreshTokenTimeToLive,
                              SignatureAlgorithm tokenSignatureAlgorithm) {
        this.authCodeSecondsToLive = authCodeTimeToLive.toSeconds();
        this.accessTokenSecondsToLive = accessTokenTimeToLive.toSeconds();
        this.accessTokenFormat = tokenFormat.getValue();
        this.deviceCodeSecondsToLive = deviceCodeTimeToLive.toSeconds();
        this.reuseRefreshTokens = reuseRefreshTokens;
        this.refreshTokenSecondsToLive = refreshTokenTimeToLive.toSeconds();
        this.tokenSignatureAlgorithm = tokenSignatureAlgorithm.getName();
    }

    private SignatureAlgorithm getSignatureAlgorithm() {
        AssertString.notNull(tokenSignatureAlgorithm, "signature algorithm is null");
        return SignatureAlgorithm.from(tokenSignatureAlgorithm);
    }

    public static TokenAuthSettings fromTokenSettings(TokenSettings settings) {
        return new TokenAuthSettings(settings.getAuthorizationCodeTimeToLive(),
                settings.getAccessTokenTimeToLive(),
                settings.getAccessTokenFormat(),
                settings.getDeviceCodeTimeToLive(),
                settings.isReuseRefreshTokens(),
                settings.getRefreshTokenTimeToLive(),
                settings.getIdTokenSignatureAlgorithm());
    }

    public TokenSettings toTokenSettings() {
        var settingsBuilder = TokenSettings.builder();
        if (authCodeSecondsToLive != null) {
            settingsBuilder.authorizationCodeTimeToLive(Duration.ofSeconds(authCodeSecondsToLive));
        }
        if (accessTokenSecondsToLive != null) {
            settingsBuilder.accessTokenTimeToLive(Duration.ofSeconds(accessTokenSecondsToLive));
        }
        if (accessTokenFormat != null) {
            settingsBuilder.accessTokenFormat(new OAuth2TokenFormat(accessTokenFormat));
        }
        if (deviceCodeSecondsToLive != null) {
            settingsBuilder.deviceCodeTimeToLive(Duration.ofSeconds(deviceCodeSecondsToLive));
        }
        if (reuseRefreshTokens != null) {
            settingsBuilder.reuseRefreshTokens(reuseRefreshTokens);
        }
        if (refreshTokenSecondsToLive != null) {
            settingsBuilder.refreshTokenTimeToLive(Duration.ofSeconds(refreshTokenSecondsToLive));
        }
        if (tokenSignatureAlgorithm != null) {
            settingsBuilder.idTokenSignatureAlgorithm(getSignatureAlgorithm());
        }
        return settingsBuilder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TokenAuthSettings that = (TokenAuthSettings) o;

        return new EqualsBuilder()
                .append(authCodeSecondsToLive, that.authCodeSecondsToLive)
                .append(accessTokenSecondsToLive, that.accessTokenSecondsToLive)
                .append(accessTokenFormat, that.accessTokenFormat)
                .append(deviceCodeSecondsToLive, that.deviceCodeSecondsToLive)
                .append(reuseRefreshTokens, that.reuseRefreshTokens)
                .append(refreshTokenSecondsToLive, that.refreshTokenSecondsToLive)
                .append(tokenSignatureAlgorithm, that.tokenSignatureAlgorithm)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(authCodeSecondsToLive)
                .append(accessTokenSecondsToLive)
                .append(accessTokenFormat)
                .append(deviceCodeSecondsToLive)
                .append(reuseRefreshTokens)
                .append(refreshTokenSecondsToLive)
                .append(tokenSignatureAlgorithm)
                .toHashCode();
    }
}
