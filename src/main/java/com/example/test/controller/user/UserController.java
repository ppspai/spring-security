package com.example.test.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.service.user.UserService;
import com.example.test.util.JwtTokenProvider;
import com.example.test.vo.Users;
import com.example.test.vo.user.JoinReqVO;
import com.example.test.vo.user.LoginReqVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    /*
    public UserController(UserService userService, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }
    */

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginReqVO vo) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Users user = userService.login(vo);

            result.put("user", user);

            String token = tokenProvider.createToken(user.getId());
            System.out.println("createToken : " + token);
            response.setHeader("Authorization", "BEARER " + token);

            // HttpSession session = request.getSession();
            // session.setAttribute("token", token);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/joinPage")
    public String joinPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = (String)session.getAttribute("token");
        System.out.println(token);

        if(token != null) {
            String userId = tokenProvider.validateToken(token);
            System.out.println(userId);
        }
        
        return "user/join";
    }

    @PostMapping("/join")
    public String join(JoinReqVO vo) {
        userService.join(vo);
        return "main";
    }

}
