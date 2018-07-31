package com.shortener.service;

import com.shortener.entity.CustomShortUrl;
import com.shortener.exceptions.NullLongUrlResultException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Custom short url service.
 */
public interface CustomShortUrlService {

    /**
     * Create custom short url.
     *
     * @param longUrl the long url
     */
    void createCustomShortUrl(String longUrl);

    /**
     * Gets full url by short url.
     *
     * @param shortUrl the short url
     * @return the full url by short url
     * @throws NullLongUrlResultException the null long url result exception
     */
    String getFullUrlByShortUrl(String shortUrl) throws NullLongUrlResultException;

    /**
     * Find page page.
     *
     * @param pageable the pageable
     * @return the page
     */
    Page<CustomShortUrl> findPage(Pageable pageable);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);
}
