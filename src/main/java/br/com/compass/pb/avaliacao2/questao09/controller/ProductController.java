package br.com.compass.pb.avaliacao2.questao09.controller;

import br.com.compass.pb.avaliacao2.questao09.model.Product;
import br.com.compass.pb.avaliacao2.questao09.service.ProductService;

import java.util.List;


public class ProductController implements AutoCloseable {

    private final ProductService service;

    public ProductController() {
        this.service = new ProductService();
    }

    public List<Product> search(String str) {
        return this.service.search(str);
    }

    public List<Product> listAll() {
        return this.service.listAll();
    }

    public void save(Product product) {
        this.service.save(product);
    }

    public void update(Product updated) {
        this.service.update(updated);
    }

    public void delete(int id) {
        this.service.delete(id);
    }

    @Override
    public void close() {
        this.service.close();
    }
}

