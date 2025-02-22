package com.example.gimnasio_backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.gimnasio_backend.model.Usuario;
import com.example.gimnasio_backend.repository.UsuarioRepository;



public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
    private UsuarioRepository UsuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return UsuarioRepository.findAll();
    }

    @Override
    public Usuario save(Usuario usuario) {
        return UsuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return UsuarioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String usuario) {
        return UsuarioRepository.findByUsername(usuario).orElse(null);
    }

    @Override
    public void delete(Long id) {
        UsuarioRepository.deleteById(id);
    }
}
