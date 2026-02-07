package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Optional;

/**
 * Utility class to load database configuration from environment variables
 * or a properties file.
 */
public class ConfigLoader {
    private static final Properties properties = new Properties();
    private static final String DEFAULT_PROPERTIES_FILE = "database.properties";

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException ex) {
            System.err.println("Could not load " + DEFAULT_PROPERTIES_FILE);
        }
    }

    /**
     * Retrieves a configuration value from environment variables or a properties file.
     *
     * @param key The key in the properties file.
     * @param envVar The name of the environment variable.
     * @return The configuration value, or null if not found.
     */
    public static String getProperty(String key, String envVar) {
        return Optional.ofNullable(System.getenv(envVar))
                .orElse(properties.getProperty(key));
    }

    public static String getUrl() {
        return getProperty("db.url", "DB_URL");
    }

    public static String getUsername() {
        return getProperty("db.username", "DB_USERNAME");
    }

    public static String getPassword() {
        return getProperty("db.password", "DB_PASSWORD");
    }
}
