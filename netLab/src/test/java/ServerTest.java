import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    @Test
    public void givenClient__whenServerRespondsWhenStarted__thenCorrect() {
        ClientApp clientApp = new ClientApp();
        clientApp.startConnection("127.0.0.1", 6666);
        String response = clientApp.sendMessage("hello server");
        assertEquals("hello clientApp", response);
    }

}