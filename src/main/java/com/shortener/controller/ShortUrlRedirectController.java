package com.shortener.controller;

import com.shortener.exceptions.NullLongUrlResultException;
import com.shortener.service.CustomShortUrlService;
import com.shortener.service.SimpleUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ShortUrlRedirectController {

    private static final Logger slf4jLogger = LoggerFactory.getLogger(ShortUrlRedirectController.class);

    private CustomShortUrlService customShortUrlService;

    private SimpleUrlService simpleUrlService;

    @Autowired
    public ShortUrlRedirectController(CustomShortUrlService customShortUrlService, SimpleUrlService simpleUrlService) {
        this.customShortUrlService = customShortUrlService;
        this.simpleUrlService = simpleUrlService;
    }

    @RequestMapping("/s/{shortUrl}")
    public ModelAndView out(@PathVariable String shortUrl) throws NullLongUrlResultException {
        slf4jLogger.info("Redirect short url: " + shortUrl);
        String longUrl = customShortUrlService.getFullUrlByShortUrl(shortUrl);
        return new ModelAndView(new RedirectView(longUrl));
    }

    @RequestMapping("/f/{shortUrl}")
    public ModelAndView outSimpleUrl(@PathVariable String shortUrl) throws NullLongUrlResultException {
        slf4jLogger.info("Redirect simple short url: " + shortUrl);
        String longUrl = simpleUrlService.getLongUrl(shortUrl);
        return new ModelAndView(new RedirectView(longUrl));
    }
}
