package uz.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.project.models.Role;
import uz.project.models.User;
import uz.project.services.UserService;
import uz.project.utilds.CustomResponse;
import uz.project.utilds.RegistrationException;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/client/register")
    public ResponseEntity<?> saveUser(@RequestBody User userInput) throws RegistrationException {
        var role = new Role();
        role.setName("ROLE_USER");
        var set = new HashSet<Role>();
        set.add(role);

        var user = saveUser(userInput, set);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> saveAdmin(@RequestBody User inputUser) throws RegistrationException {

        var role = new Role();
        role.setName("ROLE_ADMIN");
        var set = new HashSet<Role>();
        set.add(role);

        var user = saveUser(inputUser, set);
        return ResponseEntity.ok(user);
    }

    public User saveUser(User userInput, Set<Role> roles) throws RegistrationException {

        checkValidation(userInput);

        var user = new User();
        user.setUsername(userInput.getUsername());
        user.setName(userInput.getName());
        user.setSurname(userInput.getSurname());
        user.setPassword(userInput.getPassword());
        user.setDescription(userInput.getDescription());
        user.setPhoneNumber(userInput.getPhoneNumber());
        user.setRoles(roles);

        return userService.saveOrUpdate(user);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        checkValidation(user);

        var bool = !Objects.equals(user.getPassword(), userService.getUserById(user.getId()).getPassword());

        return ResponseEntity.ok(userService.update(user, bool));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) throws NotFoundException {
        if (!userService.doesUserExist(id))
            throw new NotFoundException("This user does not exist!");

        return ResponseEntity.ok(new CustomResponse(HttpStatus.OK.value(), "Item is deleted successfully!"));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws NotFoundException {
        if (!userService.doesUserExist(id))
            throw new NotFoundException("This user does not exist!");

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAllUserByName(@RequestParam("name") String name) {

        try {
            var list = userService.getAllUsersByName(name);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            var list = userService.getAllUsers();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    private Boolean checkPasswordLength(String password) {
        return password.length() >= 4;
    }

    private boolean checkWhereSingleWord(String s) {
        if (s == null || s.isEmpty())
            return false;

        return s.trim().contains(" ");
    }

    public void checkValidation(User userInput) throws RegistrationException {
        if (userInput.getUsername() == null || userInput.getUsername().isEmpty() || userInput.getPassword() == null || userInput.getPassword().isEmpty())
            throw new RegistrationException("Please fill all parameters!");

        if (checkWhereSingleWord(userInput.getUsername())) {
                throw new RegistrationException("Username can be only single word!");
        }

        if (checkWhereSingleWord(userInput.getPassword())) {
            throw new RegistrationException("Password can be only single word!");
        }

        if (!checkPasswordLength(userInput.getPassword())) {
            throw new RegistrationException("Your password should contain at least 4 characters!");
        }

        if (userService.doesUserExist(userInput.getUsername())  && userInput.getId() == null ) {
            throw new RegistrationException("This username has already been taken. Please select another one!");
        }

    }
}
