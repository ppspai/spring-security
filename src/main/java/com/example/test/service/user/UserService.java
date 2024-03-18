package com.example.test.service.user;

import com.example.test.vo.Users;
import com.example.test.vo.user.JoinReqVO;
import com.example.test.vo.user.LoginReqVO;

public interface UserService {

    public int join(JoinReqVO vo);
    public Users login(LoginReqVO vo);
    
}
