package ch.cern.todo.dto.response;

import ch.cern.todo.entity.TaskCategories;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCategoryUpdateInfoDto {
    private String categoryName;
    private String categoryDescription;


    public TaskCategoryUpdateInfoDto(String categoryName, String categoryDescription) {
        this.categoryDescription = categoryDescription;
        this.categoryName = categoryName;
    }
}
