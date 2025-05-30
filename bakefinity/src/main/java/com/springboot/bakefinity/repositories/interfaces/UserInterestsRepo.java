package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.UserInterestsDTO;
import com.springboot.bakefinity.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;

public interface UserInterestsRepo {
    /* remove throws sql exception */
    boolean createUserInterests(UserInterestsDTO userInterests) ;
    List<UserInterestsDTO> getUserInterests(int userId);
}