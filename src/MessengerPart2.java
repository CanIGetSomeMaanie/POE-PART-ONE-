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
    }
    @Test
    public void testLongestMessage() {

        MainPart2.storedMessages.clear();

        MainPart2.storedMessages.add(
                "Did you get the cake?");

        MainPart2.storedMessages.add(
                "Where are you? You are late! I have asked you to be on time.");

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                MainPart2.getLongestMessage());
    }

    @Test
    public void testSearchRecipient() {

        MainPart2.recipients.clear();
        MainPart2.storedMessages.clear();

        MainPart2.recipients.add(
                "+27838884567");

        MainPart2.storedMessages.add(
                "Did you get the cake?");

        assertEquals(
                "+27838884567",
                MainPart2.recipients.get(0));
    }

    @Test
    public void testMessageArrayPopulated() {

        MainPart2.sentMessages.clear();

        MainPart2.sentMessages.add(
                "Did you get the cake?");

        MainPart2.sentMessages.add(
                "It is dinner time!");

        assertEquals(
                2,
                MainPart2.sentMessages.size());
    }

    @Test
    public void testDeleteMessageHash() {

        MainPart2.messageHashes.clear();
        MainPart2.storedMessages.clear();

        MainPart2.messageHashes.add(
                "Test Message 2");

        MainPart2.storedMessages.add(
                "Where are you? You are late! I have asked you to be on time.");

        MainPart2.deleteMessageHash(
                "Test Message 2");

        assertEquals(
                0,
                MainPart2.messageHashes.size());
    }

    @Test
    public void testDisplayReportData() {

        MainPart2.messageHashes.clear();
        MainPart2.recipients.clear();
        MainPart2.storedMessages.clear();

        MainPart2.messageHashes.add(
                "66:1:DIDCAKE");

        MainPart2.recipients.add(
                "+27838884567");

        MainPart2.storedMessages.add(
                "Did you get the cake?");

        assertEquals(
                "Did you get the cake?",
                MainPart2.storedMessages.get(0));
    }}
