package in.pipeup.backend.campaign.campaign.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Platform {

    YOUTUBE("YouTube"),
    INSTAGRAM("Instagram"),
    FACEBOOK("Facebook"),
    LINKEDIN("LinkedIn"),
    X("X");

    private final String value;

    Platform(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Platform fromValue(String value) {
        for (Platform p : values()) {
            if (p.value.equalsIgnoreCase(value)
                    || p.name().equalsIgnoreCase(value)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Unknown platform: " + value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
