package simple.run.SimpleRunWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.run.SimpleRunWebApp.models.Training;

import java.util.List;

@Repository
public interface TraningRepository extends JpaRepository<Training, Long> {
    public List<Training> findAllByUserId(Long user_id);
}
