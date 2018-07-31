package com.shortener.service.implementation;

import com.shortener.entity.CustomShortUrl;
import com.shortener.exceptions.NullLongUrlResultException;
import com.shortener.repository.CustomShortUrlRepository;
import com.shortener.service.CustomShortUrlService;
import com.shortener.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static com.shortener.constants.Constants.REDIRECT_PREFIX;

@Service
public class CustomShortUrlServiceImplementation implements CustomShortUrlService {

    private static final Logger slf4jLogger = LoggerFactory.getLogger(CustomShortUrlServiceImplementation.class);

    private CustomShortUrlRepository customShortUrlRepository;

    @Autowired
    public CustomShortUrlServiceImplementation(CustomShortUrlRepository customShortUrlRepository) {
        this.customShortUrlRepository = customShortUrlRepository;
    }

    @Override
    public void createCustomShortUrl(String longUrl) {
        slf4jLogger.info("Creating new short url from long url: " + longUrl);
        CustomShortUrl customShortUrl = new CustomShortUrl(longUrl);
        customShortUrl.setUser(UserUtil.getUser());
        Long id = customShortUrlRepository.saveAndFlush(customShortUrl).getId();
        String encodedShortUrl = Base64.getEncoder().encodeToString(id.toString().getBytes());
        customShortUrlRepository.setSortUrl(REDIRECT_PREFIX + encodedShortUrl, id);
    }

    @Override
    public String getFullUrlByShortUrl(String shortUrl) throws NullLongUrlResultException {
        slf4jLogger.info("Getting full url");
        CustomShortUrl customShortUrl = customShortUrlRepository.findByShortUrl(REDIRECT_PREFIX + shortUrl);
        if (customShortUrl == null) {
            slf4jLogger.info("No long url found for short url: " + shortUrl);
            throw new NullLongUrlResultException();
        }
        customShortUrlRepository.incrementCountOfClicks(customShortUrl.getId());
        return customShortUrl.getLongUrl();
    }

    @Override
    public Page<CustomShortUrl> findPage(Pageable pageable) {
        return customShortUrlRepository.findAll(filterByUserId(), pageable);
    }

    @Override
    public void delete(Long id) {
        customShortUrlRepository.deleteById(id);
    }

    private Specification<CustomShortUrl> filterByUserId() {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("user")), UserUtil.getUser());
    }
}
