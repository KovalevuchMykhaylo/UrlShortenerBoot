package com.shortener.repository;

import com.shortener.entity.CustomShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomShortUrlRepository extends JpaRepository<CustomShortUrl, Long>, JpaSpecificationExecutor<CustomShortUrl> {

    @Query("UPDATE CustomShortUrl c SET c.shortUrl=?1 WHERE c.id=?2")
    @Modifying
    @Transactional
    void setSortUrl(String shortUrl, Long customShortUrlId);

    @Query("UPDATE CustomShortUrl c SET c.shortUrlClicks = c.shortUrlClicks + 1 WHERE c.id=?1")
    @Modifying
    @Transactional
    void incrementCountOfClicks(Long customShortUrlId);

    CustomShortUrl findByShortUrl(String shortUrl);
}
