package br.com.dapatroa.controller;

import br.com.dapatroa.data.entity.Categoria;
import br.com.dapatroa.data.service.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.PersistenceException;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;
    private Categoria categoria;

    public Categoria getCategoria() {
        if (categoria == null) {
            categoria = new Categoria();
        }
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaController() {
        categoria = new Categoria();
    }

    public void save() throws PersistenceException {
        repository.save(categoria);
    }

    public void delete() {
        repository.delete(categoria);
    }
}
