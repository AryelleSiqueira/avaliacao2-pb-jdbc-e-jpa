package br.com.compass.pb.avaliacao2.questao09.dao;

import br.com.compass.pb.avaliacao2.questao09.connection.ConnectionFactory;
import br.com.compass.pb.avaliacao2.questao09.connection.ConnectionFailedException;
import br.com.compass.pb.avaliacao2.questao09.model.Product;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Product>, Closeable {

    private final Connection connection;


    public ProductDAO() {
        this.connection = new ConnectionFactory().retrieveConnection();
    }


    public void save(Product product) {
        String sql = "INSERT INTO TBPRODUCT (NAME, DESCRIPTION, PRICE, DISCOUNT, START_DATE";

        if (product.getId() != null) {
            sql += ", ID) VALUES (?, ?, ?, ?, ?, ?)";
        }
        else {
            sql += ") VALUES (?, ?, ?, ?, ?)";
        }

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setFloat(3, product.getPrice());
            ps.setFloat(4, product.getDiscount());
            ps.setDate(5, product.getDate());
            if (product.getId() != null) {
                ps.setInt(6, product.getId());
            }
            ps.execute();
        }
        catch (SQLException e) {
            throw new ConnectionFailedException("Something went wrong while trying to save product...");
        }
    }


    @Override
    public void update(Product updated) {
        String sql = "UPDATE TBPRODUCT " +
                "SET NAME = ?, DESCRIPTION = ?, PRICE = ?, DISCOUNT = ?, START_DATE = ? WHERE ID = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, updated.getName());
            ps.setString(2, updated.getDescription());
            ps.setFloat(3, updated.getPrice());
            ps.setFloat(4, updated.getDiscount());
            ps.setDate(5, updated.getDate());
            ps.setInt(6, updated.getId());

            ps.execute();
        }
        catch (SQLException e) {
            throw new ConnectionFailedException("Something went wrong while trying to save product...");
        }
    }


    @Override
    public Product get(int id) {
        String query = "SELECT * FROM TBPRODUCT WHERE ID = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();

            try (ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {
                    return new Product(rs.getInt("ID"), rs.getString("NAME"),
                            rs.getString("DESCRIPTION"), rs.getFloat("PRICE"),
                            rs.getFloat("DISCOUNT"), rs.getDate("START_DATE"));
                }
                return null;
            }
        }
        catch (SQLException e) {
            throw new ConnectionFailedException("Something went wrong while trying to get product...");
        }
    }


    public List<Product> search(List<String> terms) {
        StringBuilder sql = new StringBuilder("SELECT * FROM TBPRODUCT WHERE NAME LIKE ?");

        int nTerms = terms.size();
        for (int i = 1; i < nTerms; i++) {
            sql.append(" OR NAME LIKE ?");
        }

        try (PreparedStatement ps = this.connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < nTerms; i++) {
                ps.setString( i + 1, "%" + terms.get(i) + "%");
            }
            ps.execute();

            List<Product> productList = new ArrayList<>();

            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    productList.add(new Product(rs.getInt("ID"), rs.getString("NAME"),
                            rs.getString("DESCRIPTION"), rs.getFloat("PRICE"),
                            rs.getFloat("DISCOUNT"), rs.getDate("START_DATE")));
                }
            }
            return productList;
        }
        catch (SQLException e) {
            throw new ConnectionFailedException("Something went wrong while trying to find products...");
        }
    }


    @Override
    public List<Product> listAll() {
        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM TBPRODUCT";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.execute();

            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    productList.add(new Product(rs.getInt("ID"), rs.getString("NAME"),
                            rs.getString("DESCRIPTION"), rs.getFloat("PRICE"),
                            rs.getFloat("DISCOUNT"), rs.getDate("START_DATE")));
                }
            }
            return productList;
        }
        catch (SQLException e) {
            throw new ConnectionFailedException("Something went wrong while trying to list products...");
        }
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM TBPRODUCT WHERE ID = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        }
        catch (SQLException e) {
            throw new ConnectionFailedException("Something went wrong while trying to delete product...");
        }
    }


    @Override
    public void close() {
        try {
            this.connection.close();
        }
        catch (SQLException e) {
            throw new ConnectionFailedException("Something went wrong while trying to close connection...");
        }
    }
}
