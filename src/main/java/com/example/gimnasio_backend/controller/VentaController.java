package com.example.gimnasio_backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.gimnasio_backend.Services.ProductoService;
import com.example.gimnasio_backend.Services.VentaService;
import com.example.gimnasio_backend.model.Producto;
import com.example.gimnasio_backend.model.Ventas;
import com.example.gimnasio_backend.repository.VentaRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	@Autowired
    private VentaRepository ventaRepository;
	
	@Autowired
	private VentaService ventaService;

    @Autowired
    private ProductoService productoService;

    @Transactional
    public Ventas registrarVenta(Ventas venta) {
        Producto producto = venta.getProducto();
        int cantidad = venta.getCantidad();

        // Validar stock
        if (producto.getStock() < cantidad) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente");
        }

        // Actualizar stock
        producto.setStock(producto.getStock() - cantidad);
        productoService.save(producto);

        // Guardar venta
        return ventaRepository.save(venta);
    }
    
    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ventas creteVenta(@RequestBody Ventas venta) {
        // Cargar el producto real desde la BD utilizando el ID enviado
        if (venta.getProducto() == null || venta.getProducto().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El producto es obligatorio");
        }
        Producto producto = productoService.findById(venta.getProducto().getId());
        if (producto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        int cantidad = venta.getCantidad();

        // Validar stock
        if (producto.getStock() < cantidad) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente");
        }

        // Actualizar el stock
        producto.setStock(producto.getStock() - cantidad);
        productoService.save(producto);

        // Asegurar que la fecha de venta se establece (aunque ya lo haga en la entidad)
        venta.setFechaVenta(LocalDate.now());
        
        venta.setTotal( cantidad * producto.getPrecio());
        // Registrar la venta
        return ventaService.save(venta);
    }
    
    
    //@PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    //public Ventas crearVenta(@RequestBody Ventas venta) {
      //  return this.registrarVenta(venta);
    //}

    @GetMapping
    public List<Ventas> listarVentas() {
        return ventaService.findAll();
    }
    
    //FILTRAR POR FECHA
    @GetMapping("/por-fecha")
    public List<Ventas> getVentasPorFecha(
        @RequestParam LocalDate start,
        @RequestParam LocalDate end
    ) {
        return ventaService.findByFechaVentaBetween(start, end);
    }
    
}
