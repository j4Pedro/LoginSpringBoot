package user.pedro.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import user.pedro.demo.model.User;
import user.pedro.demo.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto userRegistrationDto);

	List<User> getAllUsers();

	Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	void createUser(User user);

}
