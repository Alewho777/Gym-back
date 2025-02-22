package com.example.gimnasio_backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gimnasio_backend.model.Producto;
import com.example.gimnasio_backend.repository.ProductoRepository;


@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
    private ProductoRepository ProductoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return ProductoRepository.findAll();
    }

    @Override
    public Producto save(Producto producto) {
        return ProductoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return ProductoRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        ProductoRepository.deleteById(id);
    }

	@Override
	public Producto findByCodigo(String codigo) {
		return ProductoRepository.findByCodigo(codigo).orElse(null);
	}

	@Override
	 public void deleteByCodigo(String codigo) {
       ProductoRepository.deleteByCodigo(codigo);
   }

}
