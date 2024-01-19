package ch.cern.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCategoryDto {
    @NotBlank
    @Size(max=100)
    private String categoryName;

    @Size(max=500)
    private String categoryDescription;
}
