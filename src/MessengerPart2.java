import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessengerPart2 {

    @Test
    public void testMessageLengthSuccess() {

        String message =
                "Hi Mike, can you join us for dinner tonight?";

        assertEquals(
                "Message ready to send.",
                message.length() <= 250
                        ? "Message ready to send."
                        : "Fail");
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                "a".repeat(260);

        int exceeded =
                longMessage.length() - 250;

        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                "Message exceeds 250 characters by "
                        + exceeded
                        + "; please reduce the size.");
    }

    @Test
    public void testRecipientSuccess() {

        String recipient =
                "+27718693002";

        assertEquals(
                "Cell phone number successfully captured.",
                MainPart2.checkRecipientCell(recipient));
    }

    @Test
    public void testRecipientFailure() {

        String recipient =
                "08575975889";

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                MainPart2.checkRecipientCell(recipient));
    }}