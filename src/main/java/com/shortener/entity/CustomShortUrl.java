package com.shortener.entity;

import com.shortener.entity.abstractClasses.UrlAbstract;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "custom_short_url")
public class CustomShortUrl extends UrlAbstract {

    @Column(name = "short_url_clicks")
    private Long shortUrlClicks = 0L;

    @Temporal(TemporalType.DATE)
    private Date dateCreation = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public CustomShortUrl() {
    }

    public CustomShortUrl(String longUrl) {
        super(longUrl);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getShortUrlClicks() {
        return shortUrlClicks;
    }

    public void setShortUrlClicks(Long shortUrlClicks) {
        this.shortUrlClicks = shortUrlClicks;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
