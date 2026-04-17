package com.tienda.service;
import com.tienda.entity.Categoria;
import com.tienda.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Categoria save(Categoria c) {
        return repo.save(c);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}