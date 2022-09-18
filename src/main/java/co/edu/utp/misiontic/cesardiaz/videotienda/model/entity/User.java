package co.edu.utp.misiontic.cesardiaz.videotienda.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    private String username; // user_name
    
    private String password;
    
    private String name;

    @Column(unique = true)
    private String email;
    
    private Boolean active;
    
    private Boolean admin;
}
