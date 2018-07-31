package com.shortener.controller;

import com.shortener.dto.CreateCustomUrlDto;
import com.shortener.service.SimpleUrlService;
import com.shortener.validators.CustomShortUrlDtoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

import static com.shortener.constants.Constants.SIMPLE_URL_REDIRECT_PREFIX;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger slf4jLogger = LoggerFactory.getLogger(IndexController.class);

    private SimpleUrlService simpleUrlService;

    @Autowired
    public IndexController(SimpleUrlService simpleUrlService) {
        this.simpleUrlService = simpleUrlService;
    }

    @ModelAttribute("createCustomUrlDto")
    public CreateCustomUrlDto getDto() {
        return new CreateCustomUrlDto();
    }


    @InitBinder("createCustomUrlDto")
    protected void Bind(WebDataBinder binder) {
        binder.setValidator(new CustomShortUrlDtoValidator());
    }

    @RequestMapping
    public String index(Model model, String shortUrl) {
        if (shortUrl != null) {
            model.addAttribute("shortUrl", SIMPLE_URL_REDIRECT_PREFIX + shortUrl);
        }
        slf4jLogger.info("Index page");
        return "index";
    }

    @PostMapping
    public String createSimpleShortUrl(@ModelAttribute("createCustomUrlDto") @Valid CreateCustomUrlDto createCustomUrlDto, BindingResult br, Model model, SessionStatus status) {
        if (br.hasErrors()) return index(model, null);
        String sortUrl = simpleUrlService.createSimpleShortUrl(createCustomUrlDto.getLongUrl().trim());
        status.setComplete();
        return index(model, sortUrl);
    }
}
