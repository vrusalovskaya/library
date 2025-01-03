package org.library.application.models.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Content {
    private final String data;
    private final MimeType contentType;
}
