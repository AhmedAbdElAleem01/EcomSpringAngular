package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.UserInterestsDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserInterestsService {
    boolean createUserInterests(UserInterestsDTO userInterests) ;


    List<UserInterestsDTO> getUserInterests(int userId);
}
