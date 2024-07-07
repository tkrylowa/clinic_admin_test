package com.springlessons.securityform.controller;

import com.springlessons.securityform.entity.ApplicationUser;
import com.springlessons.securityform.entity.Token;
import com.springlessons.securityform.exception.AccountException;
import com.springlessons.securityform.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/registration")
    public String registration(ApplicationUser applicationUser) {
        return "account/registration";
    }

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @PostMapping("/registration")
    public String createAccount(ContentManagerUser user, Model model) {
        try {
            accountService.registration(user);
            return "redirect:/account/login";
        } catch (AccountException e) {
            model.addAttribute("error", e.getMessage());
            return "account/registration";
        }
    }

    @PostMapping("/login?failed")
    public String createAccount() {
        return "redirect:/account/login";
    }

    @PostMapping("/logout")
    public String createAccount() {
        return "redirect:/account/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public Token loginAccount(@RequestParam("application_user_username") String username,
                              @RequestParam("application_user_password") String password) {
        try {
            return accountService.loginAccount(username, password);
        } catch (AccountException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
