package com.boot.contract.controller;

import com.boot.contract.model.User;
import com.boot.contract.param.LoginParam;
import com.boot.contract.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/form")
    public String form() {
        return "loginForm";
    }

    @RequestMapping(value = "/access", method = RequestMethod.POST)
    public String login(LoginParam loginParam, HttpSession httpSession) {
        log.info("param={}", loginParam);
        User user = userService.findById(loginParam.getId()); //이부분

        if (user.getUserId() == null)
            return "redirect:/login/form";
        if (user.getUserPassword().equals(loginParam.getUserPassword())) {
            httpSession.setAttribute("userID", user.getUserId());  //이부분은 id,pw로 로그인하는 부분
            return "redirect:/index";
        }
        return "redirect:/login/form";
    }

    @RequestMapping(value = "/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(User user) {
        /*
         * 중복 ID체크하는 부분 valid 어노테이션 붙여서 검증하는 부분까지 추가하기.
         * */
        if (userService.checkUserId(user.getId()) {
            return "redirect:/login/form";
        }
        userService.save(user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("userID");
        return "redirect:/index";
    }
}
