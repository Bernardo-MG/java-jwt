
package com.bernardomg.jwt.encoding.test.config.factory;

public final class JwtTokens {

    public static final String EMPTY                     = "eyJhbGciOiJIUzUxMiJ9..BZwJ1TnPaPg1jkp7l8Y7qj-jHXWkTP6TXYAElGiAlFz-bWL6aPq8-T8nWAT0G_QkZYQ2bPizJdSPcNuAXCLwvQ";

    public static final String EXPIRATION_DATE           = "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjQxMDI0NDQ4MDB9.dywFie93vBHKBTTgmzGZ2LLwh7uIyRMCh-QfqDo6KJPsvuY4cV5lYjAxcAgzNQWdUMDGqHEpTa9IJXKH9MVfkg";

    public static final String EXPIRED                   = "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1ODA1MTE2MDB9.uQag93K8bdiHFFMJAx-9s0JTZ6-3fQOWfLbdXwimbg84A68BulI1RMftGZyZt7UmL_QoEQb3Z6lG_aL_4P_Kkg";

    public static final String WITH_AUDIENCE             = "eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOlsiYXVkaWVuY2UiXX0.MC9oB7dYxYp6yrzZwKazSZ484NUwTQrs_sgAMeBdVPMpF0aU83GRF3fLD8FhiamG5f0yWH6LxsWUUARVqdA6pA";

    public static final String WITH_CUSTOM_VALUE         = "eyJhbGciOiJIUzUxMiJ9.eyJrZXkiOiJ2YWx1ZSJ9.OFT4xa37aux54ZjvwIv33lJnZBTZIpTi_1GP0i72T_HoelPjNeHzo4Q3P0-SYwEK4MIqMgJsQKszawiIpSsdmQ";

    public static final String WITH_ISSUED_AT            = "eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1ODA1MTUyMDB9.6doBECakGZqU4zR6d-QmqYCYTwyZTlU9qK8_9f06U6nl5IwaD0SYxBqt6w0SSrWrQzVdRwecOjdsmTT5iKNdlg";

    public static final String WITH_ISSUER               = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJpc3N1ZXIifQ.PH60Xpv3vcGpi50DTUVZE0lGKi5r2wsJwLQWk2Vm_Wg8YBQdTfobnoYie8wfgsyKNoM_j21B_riBWMIyEcYTDA";

    public static final String WITH_NOT_BEFORE_IN_FUTURE = "eyJhbGciOiJIUzUxMiJ9.eyJuYmYiOjE3NjcyMjU2MDB9.4s0ZFm_-pUpwyPt_aRHlINFejrbAtQ3aRRUj0YMKjsLEBinEJilRe36EXg2npyFi4FrHBLShEA_kmM1zqBURig";

    public static final String WITH_NOT_BEFORE_IN_PAST   = "eyJhbGciOiJIUzUxMiJ9.eyJuYmYiOjE1ODA1MTUyMDB9.1GVQDx9yXlZbApSm8R2nySj79itvFABY5wsihsqE9icjP3TlQ0YP_ZEUsV-ZFr2q5LKrEWQJEVpZJRbNVaD4rg";

    public static final String WITH_PERMISSIONS          = "eyJhbGciOiJIUzUxMiJ9.eyJwZXJtaXNzaW9ucyI6eyJEQVRBIjpbIlJFQUQiXX19.QlVE0vPANQLHnkZtpnIQoMiFD2bvPzaeYn_ahAUckdXMX_IUg3f3IznK9lfCgjdsqFn5egtjpeOyKaHRX_e11A";

    public static final String WITH_SUBJECT              = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdWJqZWN0In0.8fhjRsjPswScvhcPnN56SHmpKtqO53EpCmCIZg0SDp_sjeaMEAV4GfqPK5spRYdSZPKfdjz1FxvF7re9Sc6nZg";

    private JwtTokens() {
        super();
    }

}
