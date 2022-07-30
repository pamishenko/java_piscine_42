package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{


    private final Connection connection;
    private static final String QUERY_TEMPLATE = "SELECT * FROM TEST.PRODUCTS WHERE id=?";
    private static final String QUERY_TEMPLATE_SELECT_ALL = "SELECT * FROM TEST.PRODUCTS";
    private static final String QUERY_TEMPLATE_UPDATE = "UPDATE TEST.PRODUCTS SET name=?, price=? WHERE id=?";



    public ProductsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product        product = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(QUERY_TEMPLATE);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("price")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(product);
    }



    @Override
    public List<Product> findAll() {
        List<Product>        product = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(QUERY_TEMPLATE_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.add(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("price")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return product;
    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(QUERY_TEMPLATE_UPDATE);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Product product) {
        final String QUERY_TEMPLATE_INSERT =
                "INSERT INTO TEST.PRODUCTS (name, price)" +
                        " VALUES (?, ?)";
        try {
                PreparedStatement preparedStatement = this.connection.prepareStatement(QUERY_TEMPLATE_INSERT);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setLong(2, product.getPrice());
                preparedStatement.executeUpdate();
                preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        final String QUERY_TEMPLATE_DELETE = "DELETE FROM TEST.PRODUCTS WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_TEMPLATE_DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
