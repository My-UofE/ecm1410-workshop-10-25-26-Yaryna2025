import messageboard.*;
import java.io.IOException;

public class TestPostApp {
    public static void main(String[] args) {

        Post p = new Post("Alex Adam", "Help with Java",
            "Hi, could anyone help me I need to learn how to code in java!");

        System.out.println(p.toFormattedString());

        try {
            p.saveAsTextFile("mypost.txt");
        } catch(IOException e) {
            System.out.println("File not saved.");
            e.printStackTrace();
        }
    }
}