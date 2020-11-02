package taco.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Taco")
public class Taco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @ManyToMany
    @Size(min=2, message="You must choose at least 2 ingredient")
    private List<Ingredient> ingredients;

    private Date createdAt;

    @PrePersist
    void createdAt(){
        this.createdAt= new Date();
    }

}
