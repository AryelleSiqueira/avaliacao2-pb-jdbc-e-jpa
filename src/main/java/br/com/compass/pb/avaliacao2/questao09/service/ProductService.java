package br.com.compass.pb.avaliacao2.questao09.service;

import br.com.compass.pb.avaliacao2.questao09.dao.ProductDAO;
import br.com.compass.pb.avaliacao2.questao09.model.Product;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;


public class ProductService implements Closeable {

    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }


    public void save(Product product) {
        if (product.getId() != null && this.productExists(product.getId())) {
            throw new ProductAlreadyExistsException("Product already exists");
        }
        this.productDAO.save(product);
    }


    public void update(Product updatedProduct) {
        if (! this.productExists(updatedProduct.getId())) {
            this.productDAO.save(updatedProduct);
        }
        else {
            this.productDAO.update(updatedProduct);
        }
    }


    public void delete(int id) {
        if (! this.productExists(id)) {
            throw new NoProductsFoundException("Product does not exist");
        }

        this.productDAO.delete(id);
    }


    public List<Product> search(String str) {
        String[] terms = str.strip().split(" ");

        if (terms.length == 1 && terms[0].isEmpty()) {
            throw new NoProductsFoundException("No keywords were entered");
        }

        List <String> termList = new ArrayList<>();

        for (String term : terms) {
            if (!term.isEmpty()) {
                termList.add(term);
            }
        }

        List<Product> productList = this.productDAO.search(termList);

        if (productList.isEmpty()) {
            throw new NoProductsFoundException(String.format("No results for: '%s'", str));
        }

        return productList;
    }


    public List<Product> listAll() {
        List<Product> productList = this.productDAO.listAll();

        if (productList.isEmpty()) {
            throw new NoProductsFoundException("No products registered yet");
        }

        return productList;
    }


    public Product get(int id) {
        Product product = this.productDAO.get(id);

        if (product == null) {
            throw new NoProductsFoundException("Product does not exist");
        }

        return product;
    }


    private boolean productExists(int id) {
        return this.productDAO.get(id) != null;
    }


    @Override
    public void close() {
        this.productDAO.close();
    }
}
