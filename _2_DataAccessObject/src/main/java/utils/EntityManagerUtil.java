package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.HashMap;
import java.util.Map;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class EntityManagerUtil {

    private static final EntityManagerFactory emf;

    static {
        Map<String, String> props = new HashMap<>();
        String url = ConfigLoader.getUrl();
        String user = ConfigLoader.getUsername();
        String pass = ConfigLoader.getPassword();

        if (url != null) props.put("javax.persistence.jdbc.url", url);
        if (user != null) props.put("javax.persistence.jdbc.user", user);
        if (pass != null) props.put("javax.persistence.jdbc.password", pass);

        emf = createEntityManagerFactory("myApp", props);
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}