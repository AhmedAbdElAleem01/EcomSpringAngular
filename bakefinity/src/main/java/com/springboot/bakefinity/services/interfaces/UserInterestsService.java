package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.UserInterestsDTO;
import com.springboot.bakefinity.model.entities.InterestsId;
import com.springboot.bakefinity.model.entities.UserInterest;

import java.sql.SQLException;
import java.util.List;

public interface UserInterestsService {
    UserInterest createUserInterests(UserInterest userInterests);
}
