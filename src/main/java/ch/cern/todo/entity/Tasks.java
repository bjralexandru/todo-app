package ch.cern.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @NotBlank
    @Size(max=100)
    private String taskName;

    @Size(max=500)
    private String taskDescription;

    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "category_id")

    private TaskCategories category;

    public Tasks() {

    }

    public String getCategory() {
        return category.getCategoryName();
    }
}
