package ch.cern.todo.repository;

import ch.cern.todo.entity.Tasks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

    @Query("SELECT t FROM Tasks t")
    List<Tasks> getAllTasks();
    @Query("SELECT t FROM Tasks t WHERE t.taskId = :taskId")
    Optional<Tasks> findByTaskId(@Param("taskId") Long taskId);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Tasks t WHERE t.taskId = :taskId")
    boolean existsByTaskId(@Param("taskId") Long taskId);

    @Modifying
    @Transactional
    @Query("UPDATE Tasks t SET t.taskDescription = :newDescription WHERE t.id = :taskId")
    void updateDescription(@Param("taskId") Long taskId, @Param("newDescription") String newDescription);

    @Modifying
    @Transactional
    @Query("UPDATE Tasks t SET t.taskName = :newName WHERE t.id = :taskId")
    void updateName(@Param("taskId") Long taskId, @Param("newName") String newName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Tasks t WHERE t.taskId = :taskId")
    void deleteTaskByTaskId(Long taskId);
}
