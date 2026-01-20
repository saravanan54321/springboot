package com.example.demo.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 1️⃣ Create User
    @PostMapping("/post")
    public UserDTO createUser(@RequestBody UserDTO userdto) {

        User u = new User();
        u.setName(userdto.getName());
        userRepository.save(u);

        return new UserDTO(u.getId(),u.getName());
    }


    // 3️⃣ Get User by ID
    @GetMapping("/get/{id}")
    public UserDTO getUserById(@PathVariable Long id) {


        Optional<User> ou =  userRepository.findById(id);
        if(!ou.isPresent()){
            throw new UserNotFoundException("User not found in db ");
        }

        return  new UserDTO(ou.get().getId(), ou.get().getName());
    }

    // 2️⃣ Get All Users
    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {

        List <UserDTO> allUsers = new ArrayList<>();
        List<User> lu =userRepository.findAll();

        for(User l:lu){
            allUsers.add(new UserDTO(l.getId(),l.getName()));
        }

        return allUsers;
    }


    // 4️⃣ Update User
    @PutMapping("/put/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDetails) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        User user = optionalUser.get();
        user.setName(userDetails.getName());


        User lk = userRepository.save(user);
        return  new UserDTO(lk.getId(),lk.getName());

    }


    // 5️⃣ Delete User
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted successfully";
        }

        return "User not found";
    }
}
