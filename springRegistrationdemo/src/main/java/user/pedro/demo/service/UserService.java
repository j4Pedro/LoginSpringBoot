package user.pedro.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import user.pedro.demo.model.User;
import user.pedro.demo.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto userRegistrationDto);
}
