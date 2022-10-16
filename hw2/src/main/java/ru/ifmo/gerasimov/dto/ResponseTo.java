package ru.ifmo.gerasimov.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michael Gerasimov
 */
public class ResponseTo {
    private final SuccessTo success;
    private final ErrorTo error;

    @JsonCreator
    public ResponseTo(
            @JsonProperty("response") SuccessTo success,
            @JsonProperty("error") ErrorTo error
    ) {
        this.success = success;
        this.error = error;
    }

    public SuccessTo getSuccess() {
        return success;
    }

    public ErrorTo getError() {
        return error;
    }
}
