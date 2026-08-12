package in.pipeup.backend.creator.integration;

public class YoutubeUtil {

    private YoutubeUtil() {
    }

    public static String extractHandle(String youtubeUrl) {

        if (youtubeUrl == null || youtubeUrl.isBlank()) {
            return null;
        }

        String value = youtubeUrl.trim();
        int index = value.indexOf("@");

        if (index == -1) {
            return null;
        }

        return value.substring(index + 1);
    }

}
