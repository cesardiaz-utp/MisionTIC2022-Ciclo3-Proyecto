package co.edu.utp.misiontic.cesardiaz.videotienda.model.entity;

// JPA
import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    private String code;
    
    private String name;
    private String description;
    private Integer length;
    private Double value;
    private String imageUrl;

    @ManyToOne
    private Category category;
}
