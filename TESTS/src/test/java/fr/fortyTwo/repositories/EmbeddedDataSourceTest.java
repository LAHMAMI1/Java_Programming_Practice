package fr.fortyTwo.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmbeddedDataSourceTest {

    private EmbeddedDatabase dataSource;

    @BeforeEach
    public void init() {
        // Creates an in-memory database
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Test
    public void testGetConnectionReturnsNonNullConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        // Connection should not be null
        assertNotNull(con);

        con.close();
    }
}
