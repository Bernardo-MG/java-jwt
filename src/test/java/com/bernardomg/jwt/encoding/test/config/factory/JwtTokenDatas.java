
package com.bernardomg.jwt.encoding.test.config.factory;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.bernardomg.jwt.encoding.JwtTokenData;

public final class JwtTokenDatas {

    public static final JwtTokenData empty() {
        return new JwtTokenData(null, null, null, null, null, null, List.of(), Map.of(), Map.of());
    }

    public static final JwtTokenData expirationNextMonth() {
        return new JwtTokenData(null, null, null, null, null, Tokens.EXPIRATION_DATE, List.of(), Map.of(), Map.of());
    }

    public static final JwtTokenData expired() {
        return new JwtTokenData(null, null, null, null, null, Instant.now()
            .minusSeconds(60), List.of(), Map.of(), Map.of());
    }

    public static final JwtTokenData issuedAt() {
        return new JwtTokenData(null, null, null, Tokens.ISSUED_AT, null, null, List.of(), Map.of(), Map.of());
    }

    public static final JwtTokenData issuer() {
        return new JwtTokenData(null, null, Tokens.ISSUER, null, null, null, List.of(), Map.of(), Map.of());
    }

    public static final JwtTokenData notBeforeInFuture() {
        return new JwtTokenData(null, null, null, null, Instant.now()
            .plusSeconds(60), null, List.of(), Map.of(), Map.of());
    }

    public static final JwtTokenData notBeforeInPast() {
        return new JwtTokenData(null, null, null, null, Instant.now()
            .minusSeconds(60), null, List.of(), Map.of(), Map.of());
    }

    public static final JwtTokenData notExpired() {
        return new JwtTokenData(null, null, null, null, null, Instant.now()
            .plusSeconds(60), List.of(), Map.of(), Map.of());
    }

    public static final JwtTokenData permissions() {
        return new JwtTokenData(null, null, null, null, null, null, List.of(),
            Map.of(PermissionConstants.DATA, List.of(PermissionConstants.READ)), Map.of());
    }

    public static final JwtTokenData value() {
        return new JwtTokenData(null, null, null, null, null, null, List.of(), Map.of(), Map.of("key", "value"));
    }

    private JwtTokenDatas() {
        super();
    }

}
