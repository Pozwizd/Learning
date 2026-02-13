package xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageServiceTest {

    @Test
    void shouldReturnExpectedMessage() {
        MessageService service = new MessageService();
        assertEquals("Hello, Spring!", service.getMessage());
    }
}
