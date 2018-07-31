package com.shortener.controller.userController;

import com.shortener.dto.CreateCustomUrlDto;
import com.shortener.service.CustomShortUrlService;
import com.shortener.validators.CustomShortUrlDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

import static com.shortener.util.ParamBuilder.getParams;

@Controller
@RequestMapping("/user/shortUrl")
public class CustomShotUrlController {

    private CustomShortUrlService customShortUrlService;

    @Autowired
    public CustomShotUrlController(CustomShortUrlService customShortUrlService) {
        this.customShortUrlService = customShortUrlService;
    }

    @ModelAttribute("createCustomUrlDto")
    public CreateCustomUrlDto getDto(){
        return new CreateCustomUrlDto();
    }

    @InitBinder("createCustomUrlDto")
    protected void Bind(WebDataBinder binder){
        binder.setValidator(new CustomShortUrlDtoValidator());
    }

    @RequestMapping
    public String show(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("page", customShortUrlService.findPage(pageable));
        return "user/customUrl";
    }

    @PostMapping
    public String createShortUrl(@ModelAttribute("createCustomUrlDto") @Valid CreateCustomUrlDto createCustomUrlDto, BindingResult br, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, SessionStatus status){
        if (br.hasErrors()) return show(model, pageable);
        customShortUrlService.createCustomShortUrl(createCustomUrlDto.getLongUrl().trim());
        status.setComplete();
        return "redirect:/user/shortUrl"+getParams(pageable);
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        customShortUrlService.delete(id);
        return "redirect:/user/shortUrl"+getParams(pageable);
    }

    @RequestMapping("/cancel")
    public String cancel(SessionStatus status){
        status.setComplete();
        return "redirect:/user/shortUrl";
    }
}
