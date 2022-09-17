package co.edu.utp.misiontic.cesardiaz.videotienda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.LoginRequest;
import co.edu.utp.misiontic.cesardiaz.videotienda.service.SecurityService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/login")
public class LoginRestController {

    // POST -> Insertar (C)
    // GET -> Consultar / Listar (R)
    // PUT -> Actualizar (U)
    // PATCH -> Actualizar Campo
    // DELETE -> Borrar (D)

    private final SecurityService securityService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest user) {
        try {
            var response = securityService.validateUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

}
