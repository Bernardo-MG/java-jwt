
package com.bernardomg.jwt.encoding.test.unit.jjwt;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.jwt.encoding.JwtTokenData;
import com.bernardomg.jwt.encoding.TokenDecoder;
import com.bernardomg.jwt.encoding.jjwt.JjwtTokenDecoder;
import com.bernardomg.jwt.encoding.test.config.factory.JwtTokens;
import com.bernardomg.jwt.encoding.test.config.factory.PermissionConstants;
import com.bernardomg.jwt.encoding.test.config.factory.Tokens;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@DisplayName("JjwtTokenDecoder - decode")
class TestJjwtTokenDecoderDecode {

    private final TokenDecoder decoder = new JjwtTokenDecoder(Tokens.KEY);

    @Test
    @DisplayName("Recovers the audience from a token")
    void testDecode_Audience() {
        final String       token;
        final JwtTokenData tokenData;

        // GIVEN
        token = JwtTokens.WITH_AUDIENCE;

        // WHEN
        tokenData = decoder.decode(token);

        // THEN
        Assertions.assertThat(tokenData.audience())
            .as("audience")
            .containsExactly(Tokens.AUDIENCE);
    }

    @Test
    @DisplayName("Recovers the custom values from a token")
    void testDecode_CustomValue() {
        final String       token;
        final JwtTokenData tokenData;

        // GIVEN
        token = JwtTokens.WITH_CUSTOM_VALUE;

        // WHEN
        tokenData = decoder.decode(token);

        // THEN
        Assertions.assertThat(tokenData.values())
            .as("values")
            .containsExactlyEntriesOf(Map.of(Tokens.CUSTOM_KEY, Tokens.CUSTOM_VALUE));
    }

    @Test
    @DisplayName("An empty token generates an exception")
    void testDecode_Empty() {
        final String           token;
        final ThrowingCallable executable;

        // GIVEN
        token = JwtTokens.EMPTY;

        // WHEN
        executable = () -> decoder.decode(token);

        // THEN
        Assertions.assertThatThrownBy(executable)
            .isInstanceOf(UnsupportedJwtException.class);
    }

    @Test
    @DisplayName("Recovers the expiration date from a token")
    void testDecode_ExpirationDate() {
        final String       token;
        final JwtTokenData tokenData;

        // GIVEN
        token = JwtTokens.EXPIRATION_DATE;

        // WHEN
        tokenData = decoder.decode(token);

        // THEN
        Assertions.assertThat(tokenData.expiration())
            .as("subject")
            .isEqualTo(Tokens.EXPIRATION_DATE);
    }

    @Test
    @DisplayName("Decoding an expired token generates an exception")
    void testDecode_Expired() {
        final String           token;
        final ThrowingCallable executable;

        // GIVEN
        token = JwtTokens.EXPIRED;

        // WHEN
        executable = () -> decoder.decode(token);

        // THEN
        Assertions.assertThatThrownBy(executable)
            .isInstanceOf(ExpiredJwtException.class);
    }

    @Test
    @DisplayName("Recovers the issued at from a token")
    void testDecode_IssuedAt() {
        final String       token;
        final JwtTokenData tokenData;

        // GIVEN
        token = JwtTokens.WITH_ISSUED_AT;

        // WHEN
        tokenData = decoder.decode(token);

        // THEN
        Assertions.assertThat(tokenData.issuedAt())
            .as("issued at")
            .isEqualTo(Tokens.ISSUED_AT);
    }

    @Test
    @DisplayName("Recovers the issuer from a token")
    void testDecode_Issuer() {
        final String       token;
        final JwtTokenData tokenData;

        // GIVEN
        token = JwtTokens.WITH_ISSUER;

        // WHEN
        tokenData = decoder.decode(token);

        // THEN
        Assertions.assertThat(tokenData.issuer())
            .as("issuer")
            .isEqualTo(Tokens.ISSUER);
    }

    @Test
    @DisplayName("Recovers the not before date from a token")
    void testDecode_NotBefore() {
        final String       token;
        final JwtTokenData tokenData;

        // GIVEN
        token = JwtTokens.WITH_NOT_BEFORE_IN_PAST;

        // WHEN
        tokenData = decoder.decode(token);

        // THEN
        Assertions.assertThat(tokenData.notBefore())
            .as("not before")
            .isEqualTo(Tokens.NOT_BEFORE);
    }

    @Test
    @DisplayName("Recovers the permissions from a token")
    void testDecode_Permissions() {
        final String       token;
        final JwtTokenData tokenData;

        // GIVEN
        token = JwtTokens.WITH_PERMISSIONS;

        // WHEN
        tokenData = decoder.decode(token);

        // THEN
        Assertions.assertThat(tokenData.permissions())
            .as("permissions")
            .containsExactlyEntriesOf(Map.of(PermissionConstants.DATA, List.of(PermissionConstants.READ)));
    }

    @Test
    @DisplayName("Recovers the subject from a token")
    void testDecode_Subject() {
        final String       token;
        final JwtTokenData tokenData;

        // GIVEN
        token = JwtTokens.WITH_SUBJECT;

        // WHEN
        tokenData = decoder.decode(token);

        // THEN
        Assertions.assertThat(tokenData.subject())
            .as("subject")
            .isEqualTo(Tokens.SUBJECT);
    }

}
