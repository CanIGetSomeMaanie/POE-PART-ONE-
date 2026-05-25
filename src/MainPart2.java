import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.json.JSONObject;
import java.util.Scanner;

public class MainPart2 {
    //hello//

    static int messageCount = 0;

    public static void main(String[] args) {

        startChat();
    }

    public static void startChat() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Welcome to Quick Chat");
            System.out.println("Select transaction:");
            System.out.println("Option 1 - Select Quickchat");
            System.out.println("Option 2 - Send Quickchat");
            System.out.println("Option 3 - Quit");

            System.out.print("Enter your choice (1, 2, or 3): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("You selected: Select Quickchat");
                    System.out.println("This feature is coming soon, please stay tuned");

                    break;

                case 2:

                    System.out.println("You selected: Send Quickchat");

                    System.out.print("How many messages would you like to send? ");

                    int numMessages = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < numMessages; i++) {

                        String recipient;

                        do {

                            System.out.print("Enter your number (must start with +27 and be exactly 12 characters): ");

                            recipient = scanner.nextLine();

                            if (!(recipient.startsWith("+27")
                                    && recipient.length() == 12)) {

                                System.out.println(
                                        "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                            }

                        } while (!(recipient.startsWith("+27")
                                && recipient.length() == 12));

                        System.out.println(
                                "Cell phone number successfully captured.");

                        System.out.print(
                                "Enter your Quickchat (must be 250 characters or less): ");

                        String message = scanner.nextLine();

                        if (message.length() > 250) {

                            int exceeded =
                                    message.length() - 250;

                            System.out.println(
                                    "Message exceeds 250 characters by "
                                            + exceeded
                                            + "; please reduce the size.");

                            break;

                        } else {

                            System.out.println(
                                    "Message ready to send.");
                        }

                        String messageId =
                                generateMessageId();

                        System.out.println(
                                "Message ID generated: "
                                        + messageId);

                        int currentMessageCount =
                                ++messageCount;

                        String messageHash =
                                generateMessageHash(
                                        messageId,
                                        currentMessageCount,
                                        message);

                        System.out.println(
                                "Message Hash: "
                                        + messageHash);

                        System.out.println("Choose an option:");
                        System.out.println("Option 1 - Send Quickchat");
                        System.out.println("Option 2 - Disregard Quickchat");
                        System.out.println("Option 3 - Store Quickchat to send later");

                        int subChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (subChoice) {

                            case 1:

                                System.out.println(
                                        "Message successfully sent.");

                                printMessages(
                                        messageId,
                                        recipient,
                                        message,
                                        messageHash);

                                break;

                            case 2:

                                System.out.println(
                                        "Press 0 to delete the message.");

                                break;

                            case 3:

                                storeMessageToJSONFile(
                                        messageId,
                                        recipient,
                                        message,
                                        messageHash);

                                System.out.println(
                                        "Message successfully stored.");

                                break;

                            default:

                                System.out.println(
                                        "Invalid option");
                        }
                    }

                    System.out.println(
                            "Total number of messages sent: "
                                    + returnTotalMessages());

                    break;

                case 3:

                    System.out.println("Goodbye!");

                    return;

                default:

                    System.out.println("Invalid choice.");
            }
        }
    }

    public static boolean checkMessageID(String messageID) {

        return messageID.length() <= 10;
    }

    public static String checkRecipientCell(String recipient) {

        if (recipient.startsWith("+27")
                && recipient.length() == 12) {

            return "Cell phone number successfully captured.";
        }

        return
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public static String createMessageHash(
            String messageId,
            int count,
            String message) {

        return generateMessageHash(
                messageId,
                count,
                message);
    }

    public static String SentMessage(int choice) {

        switch (choice) {

            case 1:
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    public static String printMessages(
            String messageId,
            String recipient,
            String message,
            String hash) {

        String output =
                "Message ID: " + messageId
                        + "\nMessage Hash: " + hash
                        + "\nRecipient: " + recipient
                        + "\nMessage: " + message;

        System.out.println(output);

        return output;
    }

    public static int returnTotalMessages() {

        return messageCount;
    }

    private static String generateMessageHash(
            String messageId,
            int count,
            String message) {

        String[] words =
                message.trim().split("\\s+");

        String first =
                words.length > 0 ? words[0] : "";

        String last =
                words.length > 1
                        ? words[words.length - 1]
                        : first;

        return messageId.substring(0, 2)
                + ":"
                + count
                + ":"
                + first.toUpperCase()
                + last.toUpperCase();
    }

    private static String generateMessageId() {

        Random random = new Random();

        String id = "";

        for (int i = 0; i < 10; i++) {

            id += random.nextInt(10);
        }

        return id;
    }

    private static void storeMessageToJSONFile(
            String id,
            String recipient,
            String message,
            String hash) {

        try {

            JSONObject jo = new JSONObject();

            jo.put("messageID", id);
            jo.put("recipient", recipient);
            jo.put("message", message);
            jo.put("messageHash", hash);

            FileWriter file =
                    new FileWriter(
                            "stored_messages.json",
                            true);

            file.write(jo.toString(4) + "\n");

            file.close();

            System.out.println(
                    "Message stored successfully in JSON file.");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}