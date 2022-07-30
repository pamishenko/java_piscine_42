package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.school21.utils.utilsTests.*;
import static edu.school21.repositories.EmbeddedDataSourceTest.runStatement;
import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {

    private static final String DB_SCHEMA = "/resources/schema.sql";
    private static final String DB_DATA = "/resources/data.sql";

    static DataSource ds;
    static Connection connection;
    ProductsRepository productsRepository;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList(Arrays.asList(
            new Product(0L, "Lenovo", 559888L),
            new Product(1L, "HP R", 15900L),
            new Product(2L, "MacBook Air, 16gb, 13inches", 98779L),
            new Product(3L, "Huawei P40", 1230L),
            new Product(4L, "ssd", 15921L),
            new Product(5L, "Yandex  Device", 20939L),
            new Product(6L, "Yandex IoT Device", 10822L)));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "MacBook Air, 16gb, 13inches", 98779L);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "Update Name", 1000L);
    final Product EXPECTED_SAVE_PRODUCT = new Product(1L, "Testing save function", 100500L);
    @Test
    @BeforeEach
    public void init() throws Exception {
        EmbeddedDatabaseBuilder dataSource = new EmbeddedDatabaseBuilder();
        ds = dataSource
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        ds.getConnection();
        Assertions.assertNotNull(ds);
        connection = connect();
        runStatement(connection, DB_SCHEMA);
        runStatement(connection, DB_DATA);

        productsRepository = new ProductsRepositoryJdbcImpl(connection);
    }

    @Test
    public  void findAllTest(){
        EXPECTED_FIND_ALL_PRODUCTS.forEach(
                it -> assertEquals(
                        it.getName(), productsRepository.findById(it.getId()).get().getName()
                )
        );
    }

    @Test
    public void findByIdTest() {
        Product product = productsRepository.findById(2L).get();
        assertEquals(product.getName(), EXPECTED_FIND_BY_ID_PRODUCT.getName());
        assertEquals(product.getPrice(), EXPECTED_FIND_BY_ID_PRODUCT.getPrice());
    }

    @Test
    void updateTest(){
        Product product = new Product(1L, "Update Name", 1000L);
        productsRepository.update(product);
        assertEquals(productsRepository.findById(1L).get().getName(), EXPECTED_UPDATED_PRODUCT.getName());
        assertEquals(productsRepository.findById(1L).get().getPrice(), EXPECTED_UPDATED_PRODUCT.getPrice());
    }

    @Test
    void saveTest(){
        productsRepository.save(EXPECTED_SAVE_PRODUCT);
        assertEquals(productsRepository.findAll().size(), 8);
    }

    @Test
    void deleteTest(){
        productsRepository.delete(1L);
        productsRepository.delete(2L);
        assertEquals(productsRepository.findAll().size(), 5);
    }

    @AfterEach
    void endTest() throws SQLException {
        connection.close();
    }
}
