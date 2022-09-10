package co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String description;
    private Integer length;
    private String imageUrl;  
}
