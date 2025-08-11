import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonService {

//    @SneakyThrows
    public void createAnimalJson(String filePath, List<Animal> animals) {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            mapper.writeValue(new File(filePath), animals);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
