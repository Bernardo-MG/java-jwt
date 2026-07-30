
package com.bernardomg.jwt.encoding.test.config.factory;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.bernardomg.jwt.encoding.JwtTokenData;

public final class JwtTokenDatas {

    public static final JwtTokenData empty() {
        return new JwtTokenData(null, null, null, null, null, null, List.of(), Map.of(), Map.of("key", "value"));
    }

    public static final JwtTokenData expired() {
        return new JwtTokenData(null, null, null, null, null, Instant.now()
            .minusSeconds(60), List.of(), Map.of(), Map.of());
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

    public static final JwtTokenData withIssuerNextMonth() {
        return new JwtTokenData(null, null, Tokens.ISSUER, null, null, Tokens.NEXT_MONTH_DATE, List.of(), Map.of(),
            Map.of());
    }

    private JwtTokenDatas() {
        super();
    }

}
