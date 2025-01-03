package org.library.application.models.content;

import lombok.Getter;

@Getter
public enum MimeType {
    JSON("application/json"),
    XML("application/xml");

    private final String type;

    MimeType(String type) {
        this.type = type;
    }
}
