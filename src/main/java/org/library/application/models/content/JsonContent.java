package org.library.application.models.content;

public class JsonContent extends Content {

    public JsonContent(String data) {
        super(data, MimeType.JSON);
    }

    @Override
    public String toString() {
        return "Content type: " + getContentType() +
                "\n" + getData();
    }
}
