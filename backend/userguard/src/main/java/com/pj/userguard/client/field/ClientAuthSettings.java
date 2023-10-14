package com.pj.userguard.client.field;

import com.pj.userguard.util.lang.AssertString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

@Embeddable
public class ClientAuthSettings {

    @Column(name = "require_proof_key")
    private Boolean requireProofKey;

    @Column(name = "require_auth_consent")
    private Boolean requireAuthConsent;

    @Column(name = "jwk_set_url")
    private String jwkSetUrl;

    @Column(name = "signing_algorithm")
    private String signingAlgorithm;

    protected ClientAuthSettings() {}

    private ClientAuthSettings(boolean requireProofKey, boolean requireAuthConsent, String jwkSetUrl,
                               JwsAlgorithm jwsAlgorithm) {
        this.requireProofKey = requireProofKey;
        this.requireAuthConsent = requireAuthConsent;
        this.jwkSetUrl = jwkSetUrl;
        this.signingAlgorithm = jwsAlgorithm.getName();
    }

    public static ClientAuthSettings fromClientSettings(ClientSettings settings) {
        return new ClientAuthSettings(settings.isRequireProofKey(),
                settings.isRequireAuthorizationConsent(),
                settings.getJwkSetUrl(),
                settings.getTokenEndpointAuthenticationSigningAlgorithm());
    }

    public ClientSettings toClientSettings() {
        var settingsBuilder = ClientSettings.builder();
        if (requireProofKey != null) settingsBuilder.requireProofKey(requireProofKey);
        if (requireAuthConsent != null) settingsBuilder.requireAuthorizationConsent(requireAuthConsent);
        if (jwkSetUrl != null) settingsBuilder.jwkSetUrl(jwkSetUrl);
        if (signingAlgorithm != null) settingsBuilder.tokenEndpointAuthenticationSigningAlgorithm(getSignatureAlgorithm());
        return settingsBuilder.build();
    }

    private SignatureAlgorithm getSignatureAlgorithm() {
        AssertString.notNull(signingAlgorithm, "signing algorithm is null");
        return SignatureAlgorithm.from(signingAlgorithm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ClientAuthSettings that = (ClientAuthSettings) o;

        return new EqualsBuilder()
                .append(requireProofKey, that.requireProofKey)
                .append(requireAuthConsent, that.requireAuthConsent)
                .append(jwkSetUrl, that.jwkSetUrl)
                .append(signingAlgorithm, that.signingAlgorithm)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(requireProofKey)
                .append(requireAuthConsent)
                .append(jwkSetUrl)
                .append(signingAlgorithm)
                .toHashCode();
    }
}
