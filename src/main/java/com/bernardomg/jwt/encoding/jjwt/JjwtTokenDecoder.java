
package com.bernardomg.jwt.encoding.jjwt;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.crypto.SecretKey;

import com.bernardomg.jwt.encoding.JwtTokenData;
import com.bernardomg.jwt.encoding.TokenDecoder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

/**
 * JWT token decoder based on the JJWT library.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public final class JjwtTokenDecoder implements TokenDecoder {

    private static final Collection<String> MANAGED_CLAIMS = Set.of(Claims.ID, Claims.SUBJECT, Claims.ISSUER,
        Claims.ISSUED_AT, Claims.NOT_BEFORE, Claims.EXPIRATION, Claims.AUDIENCE, "permissions");

    private static Map<String, String> readValues(final Claims claims) {
        final Map<String, String> values;

        values = new HashMap<>();
        claims.forEach((name, value) -> {
            if (!MANAGED_CLAIMS.contains(name) && value instanceof final String stringValue) {
                values.put(name, stringValue);
            }
        });

        return Map.copyOf(values);
    }

    /**
     * JWT parser for reading tokens.
     */
    private final JwtParser parser;

    /**
     * Builds a decoder with the received parser.
     *
     * @param prsr
     *            JWT parser
     */
    public JjwtTokenDecoder(final JwtParser prsr) {
        super();

        parser = Objects.requireNonNull(prsr);
    }

    /**
     * Builds a decoder with the received key.
     *
     * @param key
     *            secret key for the token
     */
    public JjwtTokenDecoder(final SecretKey key) {
        super();

        Objects.requireNonNull(key);

        parser = Jwts.parser()
            .verifyWith(key)
            .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final JwtTokenData decode(final String token) {
        final Claims                    claims;
        final Instant                   issuedAt;
        final Instant                   expiration;
        final Instant                   notBefore;
        final Map<String, List<String>> permissions;
        final Map<String, String>       values;

        // Acquire claims
        claims = parser.parseSignedClaims(token)
            .getPayload();

        // Issued at
        if (claims.getIssuedAt() != null) {
            issuedAt = claims.getIssuedAt()
                .toInstant();
        } else {
            issuedAt = null;
        }

        // Expiration
        if (claims.getExpiration() != null) {
            expiration = claims.getExpiration()
                .toInstant();
        } else {
            expiration = null;
        }

        // Not before
        if (claims.getNotBefore() != null) {
            notBefore = claims.getNotBefore()
                .toInstant();
        } else {
            notBefore = null;
        }

        // Permissions
        if (claims.get("permissions") != null) {
            permissions = (Map<String, List<String>>) claims.get("permissions");
        } else {
            permissions = null;
        }

        values = readValues(claims);

        return new JwtTokenData(claims.getId(), claims.getSubject(), claims.getIssuer(), issuedAt, notBefore,
            expiration, claims.getAudience(), permissions, values);
    }

}
