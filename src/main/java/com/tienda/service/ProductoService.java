package com.tienda.service;
import com.tienda.entity.Producto;
import com.tienda.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> findAll() {
        return repo.findAll();
    }

    public Producto save(Producto p) {
        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    public List<Producto> filtrarPorStockBajo(int limite) {
        return repo.findAll()
                .stream()
                .filter(p -> p.getStock() < limite)
                .toList();
    }
}