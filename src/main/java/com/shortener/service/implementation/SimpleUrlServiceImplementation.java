package com.shortener.service.implementation;

import com.shortener.entity.SimpleShortUrl;
import com.shortener.exceptions.NullLongUrlResultException;
import com.shortener.repository.SimpleShortUrlRepository;
import com.shortener.service.SimpleUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.shortener.constants.Constants.SIMPLE_URL_REDIRECT_PREFIX;

@Service
public class SimpleUrlServiceImplementation implements SimpleUrlService {

    private static final Logger slf4jLogger = LoggerFactory.getLogger(SimpleUrlServiceImplementation.class);

    private SimpleShortUrlRepository simpleShortUrlRepository;

    @Autowired
    public SimpleUrlServiceImplementation(SimpleShortUrlRepository simpleShortUrlRepository) {
        this.simpleShortUrlRepository = simpleShortUrlRepository;
    }

    @Override
    public String getLongUrl(String shortUrl) throws NullLongUrlResultException {
        slf4jLogger.info("Get get long url by short url: " + shortUrl);
        SimpleShortUrl simpleShortUrl = simpleShortUrlRepository.findByShortUrl(SIMPLE_URL_REDIRECT_PREFIX + shortUrl);
        if (simpleShortUrl == null) {
            slf4jLogger.info("No long url found for short url: " + shortUrl);
            throw new NullLongUrlResultException();
        }
        return simpleShortUrl.getLongUrl();
    }

    @Override
    public String createSimpleShortUrl(String shortUrl) {
        return createShortUrl(shortUrl);
    }

    @PostConstruct
    private void removeOldUrls() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                simpleShortUrlRepository.removeOverdueUrls(new Date());
            }
        };

        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 43200000; //12 hours

        timer.scheduleAtFixedRate(task, delay, intervalPeriod);
    }

    private Date getDateForSimpleShortUrl() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return c.getTime();
    }

    private String createShortUrl(String longUrl) {
        Long id = simpleShortUrlRepository.saveAndFlush(new SimpleShortUrl(longUrl, getDateForSimpleShortUrl())).getId();
        String encodedShortUrl = Base64.getEncoder().encodeToString(id.toString().getBytes());
        simpleShortUrlRepository.setSortUrl(SIMPLE_URL_REDIRECT_PREFIX + encodedShortUrl, id);
        return encodedShortUrl;
    }

}
