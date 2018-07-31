package com.shortener.entity;

import com.shortener.entity.abstractClasses.UrlAbstract;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "simple_short_url")
public class SimpleShortUrl extends UrlAbstract {

    @Temporal(TemporalType.DATE)
    private Date removeDate;

    public SimpleShortUrl() {
    }

    public SimpleShortUrl(Date removeDate) {
        this.removeDate = removeDate;
    }

    public SimpleShortUrl(String longUrl, Date removeDate) {
        super(longUrl);
        this.removeDate = removeDate;
    }

    public SimpleShortUrl(String longUrl) {
        super(longUrl);
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }
}
