package com.es.jwtsecurity.controller;

import com.es.jwtsecurity.dto.UsuarioLoginDTO;
import com.es.jwtsecurity.dto.UsuarioRegisterDTO;
import com.es.jwtsecurity.model.Usuario;
import com.es.jwtsecurity.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(UsuarioLoginDTO usuarioLoginDTO) {
        Authentication authentication= authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUsername(), usuarioLoginDTO.getPassword())
        );
        return authentication.toString();
    }


    @PostMapping("/register")
    public ResponseEntity<UsuarioRegisterDTO> register(
            @RequestBody UsuarioRegisterDTO usuarioRegisterDTO) {

        return ResponseEntity.ok(usuarioService.registerUser(usuarioRegisterDTO));
    }

    @PreAuthorize("hasRole('ADMIN') or #nombre == authentication.name")
    @GetMapping("/{nombre}")
    public ResponseEntity<?> getUsuarioByNombre(@PathVariable String nombre) {
        return usuarioService.getAllByUsername(nombre)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}
