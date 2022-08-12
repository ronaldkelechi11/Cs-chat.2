package com.bms.cschat.classes;

import java.net.URI;

public class News {

    String content,by;
    URI image_url;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public URI getImage_url() {
        return image_url;
    }

    public void setImage_url(URI image_url) {
        this.image_url = image_url;
    }

}
