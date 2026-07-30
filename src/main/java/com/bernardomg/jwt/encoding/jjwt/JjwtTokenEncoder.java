
package com.bernardomg.jwt.encoding.jjwt;

import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bernardomg.jwt.encoding.JwtTokenData;
import com.bernardomg.jwt.encoding.TokenEncoder;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

/**
 * JWT token encoder based on the JJWT library.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public final class JjwtTokenEncoder implements TokenEncoder {

    /**
     * Logger for the class.
     */
    private static final Logger log = LoggerFactory.getLogger(JjwtTokenEncoder.class);

    /**
     * Secret key for generating tokens.
     */
    private final SecretKey     key;

    /**
     * Constructs an encoder with the received arguments.
     *
     * @param secretKey
     *            secret key used for the token
     */
    public JjwtTokenEncoder(final SecretKey secretKey) {
        super();

        key = Objects.requireNonNull(secretKey, "The secret key must not be null");
    }

    @Override
    public final String encode(final JwtTokenData data) {
        final String     token;
        final Date       issuedAt;
        final Date       expiration;
        final Date       notBefore;
        final JwtBuilder jwtBuilder;

        jwtBuilder = Jwts.builder()
            .id(data.id())
            .issuer(data.issuer())
            .subject(data.subject());

        if (!data.permissions()
            .isEmpty()) {
            jwtBuilder.claim("permissions", data.permissions());
        }

        jwtBuilder.audience()
            .add(data.audience());

        // TODO: Use optional
        // Issued at
        if (data.issuedAt() != null) {
            issuedAt = java.util.Date.from(data.issuedAt()
                .atZone(ZoneId.systemDefault())
                .toInstant());
            jwtBuilder.issuedAt(issuedAt);
        }

        // Expiration
        if (data.expiration() != null) {
            expiration = java.util.Date.from(data.expiration()
                .atZone(ZoneId.systemDefault())
                .toInstant());
            jwtBuilder.expiration(expiration);
        }

        // Not before
        if (data.notBefore() != null) {
            notBefore = java.util.Date.from(data.notBefore()
                .atZone(ZoneId.systemDefault())
                .toInstant());
            jwtBuilder.notBefore(notBefore);
        }

        data.values()
            .forEach(jwtBuilder::claim);

        token = jwtBuilder.signWith(key, Jwts.SIG.HS512)
            .compact();

        log.debug("Created token from {}", data);

        return token;
    }

}
