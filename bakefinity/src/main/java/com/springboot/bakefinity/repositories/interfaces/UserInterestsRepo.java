package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.dtos.UserInterestsDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserInterestsRepo {
    boolean createUserInterests(UserInterestsDTO userInterests) throws SQLException;
    List<UserInterestsDTO> getUserInterests(int userId) throws SQLException;
}