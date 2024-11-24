package com.devkduck.user.service;


import com.devkduck.user.auth.PrincipalDetails;
import com.devkduck.user.domain.User;
import com.devkduck.user.domain.UserRegisterDTO;
import com.devkduck.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입
    public int register(UserRegisterDTO userRegisterDTO) {
        //유저 존재하는지 확인
        Optional<User> existUser = userMapper.findByEmail(userRegisterDTO.getEmail());
        if(existUser.isPresent()) {
            throw new UsernameNotFoundException(userRegisterDTO.getEmail() + "는 이미 존재합니다.");
        }
        userRegisterDTO.setPassword(bCryptPasswordEncoder.encode(userRegisterDTO.getPassword()));
        User user = User.createUser(userRegisterDTO);
        try{
            return userMapper.save(user);
        }
        catch (DuplicateKeyException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //로그인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> existUser = userMapper.findByEmail(email);
        if (existUser.isPresent()){
            System.out.println("사용자 확인 완료: " + email);
            System.out.println("사용자 확인 완료: " + existUser.get().getRole());
            return new PrincipalDetails(existUser.get());
        }
        else{
            throw new UsernameNotFoundException("존재 하지 않는 아이띠!: " + email);
        }

    }
}
