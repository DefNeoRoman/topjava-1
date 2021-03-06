package ru.javawebinar.topjava.web.user;


import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.get(authorizedUser.getId());
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.delete(authorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.ValidatedRestUI.class) @RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.update(userTo, authorizedUser.getId());
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Русский текст";
    }
}