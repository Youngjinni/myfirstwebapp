package com.in28minutes.springboot.myfirstwebapp.login;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class LoginController {


    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    private AuthenticationService authenticationService;

    @RequestMapping(value = "Login", method = RequestMethod.GET)
    public String gotoLogin() {

        return "login";
    }
    @RequestMapping(value="Login", method = RequestMethod.POST)
    public String gotoWelcome(@RequestParam String name,
                                   @RequestParam String password, ModelMap model) {

        if(authenticationService.authenticate(name, password)) {
            model.put("name", name);
            model.put("password", password);
            return "welcome";

        }
        else {
            model.put("errorMessage", "Invalid username and/or password. please try again");
            return "login";
        }
    }
}
