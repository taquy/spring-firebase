package fpt.fbiz.fremote.consts;

public class SecurityConstant {
    public static final String SUBJECT = "F-Remote";
    public static final String SECRET = "ScWm8nQLkKztCAb8YFEFHt58";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/public/users/sign-up";
}
