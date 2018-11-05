package com.contract.front.controller;

import com.contract.common.model.User;
import com.contract.common.param.KakaoParam;
import com.contract.common.param.KakaoTempParam;
import com.contract.common.param.LoginParam;
import com.contract.common.service.UserService;
import com.contract.common.util.LoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String form(){
        return "loginForm";
    }

    @RequestMapping(value = "/access", method = RequestMethod.POST)
    public String login(LoginParam loginParam, HttpSession httpSession) {
        User user = userService.findByUserId(loginParam.getUserId()); //이부분

        if (user.getUserId() == null)
            return "redirect:/login/form";
        if (user.getUserPassword().equals(loginParam.getUserPassword())) {
            LoginSession.setSession(httpSession,user);
            return "redirect:/index";
        }
        return "redirect:/login/form";
    }

    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public String detail(KakaoTempParam kakaoTempParam, Model model, HttpSession httpSession){
        User user = userService.findByUserId(kakaoTempParam.getKakaoId());
        /*
         * 유저 체크 : 성공 => 로그인 session 설정하고 index로 보내주기
         *           : 실패 => 아래 로직 실행
         * */
        if(user.getId()!=null){
            LoginSession.setSession(httpSession,user);
            return "redirect:/";
        }
        if(kakaoTempParam.getNickName().equals("undefined"))
            kakaoTempParam.setNickName("");
        if(kakaoTempParam.getKakaoEmail().equals("undefined"))
            kakaoTempParam.setKakaoEmail("");
        //"김종모"
        if(kakaoTempParam.getNickName()!="") {
            kakaoTempParam.setNickName(kakaoTempParam.getNickName().replaceAll("\"",""));
        if(kakaoTempParam.getKakaoEmail()!="")
            kakaoTempParam.setKakaoEmail(kakaoTempParam.getKakaoEmail().replaceAll("\"",""));
        }
        model.addAttribute("temp",kakaoTempParam);
        return "detailForm";
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
        if (userService.checkUserId(user.getUserId()))
        {
            return "redirect:/login/form";
        }
        userService.save(user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/kakaoJoin", method = RequestMethod.POST)
    public String join(KakaoParam kakaoParam) {

        User user = new User();
        user.setUserId(kakaoParam.getKakaoId());
        user.setUserName(kakaoParam.getName());
        user.setUserAddress(kakaoParam.getAddress());
        user.setUserEmail(kakaoParam.getKakaoEmail());
        user.setUserPhone(kakaoParam.getPhoneNumber());
        user.setUserPassword(UUID.randomUUID().toString());

        userService.save(user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("userID");
        httpSession.removeAttribute("id");

        return "redirect:/index";
    }
}
