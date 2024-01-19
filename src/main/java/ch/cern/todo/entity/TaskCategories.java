package ch.cern.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "task_categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = "CategoryName")
})
public class TaskCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank
    @Size(max=100)
    private String categoryName;

    @Size(max=500)
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tasks> tasks;

    public TaskCategories() {

    }
}

