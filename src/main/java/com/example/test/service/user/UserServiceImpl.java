package com.example.test.service.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.test.common.CmmnDao;
import com.example.test.vo.Users;
import com.example.test.vo.user.JoinReqVO;
import com.example.test.vo.user.LoginReqVO;

@Service
public class UserServiceImpl extends CmmnDao implements UserService{

    private final BCryptPasswordEncoder encoder;
    
    public UserServiceImpl(SqlSession sqlSession, BCryptPasswordEncoder encoder) {
        super(sqlSession);
        this.encoder = encoder;
    }
    

    @Override
    public int join(JoinReqVO vo) {

        int userNo = selectOne("user.usersMaxNo");

        String pwd = encoder.encode(vo.getPassword());
        System.out.println(pwd);
        Users users = Users.builder()
                        .no(userNo + 1)
                        .id(vo.getId())
                        .password(pwd)
                        .name(vo.getName())
                        .email(vo.getEmail())
                        .build();

        insert("user.join", users);
        return userNo;
    }

    @Override
    public Users login(LoginReqVO vo) {

        Users users = selectOne("user.findById", vo);
        
        //boolean flag = encoder.matches(vo.getPassword(), users.getPassword());

        /*
        Authentication authentication = tokenProvider.authenticate(new UsernamePasswordAuthenticationToken(vo.getId(), vo.getPassword()));
        
        if(authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        */

        return users;
    }

   

    
}
