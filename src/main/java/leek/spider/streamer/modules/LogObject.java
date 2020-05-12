package leek.spider.streamer.modules;

import lombok.Builder;
import lombok.Value;

@Value
public class LogObject {
    private long timestamp;
    private String userMessage;
    private String relatedObjectId;
    private StackTraceElement[] stackTrace;
    private String exceptionMessage;

    @Builder
    LogObject(String relatedObjectId, String userMessage, String exceptionMessage, StackTraceElement[] stackTrace) {
        this.timestamp = System.currentTimeMillis();
        this.relatedObjectId = relatedObjectId;
        this.userMessage = userMessage;
        this.exceptionMessage = exceptionMessage;
        this.stackTrace = stackTrace;
    }
}
