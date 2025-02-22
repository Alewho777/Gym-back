package com.example.gimnasio_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.gimnasio_backend.JwtUtil;
import com.example.gimnasio_backend.model.Usuario;
import com.example.gimnasio_backend.repository.UsuarioRepository;

import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	 @Autowired
	    private UsuarioRepository usuarioRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	    	System.out.println("Username recibido: " + request.getUsername());
	        System.out.println("Contraseña recibida: " + request.getContrasena());
	        
	        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));
	        
	        System.out.println("Contraseña almacenada: " + usuario.getContrasena());
	        
	        if(!passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
	        }
	        
	        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getRol());
	        
	        //return ResponseEntity.ok(new AuthResponse(token, usuario.getRol()));
	        return ResponseEntity.ok(Map.of(
	                "token", token,
	                "user", Map.of(
	                    "username", usuario.getUsername(),
	                    "rol", usuario.getRol()
	                )
	            ));
	    }

	    // Agrega estas clases DTO como inner classes estáticas
	    @Getter
	    @Setter
	    private static class LoginRequest {
	        private String username;
	        private String contrasena;
			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
			public String getContrasena() {
		        return contrasena;
		    }

		    public void setContrasena(String contrasena) {
		        this.contrasena = contrasena;
		    }
	        
	        
	    }

	    @Getter
	    private static class AuthResponse {
	        private final String token;
	        private final String rol;
	        
	        public AuthResponse(String token, String rol) {
	            this.token = token;
	            this.rol = rol;
	        }
	    }
}
