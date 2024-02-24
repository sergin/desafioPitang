package com.sergioalves.desafiopitang.controllers;

import com.sergioalves.desafiopitang.domain.product.User;
import com.sergioalves.desafiopitang.domain.product.UserDTO;
import com.sergioalves.desafiopitang.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;


    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDTO productData){
        User newUser = this.service.insert(productData);
        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = this.service.getAll();
        return ResponseEntity.ok().body(users);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") String id, @RequestBody UserDTO productData){
        User updatedUser = this.service.update(id, productData);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
