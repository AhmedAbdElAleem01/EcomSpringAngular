package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.UserInterestsDTO;
import com.springboot.bakefinity.model.entities.Category;
import com.springboot.bakefinity.model.entities.InterestsId;
import com.springboot.bakefinity.model.entities.User;
import com.springboot.bakefinity.model.entities.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
public interface UserInterestsRepo extends JpaRepository<UserInterest, InterestsId> {

//    boolean createUserInterests(InterestsId userInterests);

    Set<UserInterest> getUserInterestsByIdUserId(int userId);
}