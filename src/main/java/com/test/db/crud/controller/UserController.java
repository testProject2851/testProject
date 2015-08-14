package com.test.db.crud.controller;

import com.test.db.crud.data.SearchObject;
import com.test.db.crud.domain.User;
import com.test.db.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Контроллер для отображения информации по клиенту на UI
 * Реализует CRUD операции над объектом типа {@link com.test.db.crud.domain.User}
 *
 * @see com.test.db.crud.domain.User
 */

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    private final int RECORDS_ON_PAGE = 10;


    @RequestMapping({"/", "", "/show"})
    public String showUsers(Model model,

                            @RequestParam(required = false) String nameQuery,
                            @RequestParam(required = false) Integer age,
                            @RequestParam(required = false) Boolean admin,
                            @RequestParam(required = false) Long id,
                            @RequestParam(required = false) String createdDate,

                            @RequestParam(required = false, defaultValue = "1") int page) {

        List<User> users;

        SearchObject searchObject = new SearchObject(id, nameQuery, age, admin, createdDate);

        userService.setPerPageValue(RECORDS_ON_PAGE);
        if (searchObject.nothingToSearch()) {
            users = userService.getUserList(page);
        } else {

            users = userService.getUserListBySearchObject(searchObject, page);
        }
        model.addAttribute("users", users);

        model.addAttribute("pages", (int) Math.ceil(userService.total() * 1.0 / RECORDS_ON_PAGE));

        model.addAttribute("currentPage", page);

        model.addAttribute("search", searchObject);

        return "show";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateUser(Model model) {

        model.addAttribute("user", new User());
        return "user";
    }


    @RequestMapping(value = "/processUser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user, Model model) {

        userService.processUser(user);
        return "redirect:/";
    }


    @RequestMapping("/edit/{userId}")
    public String updateUser(@PathVariable("userId") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/create";
        }

        model.addAttribute("user", user);
        return "user";
    }


    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long id, Model model) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}