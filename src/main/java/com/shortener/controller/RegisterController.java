package com.shortener.controller;

import com.shortener.dto.UserRegisterDto;
import com.shortener.service.UserService;
import com.shortener.validators.UserRegisterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final Logger slf4jLogger = LoggerFactory.getLogger(RegisterController.class);

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder("registerModel")
    protected void bind(WebDataBinder binder) {
        binder.setValidator(new UserRegisterValidator(userService));
    }

    @ModelAttribute("registerModel")
    public UserRegisterDto getForm() {
        return new UserRegisterDto();
    }

    @GetMapping
    public String register(Model model) {
        slf4jLogger.info("Register page");
        return "register";
    }

    @PostMapping
    public String save(@ModelAttribute("registerModel") @Valid UserRegisterDto userRegisterDto, BindingResult br, Model model, SessionStatus status) {
        if (br.hasErrors()) {
            return register(model);
        }
        userService.saveNewUserAndEncodePassword(userRegisterDto);
        status.setComplete();
        return "redirect:/login";
    }

    @RequestMapping("/cancel")
    public String cancel(SessionStatus status) {
        status.setComplete();
        return "redirect:/register";
    }
}
