package com.shortener.entity.abstractClasses;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class UrlAbstract extends AbstractId {

    @Column(name = "long_url", columnDefinition = "TEXT")
    private String longUrl;

    @Column(name = "shortUrl")
    private String shortUrl;

    public UrlAbstract() {
    }

    public UrlAbstract(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
