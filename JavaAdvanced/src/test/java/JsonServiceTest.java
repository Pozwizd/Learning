import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonServiceTest {

    @Test
    void shouldCreateJsonFile() throws Exception {
        JsonService service = new JsonService();
        Path file = Files.createTempFile("animals", ".json");

        service.createAnimalJson(file.toString(), List.of(Cat.builder().name("Murka").age(2).build()));

        String content = Files.readString(file);
        assertTrue(content.contains("Murka"));
    }
}
