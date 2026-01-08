package fr.fortyTwo.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class DatabaseConfig {
    // HikariCP configuration
    public static DataSource createDataSource() {

        HikariConfig config = new HikariConfig();

        // Database connection details
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/chat_db");
        config.setUsername("postgres");
        config.setPassword("2458");

        // Pool sizing - often smaller is better!
        config.setMaximumPoolSize(8); // Max connections in pool
        config.setMinimumIdle(4); // Min connections to maintain

        // Connection timeout settings
        config.setConnectionTimeout(30000); // Wait 30 sec for connection
        config.setIdleTimeout(600000); // Retire idle after 10 min
        config.setMaxLifetime(1800000); // Max connection life 30 min

        // Pool behavior
        config.setPoolName("Chat-DB-Pool");
        config.setAutoCommit(true); // Auto-commit transactions by default

        // Leak detection for development/testing
        // Logs warning if connection not returned within threshold
        config.setLeakDetectionThreshold(60000); // 60 seconds

        // Additional JDBC driver properties
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }
}
