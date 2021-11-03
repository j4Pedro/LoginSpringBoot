package user.pedro.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import user.pedro.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String emal);
}
