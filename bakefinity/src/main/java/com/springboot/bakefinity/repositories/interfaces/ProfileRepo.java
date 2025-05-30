package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepo extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET creditLimit = :creditLimit WHERE id = :userId", nativeQuery = true)
    int updateCreditLimit(Integer userId , Double creditLimit); // update is DML operation and Spring does not fetch or return an entity or DTO from it, can return void or int/Integer only

//    Optional<UserDTO> updateCreditLimit(UserDTO user , double creditLimit);
//    Optional<AddressDTO> updateShippingAddress(UserDTO user, String country, String city, String street, String bNo);
//    Optional<UserDTO> updateAccountDetails(UserDTO user, String username, String job, String email);
//    Optional<UserDTO> updatePassword(UserDTO user, String newPass);
//    boolean isUsernameTaken(String username);
//    Optional<UserDTO> updatePhoneNumber(UserDTO user, String mobile);
}