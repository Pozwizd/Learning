import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonService {

    private final ObjectMapper mapper;

    public JsonService() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void createAnimalJson(String filePath, List<Animal> animals) {
        try {
            mapper.writeValue(new File(filePath), animals);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write JSON file: " + filePath, e);
        }
    }
}
