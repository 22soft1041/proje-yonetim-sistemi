package com.stajtask.stajtask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Controller
public class FrontendController {
    @RequestMapping("/")
    public String index() {
        return "forward:/index.html";
    }
}
