package com.sergioalves.desafiopitang.services;

import com.sergioalves.desafiopitang.domain.category.exceptions.CarNotFoundException;
import com.sergioalves.desafiopitang.domain.product.User;
import com.sergioalves.desafiopitang.domain.product.UserDTO;
import com.sergioalves.desafiopitang.domain.product.exceptions.UserNotFoundException;
import com.sergioalves.desafiopitang.repositories.UserRepository;
import com.sergioalves.desafiopitang.services.aws.AwsSnsService;
import com.sergioalves.desafiopitang.services.aws.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CarService carService;
    private final UserRepository repository;
    private final AwsSnsService snsService;

    public User insert(UserDTO userData){
        this.carService.getById(userData.login())
                .orElseThrow(CarNotFoundException::new);
        User newUser = new User(userData);

        this.repository.save(newUser);

        this.snsService.publish(new MessageDTO(newUser.toString()));

        return newUser;
    }

    public User update(String id, UserDTO userData){
        User user = this.repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        this.carService.getById(userData.login())
                .orElseThrow(CarNotFoundException::new);


        this.repository.save(user);

        this.snsService.publish(new MessageDTO(user.toString()));

        return user;
    }

    public void delete(String id){
        User user = this.repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        this.repository.delete(user);
        this.snsService.publish(new MessageDTO(user.deleteToString()));
    }

    public List<User> getAll(){
        return this.repository.findAll();
    }
}
