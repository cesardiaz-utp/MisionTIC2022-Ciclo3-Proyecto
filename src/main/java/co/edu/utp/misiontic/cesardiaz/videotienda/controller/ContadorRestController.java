package co.edu.utp.misiontic.cesardiaz.videotienda.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contador")
public class ContadorRestController {
    
    private Integer contador = 0; 

    @PostMapping
    public String incrementar(){
        contador++;

        return String.format("%,d", contador);
    }
}
