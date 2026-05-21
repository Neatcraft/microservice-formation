package fr.neatcraft.championship.security;

public final class MdcKeys {
    public static final String CORRELATION_ID = "correlationId";
    public static final String USER_ID = "userId";
    public static final String USERNAME = "username";

    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    private MdcKeys() {}
}