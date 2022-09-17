package co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}