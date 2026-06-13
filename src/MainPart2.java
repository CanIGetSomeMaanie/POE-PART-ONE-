import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainPart2 {

    static int messageCount = 0;

   public static ArrayList<String> sentMessages = new ArrayList<>();
   public  static ArrayList<String> storedMessages = new ArrayList<>();
   public static ArrayList<String> disregardedMessages = new ArrayList<>();

    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> recipients = new ArrayList<>();

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
            System.out.println("Option 3 - Stored Messages");
            System.out.println("Option 4 - Quit");
            System.out.print("Enter your choice (1, 2, 3 or 4): ");

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

                                sentMessages.add(message);
                                messageHashes.add(messageHash);
                                messageIDs.add(messageId);
                                recipients.add(recipient);

                                System.out.println(
                                        "Message successfully sent.");

                                printMessages(
                                        messageId,
                                        recipient,
                                        message,
                                        messageHash);

                                break;

                            case 2:

                                disregardedMessages.add(message);

                                System.out.println(
                                        "Message disregarded.");

                                break;

                            case 3:

                                storedMessages.add(message);

                                messageHashes.add(messageHash);
                                messageIDs.add(messageId);
                                recipients.add(recipient);

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

                    storedMenu(scanner);

                    break;

                case 4:

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
                return "Message disregarded.";

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
            String hash)
    {

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
    public static void storedMenu(Scanner scanner) {

        System.out.println("\nStored Messages Menu");
        System.out.println("1. Display Sender and Recipient");
        System.out.println("2. Display Longest Message");
        System.out.println("3. Search Message ID");
        System.out.println("4. Search Recipient");
        System.out.println("5. Delete Message using Hash");
        System.out.println("6. Display Report");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                displaySenderRecipient();
                break;

            case 2:
                System.out.println("Longest Message: "
                        + getLongestMessage());
                break;

            case 3:

                System.out.print("Enter Message ID: ");
                String id = scanner.nextLine();
                searchMessageID(id);
                break;

            case 4:

                System.out.print("Enter Recipient: ");
                String recipient = scanner.nextLine();
                searchRecipient(recipient);
                break;

            case 5:

                System.out.print("Enter Message Hash: ");
                String hash = scanner.nextLine();
                deleteMessageHash(hash);
                break;

            case 6:

                displayReport();
                break;

            default:

                System.out.println("Invalid option.");
        }
    }
    public static void displaySenderRecipient() {

        for (int i = 0; i < recipients.size(); i++) {

            System.out.println(
                    "Sender: Developer");

            System.out.println(
                    "Recipient: "
                            + recipients.get(i));

            System.out.println();
        }
    }
    public static String getLongestMessage() {

        String longest = "";

        for (String message : storedMessages) {

            if (message.length()
                    > longest.length()) {

                longest = message;
            }
        }

        return longest;
    }
    public static void searchMessageID(String id) {
        System.out.println("IDs stored: " + messageIDs);


        for (int i = 0; i < messageIDs.size(); i++) {

            if (messageIDs.get(i).equals(id)) {

                System.out.println("Recipient: "
                        + recipients.get(i));

                if (i < storedMessages.size()) {

                    System.out.println("Message: "
                            + storedMessages.get(i));
                }

                return;
            }
        }

        System.out.println("Message ID not found.");
    }
    public static void searchRecipient(String recipient) {

        boolean found = false;

        for (int i = 0; i < recipients.size(); i++) {

            if (recipients.get(i).equals(recipient)) {

                found = true;

                if (i < storedMessages.size()) {

                    System.out.println(
                            storedMessages.get(i));
                }
            }
        }

        if (!found) {

            System.out.println(
                    "Recipient not found.");
        }
    }
    public static void deleteMessageHash(String hash) {

        for (int i = 0; i < messageHashes.size(); i++) {

            if (messageHashes.get(i).equals(hash)) {

                String deletedMessage = "";

                if (i < storedMessages.size()) {

                    deletedMessage = storedMessages.get(i);
                    storedMessages.remove(i);
                }

                messageHashes.remove(i);

                System.out.println(
                        "Message: \"" +
                                deletedMessage +
                                "\" successfully deleted.");

                return;
            }
        }

        System.out.println("Hash not found.");
    }
    public static void displayReport() {

        System.out.println("\n===== MESSAGE REPORT =====");

        for (int i = 0; i < storedMessages.size(); i++) {

            System.out.println(
                    "Message Hash: "
                            + messageHashes.get(i));

            System.out.println(
                    "Recipient: "
                            + recipients.get(i));

            System.out.println(
                    "Message: "
                            + storedMessages.get(i));

            System.out.println(
                    "-----------------------");
        }
    }
    }
