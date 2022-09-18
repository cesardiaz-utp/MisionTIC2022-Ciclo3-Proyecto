package co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private String id;
    private String name;
    private Integer categoryId;
    private String description;
    private Integer length;
    private Double price;
    private String imageUrl;  
}
