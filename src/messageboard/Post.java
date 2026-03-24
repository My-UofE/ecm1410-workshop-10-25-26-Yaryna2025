package messageboard;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Post implements Serializable {

    private static int idCounter = 0;
    private int postID;
    private String author;
    private String subject;
    private String message;
    private String[] tags;
    private int parentID;
    private int date;

    public Post(String author, String subject, String message) {
        this(author, subject, message, new String[0], -1, null);
    }

    public Post(String author, String subject, String message, LocalDate date) {
        this(author, subject, message, new String[0], -1, date);
    }

    public Post(String author, String subject, String message, String[] tags, int parentID) {
        this(author, subject, message, tags, parentID, null);
    }

    public Post(String author, String subject, String message, String[] tags, int parentID, LocalDate date) {
        this.postID = ++idCounter;
        this.author = author;
        this.subject = subject;
        this.message = message;
        this.tags = tags;
        this.parentID = parentID;
        this.date = (date == null) ? (int) LocalDate.now().toEpochDay() : (int) date.toEpochDay();
    }

    // Getters
    public int getPostID() { return postID; }
    public String getAuthor() { return author; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
    public int getDate() { return date; }
    public String[] getTags() { return tags; }
    public int getParentID() { return parentID; }

    public String toString() {
        return String.format(
            "Post[postID=%d, author=\"%s\", subject=\"%s\", message=\"%s\", date=%d, tags=%s, parentID=%d]",
            postID, author, subject, message.replace("\n","\\n"), date, Arrays.toString(tags), parentID
        );
    }

    public String toFormattedString() {
        LocalDate postDate = LocalDate.ofEpochDay(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String result =
            "\n------------------  Post " + postID + "  -------------------" +
            "\nAuthor: " + author +
            "\nDate: " + postDate.format(formatter) +
            "\nSubject: " + subject + "\n" +
            "----  Message:  -------------------------------\n" +
            message +
            "\n-----------------------------------------------\n";
        return result;
    }

    public void saveAsTextFile(String filename) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(filename));
        out.write(toFormattedString());
        out.close();
    }
}