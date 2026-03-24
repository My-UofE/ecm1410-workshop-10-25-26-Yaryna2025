import messageboard.*;
import java.io.IOException;

public class TestMBApp {
    public static void main(String[] args) {

        MessageBoard board = new MessageBoard("Coding Support");

        board.addPost("Alex Adam", "Help with Java", "Hi, could anyone help me I need to learn how to code in java!");
        board.addPost("Belinda Bennett", "Help with Java", "Hi Alex. Yes I can send some tutorials I found useful.");
        board.addPost("Cindy Carter", "Coding on a Chromebook", "Hi, could anyone help me I need to learn how to code in java!");
        board.addPost("Dennis Dobson", "Windows problems", "My windows laptop is stuck on a reboot loop. Does anyone know what to do!");

        for (int id : board.getPostIDs()) {
            System.out.println(board.getFormattedPost(id));
        }

        try {
            board.saveMessageBoard("codingsupport.ser");
        } catch(IOException ex) {
            System.out.println("Board not saved.");
            ex.printStackTrace();
        }
    }
}