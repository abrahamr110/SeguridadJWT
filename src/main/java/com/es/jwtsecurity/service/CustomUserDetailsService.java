package com.es.jwtsecurity.service;

import com.es.jwtsecurity.dto.UsuarioRegisterDTO;
import com.es.jwtsecurity.model.Usuario;
import com.es.jwtsecurity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        UserDetails user = User.builder().username(username).password(usuario.getPassword()).roles(usuario.getRoles()).build();

        return user;
    }

    public UsuarioRegisterDTO registerUser(UsuarioRegisterDTO user) {
        if (usuarioRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        Usuario newUser = new Usuario(user.getUsername(), encodedPassword, user.getRoles());
        usuarioRepository.save(newUser);


        return newUser;
    }
}
