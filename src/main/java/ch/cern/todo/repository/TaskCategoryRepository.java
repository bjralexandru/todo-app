package ch.cern.todo.repository;

import ch.cern.todo.entity.TaskCategories;
import ch.cern.todo.entity.Tasks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategories, Long> {

    @Query("SELECT tc FROM TaskCategories tc")
    List<TaskCategories> getAllTasks();
    @Query("SELECT CASE WHEN COUNT(tc) > 0 THEN true ELSE false END FROM TaskCategories tc WHERE tc.categoryName = :categoryName")
    boolean existsByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT tc FROM TaskCategories tc WHERE tc.categoryName = :categoryName")
    TaskCategories getTaskCategoryByName(@Param("categoryName") String categoryName);

    @Modifying
    @Transactional
    @Query("UPDATE TaskCategories tc SET tc.categoryDescription = :newDescription WHERE tc.categoryId = :categoryId")
    void updateCategoryDescription(@Param("categoryId") Long categoryId, @Param("newDescription") String newDescription);


    @Modifying
    @Transactional
    @Query("DELETE FROM TaskCategories tc WHERE tc.categoryId = :categoryId")
    void deleteTaskByTaskId(Long categoryId);

}
