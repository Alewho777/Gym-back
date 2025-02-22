package com.example.gimnasio_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.gimnasio_backend.Services.ProductoService;
import com.example.gimnasio_backend.model.Producto;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	@Autowired
    private ProductoService productoService;

 //BUSCAR TODAS LoS ProductoS
  @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

  //BUSCAR ProductoS POR ID
    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        Producto Producto = productoService.findById(id);
        if (Producto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return Producto;
    }

    //BUSCAR ProductoS POR CODIGO
    @GetMapping("/findByCodigo/{codigo}")
    public Producto getProductoByCodigo(@PathVariable String codigo) {
        Producto Producto = productoService.findByCodigo(codigo);
        if (Producto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return Producto;
    }

    //CREAR ProductoS
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto createProducto(@RequestBody Producto Producto) {
        return productoService.save(Producto);
    }

    //ACTUALIZAR DATOS DE Producto POR ID
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProducto(@RequestBody Producto Producto, @PathVariable Long id) {
        Producto updateProducto = productoService.findById(id);
        if (updateProducto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        updateProducto.setCodigo(Producto.getCodigo());
        updateProducto.setNombre(Producto.getNombre());
        updateProducto.setStock(Producto.getStock());
        updateProducto.setPrecio(Producto.getPrecio());
        
        productoService.save(updateProducto);
    }

    //ACTUALIZAR DATOS DE Producto POR CODIGO
    @PutMapping("/updateByCodigo/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductoByCodigo(@RequestBody Producto Producto, @PathVariable String codigo) {
        Producto updateProductoByCodigo = productoService.findByCodigo(codigo);
        if (updateProductoByCodigo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        updateProductoByCodigo.setCodigo(Producto.getCodigo());
        updateProductoByCodigo.setNombre(Producto.getNombre());
        updateProductoByCodigo.setStock(Producto.getStock());
        updateProductoByCodigo.setPrecio(Producto.getPrecio());
        
        productoService.save(updateProductoByCodigo);
    }

    //BORRAR Producto POR ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProducto(@PathVariable Long id) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        productoService.delete(id);
    }
    //BORRAR Producto POR CODIGO
    @DeleteMapping("/deleteByCodigo/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductoByCOdigo(@PathVariable String codigo) {
        Producto existingProducto = productoService.findByCodigo(codigo);
        if (existingProducto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        productoService.deleteByCodigo(codigo);
    }

    //MANEJO DE EXCEPCIONES
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

  //DESACTIVAR PRODUCTOS
    @PutMapping("/desactivar/{codigo}")
    public void desactivarProductos(@PathVariable String codigo) {
        Producto producto = productoService.findByCodigo(codigo);
        producto.setEstado(2);
        productoService.save(producto);
    }
    

}
