package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.exceptions.EmailAlreadyExistsException;
import com.springboot.bakefinity.exceptions.ResourceNotFoundException;
import com.springboot.bakefinity.exceptions.UnauthorizedException;
import com.springboot.bakefinity.mappers.UserMapper;
import com.springboot.bakefinity.model.dtos.LoginRequestDto;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.dtos.UserInterestsDTO;
import com.springboot.bakefinity.model.dtos.UserRegistrationRequestDTO;
import com.springboot.bakefinity.model.entities.*;
import com.springboot.bakefinity.repositories.interfaces.AddressRepo;
import com.springboot.bakefinity.repositories.interfaces.CategoryRepo;
import com.springboot.bakefinity.repositories.interfaces.UserInterestsRepo;
import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.UserInterestsService;
import com.springboot.bakefinity.services.interfaces.UserLoginService;
import com.springboot.bakefinity.services.interfaces.UserRegisterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserLoginService , UserRegisterService {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private AddressRepo addressRepository;
    @Autowired
    private CategoryRepo categoryRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInterestsRepo userInterestsRepo;

    @Autowired
    private UserInterestsService userInterestsService;
    private BCryptPasswordEncoder passwordEncoder;

    /****************************** Registration ***************************************/
    @Override
    @Transactional
    public void createUser(UserRegistrationRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("User with email '" + request.getEmail() + "' already exists");

        }
        try {
            Date birthDate = new SimpleDateFormat("MM-dd-yyyy").parse(request.getBirthdate());
            passwordEncoder=new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            System.out.println(request.getAuthorities());
            request.setAuthorities(new ArrayList<>());
            User user = userMapper.toUser(request, birthDate, hashedPassword);
            System.out.println("***************"+user);
            Authority authority = new Authority("ROLE_USER");
            authority.setUser(user);
            user.getAuthorities().add(authority);
            System.out.println("------------"+user);
            //user.getAuthorities().stream().forEach((a)->a.setUser(user));
            try {
                userRepository.save(user);
                userRepository.flush();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            Address address = new Address(user,
                    (request.getBuildingNo() == null || request.getBuildingNo().isBlank()) ? -1 : Integer.parseInt(request.getBuildingNo()),
                    request.getStreet(),
                    request.getCity(),
                    request.getCountry());

            addressRepository.save(address);

            if (request.getInterests() != null) {
                for (String interest : request.getInterests()) {

                        System.out.println(interest);


                    Category category = categoryRepository.findByName(interest)
                            .orElseThrow(() -> new RuntimeException("Category not found with Name: " + interest));

                    System.out.println("************************");
                    System.out.println(category);
                    System.out.println("************************");
                    System.out.println(user);
                    System.out.println("************************");

                    System.out.println(category.getName());
                    System.out.println("************************");
                   UserInterest userInterest=new UserInterest(new InterestsId(user.getId(),category.getId()));
                    System.out.println(userInterest.getId());
                    System.out.println("************************");
                    userInterestsService.createUserInterests(userInterest);
                }
            }

/************************************************************************ CART **************************************/
            // Initialize cart

        } catch (Exception e) {
            throw new RuntimeException("Registration failed", e);
        }
    }
    /********************************************************************/
    @Override
    public  boolean isUsernameAvailable(String username)
    {
       boolean isAvailable= userRepository.existsByUsername(username);
       return isAvailable;  // true >> exists     false >> does not
    }
    @Override
    public  boolean isEmailUnique(String email)
    {
        boolean isUnique= userRepository.existsByEmail(email);
        return isUnique; // true >> exists     false >> does not

    }
    /************************ AuthService (login , logout) ***************/
    @Override
    public UserDTO login(LoginRequestDto loginRequestDto, HttpSession session) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid Email or Password"));

        if (!BCrypt.checkpw(loginRequestDto.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid Email or Password");
        }

        UserDTO userDto = userMapper.toDto(user);
        session.setAttribute("user", userDto);
        return userDto;
    }

    @Override
    public void logout(HttpSession session)
    {
        //save cart by id in DB

        // then terminate session
        session.invalidate();
    }
}
