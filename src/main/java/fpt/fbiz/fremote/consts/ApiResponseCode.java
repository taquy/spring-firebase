package fpt.fbiz.fremote.consts;

public enum ApiResponseCode {
    SUCCESS("Success"),
    ERROR("Error");

    public final String label;

    private ApiResponseCode(String label) {
        this.label = label;
    }
}
