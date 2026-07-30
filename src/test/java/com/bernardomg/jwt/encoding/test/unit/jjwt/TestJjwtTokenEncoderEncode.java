
package com.bernardomg.jwt.encoding.test.unit.jjwt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.jwt.encoding.TokenEncoder;
import com.bernardomg.jwt.encoding.jjwt.JjwtTokenEncoder;
import com.bernardomg.jwt.encoding.test.config.factory.JwtTokenDatas;
import com.bernardomg.jwt.encoding.test.config.factory.Tokens;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.PrematureJwtException;

@DisplayName("JjwtTokenEncoder - encode")
class TestJjwtTokenEncoderEncode {

    private final TokenEncoder encoder = new JjwtTokenEncoder(Tokens.KEY);

    private Claims parse(final String token) {
        return Jwts.parser()
            .verifyWith(Tokens.KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    @Test
    @DisplayName("Encodes a token")
    void testEncode_Empty() {
        final String token;

        // WHEN
        token = encoder.encode(JwtTokenDatas.empty());

        // THEN
        Assertions.assertThat(token)
            .as("token")
            .isNotBlank();
    }

    @Test
    @DisplayName("Encodes expiration")
    void testEncode_Expiration() {
        final Claims claims;

        // WHEN
        claims = parse(encoder.encode(JwtTokenDatas.expirationNextMonth()));

        // THEN
        Assertions.assertThat(claims.getExpiration()
            .toInstant())
            .as("expiration")
            .isEqualTo(Tokens.EXPIRATION_DATE);
    }

    @Test
    @DisplayName("Encodes an expired token")
    void testEncode_ExpiredToken() {
        final String token;

        // GIVEN
        token = encoder.encode(JwtTokenDatas.expired());

        // WHEN / THEN
        Assertions.assertThatThrownBy(() -> parse(token))
            .as("expired token")
            .isInstanceOf(ExpiredJwtException.class);
    }

    @Test
    @DisplayName("Encodes issued at")
    void testEncode_IssuedAt() {
        final Claims claims;

        // WHEN
        claims = parse(encoder.encode(JwtTokenDatas.issuedAt()));

        // THEN
        Assertions.assertThat(claims.getIssuedAt()
            .toInstant())
            .as("issued at")
            .isEqualTo(Tokens.ISSUED_AT);
    }

    @Test
    @DisplayName("Encodes issuer")
    void testEncode_Issuer() {
        final Claims claims;

        // WHEN
        claims = parse(encoder.encode(JwtTokenDatas.issuer()));

        // THEN
        Assertions.assertThat(claims.getIssuer())
            .as("issuer")
            .isEqualTo(Tokens.ISSUER);
    }

    @Test
    @DisplayName("Encodes a token not valid before a future date")
    void testEncode_NotBeforeInFuture() {
        final String token;

        // GIVEN
        token = encoder.encode(JwtTokenDatas.notBeforeInFuture());

        // WHEN / THEN
        Assertions.assertThatThrownBy(() -> parse(token))
            .as("token with not-before date in the future")
            .isInstanceOf(PrematureJwtException.class);
    }

    @Test
    @DisplayName("Encodes a token valid after a past date")
    void testEncode_NotBeforeInPast() {
        final String token;

        // GIVEN
        token = encoder.encode(JwtTokenDatas.notBeforeInPast());

        // WHEN / THEN
        Assertions.assertThatCode(() -> parse(token))
            .as("token with not-before date in the past")
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Encodes a token which is not expired")
    void testEncode_NotExpiredToken() {
        final String token;

        // GIVEN
        token = encoder.encode(JwtTokenDatas.notExpired());

        // WHEN / THEN
        Assertions.assertThatCode(() -> parse(token))
            .as("non-expired token")
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Encodes permissions")
    void testEncode_Permissions() {
        final Claims claims;

        // WHEN
        claims = parse(encoder.encode(JwtTokenDatas.permissions()));

        // THEN
        Assertions.assertThat(claims.get("permissions"))
            .as("expiration")
            .isEqualTo(JwtTokenDatas.permissions()
                .permissions());
    }

    @Test
    @DisplayName("Creates a token signed with the configured key")
    void testEncode_SignWithConfiguredKey() {
        final String token;

        // GIVEN
        token = encoder.encode(JwtTokenDatas.value());

        // WHEN / THEN
        Assertions.assertThatCode(() -> parse(token))
            .as("token signature")
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Creates a token using HS512")
    void testEncode_UseHs512Algorithm() {
        final String token;
        final String algorithm;

        // GIVEN
        token = encoder.encode(JwtTokenDatas.value());

        // WHEN
        algorithm = Jwts.parser()
            .verifyWith(Tokens.KEY)
            .build()
            .parseSignedClaims(token)
            .getHeader()
            .getAlgorithm();

        // THEN
        Assertions.assertThat(algorithm)
            .as("signature algorithm")
            .isEqualTo("HS512");
    }

    @Test
    @DisplayName("Encodes custom values")
    void testEncode_Values() {
        final Claims claims;

        // WHEN
        claims = parse(encoder.encode(JwtTokenDatas.value()));

        // THEN
        Assertions.assertThat(claims.get("key", String.class))
            .as("custom claim")
            .isEqualTo("value");
    }

}
