package co.edu.utp.misiontic.cesardiaz.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saludo")
public class SaludoController {

    @GetMapping
    public String saludo(@RequestParam("name") String name) {
        return String.format("Hola %s!", name);
    }
}
