package com.api.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.backend.configs.security.UserDetailsServiceImpl;
import com.api.backend.dtos.UserDto;
import com.api.backend.enums.RoleName;
import com.api.backend.models.RoleModel;
import com.api.backend.models.UserModel;
import com.api.backend.services.RoleService;

@SpringBootApplication
@RestController
public class BackendApplication {

	public final UserDetailsServiceImpl userService;
	public final RoleService roleService;

	public BackendApplication(UserDetailsServiceImpl userService, RoleService roleService){
		this.userService = userService;
		this.roleService = roleService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("password"));
	}

	@GetMapping("/")
	public String index() {
		return "Olá Mundo!";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	
	@PostMapping("/signup")
    public ResponseEntity<UserModel> setNewUser(@RequestBody @Valid UserDto newUser){
        if(userService.existsByUsername(newUser.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userService.findByUsername(newUser.getUsername()).get());
        }
        var _password = new BCryptPasswordEncoder().encode(newUser.getPassword());
        UserModel _newUser = new UserModel(newUser.getUsername(), _password);
        List<RoleModel> roles = new ArrayList<>();
        roles.add(roleService.getRoleRepository().findByRoleName(RoleName.ROLE_USER).get());
        _newUser.setRoles(roles);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(_newUser));
    }
}