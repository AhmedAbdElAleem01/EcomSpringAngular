package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.exceptions.ResourceNotFoundException;
import com.springboot.bakefinity.model.entities.Category;
import com.springboot.bakefinity.model.entities.InterestsId;
import com.springboot.bakefinity.model.entities.User;
import com.springboot.bakefinity.model.entities.UserInterest;
import com.springboot.bakefinity.repositories.interfaces.CategoryRepo;
import com.springboot.bakefinity.repositories.interfaces.UserInterestsRepo;
import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.UserInterestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInterestServiceImpl implements UserInterestsService {


    @Autowired
    private UserInterestsRepo userInterestRepository;

    @Override
    public UserInterest createUserInterests(UserInterest userInterests) {

        return userInterestRepository.save(userInterests);

    }
}