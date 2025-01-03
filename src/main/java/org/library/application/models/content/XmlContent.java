package org.library.application.models.content;

public class XmlContent extends Content {
    public XmlContent(String data) {
        super(data, MimeType.XML);
    }

    @Override
    public String toString() {
        return "Content type: " + getContentType() +
                "\n" + getData();
    }
}
