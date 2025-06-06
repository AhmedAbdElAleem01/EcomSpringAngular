package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.LoginRequestDto;
import com.springboot.bakefinity.model.dtos.UserDTO;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public interface UserLoginService {

    //public Optional<UserDTO> login(String email , String password);

    /************************ AuthService (login , logout) ***************/
    UserDTO login(LoginRequestDto loginRequestDto, HttpSession session);
    void logout(HttpSession session);
}
