package com.bms.cschat.classes;


public class News {

    String content,reporter;
    String image;

    public News(String content, String reporter, String image) {
        this.content = content;
        this.reporter = reporter;
        this.image = image;
    }

    public News(){

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
