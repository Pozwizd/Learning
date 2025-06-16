package annotation;

import org.springframework.stereotype.Component;

@Component // Говорим Spring: "Это бин!"
public class MessageService {
    public String getMessage() {
        return "Hello from Annotated Spring!";
    }
}