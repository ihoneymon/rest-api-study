package net.slipp.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: ihoneymon
 * Date: 13. 8. 2.
 * Time: 오후 7:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RootController {
    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }
}
