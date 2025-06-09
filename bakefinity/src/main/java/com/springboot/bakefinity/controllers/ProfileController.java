package com.springboot.bakefinity.controllers;

import com.springboot.bakefinity.exceptions.ValidationException;
import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.services.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getProfile(@PathVariable int id) {
        UserDTO user = profileService.getUserProfile(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("{id}/address")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable int id) {
        AddressDTO address = profileService.getAddress(id);
        return ResponseEntity.ok(address);
    }
    @PostMapping(path = "{id}/address")
    public ResponseEntity<String> updateAddress(@PathVariable int id, @RequestBody AddressDTO address) {
        try{
            return ResponseEntity.ok(profileService.updateShippingInfo(id,address));
        }catch (ValidationException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PostMapping("{id}/accountDetails")
    public ResponseEntity<String> updateAccountDetails(@PathVariable int id,@RequestBody UserDTO user) {
        try{
            UserDTO updatedUser = profileService.updateAccountDetails(id,user);
            if(updatedUser != null){
                return ResponseEntity.ok("Success");
            }
        }catch (ValidationException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok("Success");
    }
    @PostMapping(path = "{id}/password",consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> changePassword(
            @PathVariable int id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        try{
            String status = profileService.changePassword(id,oldPassword,newPassword);
            return ResponseEntity.ok(status);
        }catch (ValidationException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @GetMapping("/username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        return ResponseEntity.ok(profileService.isUsernameTaken(username));
    }

}
