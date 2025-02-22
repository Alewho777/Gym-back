package com.example.gimnasio_backend.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gimnasio_backend.model.Producto;
import com.example.gimnasio_backend.model.Ventas;
import com.example.gimnasio_backend.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService{
	
	@Autowired VentaRepository ventaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Ventas> findAll() {
		return ventaRepository.findAll();
	}

	@Override
	public Ventas save(Ventas ventas) {
		return ventaRepository.save(ventas);
	}

	@Override
	@Transactional(readOnly = true)
	public Ventas findById(Long id) {
		return ventaRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		ventaRepository.deleteById(id);
		
	}

	@Override
	public List <Ventas> findByProducto(Producto producto) {
		return ventaRepository.findByProducto(producto);
	}

	@Override
    public List<Ventas> findByFechaVentaBetween(LocalDate start, LocalDate end) {
        return ventaRepository.findByFechaVentaBetween(start, end);
    }
}
