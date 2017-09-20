package ru.javawebinar.topjava.web.user;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id")int id) {

        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        User user = new User(id, name, email, password, Role.ROLE_USER);
        if (user.isNew()) {
            super.create(user);
        }else{
            super.update(user,id);
        }
    }
    @PostMapping(value = "/{id}")
    public void update(@PathVariable("id") Integer id,
                       @RequestParam("name") String name,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password){
        User user = new User(id, name, email, password, Role.ROLE_USER);
        super.update(user, id);
    }
    @PostMapping(value = "/{id}/{enable}")
    public void enable(@PathVariable("id") Integer id,
                       @PathVariable("enable") boolean enable){
        super.enable(id,enable);
    }

}
