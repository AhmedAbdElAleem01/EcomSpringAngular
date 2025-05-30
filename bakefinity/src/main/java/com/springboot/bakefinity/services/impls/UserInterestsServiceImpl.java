package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.model.dtos.UserInterestsDTO;
import com.springboot.bakefinity.model.entities.Category;
import com.springboot.bakefinity.model.entities.User;
import com.springboot.bakefinity.repositories.interfaces.CategoryRepo;
import com.springboot.bakefinity.repositories.interfaces.UserInterestsRepo;
import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.UserInterestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserInterestsServiceImpl implements UserInterestsService {

    private final UserRepo userRepository;
    private final CategoryRepo categoryRepository;

    @Autowired
    public UserInterestsServiceImpl(UserRepo userRepository, CategoryRepo categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public boolean createUserInterests(UserInterestsDTO dto) {
        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        Optional<Category> catOpt = categoryRepository.findById(dto.getCategoryId());

        if (userOpt.isEmpty() || catOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        Category category = catOpt.get();

        // add to user's categories
        boolean added = user.getCategories().add(category); // won't add if already present

        // save the user (not always necessary if @Transactional + managed entity)
        userRepository.save(user); // optional

        return added;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInterestsDTO> getUserInterests(int userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return Collections.emptyList();
        }

        Set<Category> categories = userOpt.get().getCategories();

        return categories.stream()
                .map(c -> new UserInterestsDTO(userId, c.getId()))
                .collect(Collectors.toList());
    }
}

