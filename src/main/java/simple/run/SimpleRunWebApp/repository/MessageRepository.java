package simple.run.SimpleRunWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.run.SimpleRunWebApp.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
