package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AddressRepo extends JpaRepository<Address, Integer> {
    Optional<Address> findByUser_Id(int id);
}