package org.id2k1149.project_v8.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/polls")
    public String getPolls() {
        return "polls";
    }
}
