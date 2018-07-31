package com.shortener.service;

import com.shortener.exceptions.NullLongUrlResultException;

/**
 * The interface Simple url service.
 */
public interface SimpleUrlService {

    /**
     * Gets long url.
     *
     * @param shortUrl the shot url
     * @return the long url
     * @throws NullLongUrlResultException the null long url result exception
     */
    String getLongUrl(String shortUrl) throws NullLongUrlResultException;

    /**
     * Create simple short url string.
     *
     * @param shortUrl the shot url
     * @return the string
     */
    String createSimpleShortUrl(String shortUrl);
}
