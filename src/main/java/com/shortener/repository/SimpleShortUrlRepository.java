package com.shortener.repository;

import com.shortener.entity.SimpleShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

public interface SimpleShortUrlRepository extends JpaRepository<SimpleShortUrl, Long> {

    SimpleShortUrl findByShortUrl(String shortUrl);

    @Query("UPDATE SimpleShortUrl c SET c.shortUrl=?1 WHERE c.id=?2")
    @Modifying
    @Transactional
    void setSortUrl(String s, Long id);

    @Query("DELETE FROM SimpleShortUrl c WHERE c.removeDate <= ?1")
    @Modifying
    @Transactional
    void removeOverdueUrls(Date overdueDate);
}
