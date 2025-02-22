package com.example.gimnasio_backend.Services;

import java.util.List;

import com.example.gimnasio_backend.model.Usuario;


public interface UsuarioService {

	List<Usuario> findAll();
	Usuario save(Usuario usuario);
	Usuario findById(Long id);
    void delete(Long id);
    Usuario findByUsername(String usuario);
}
