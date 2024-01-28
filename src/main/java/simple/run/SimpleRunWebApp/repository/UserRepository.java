package simple.run.SimpleRunWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.run.SimpleRunWebApp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
