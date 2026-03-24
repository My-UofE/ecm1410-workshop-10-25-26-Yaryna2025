import messageboard.*;
import java.io.IOException;

public class TestMBLoadApp {
    public static void main(String[] args) {

        MessageBoard board = new MessageBoard("Loaded Board");

        try {
            board.loadMessageBoard("codingsupport.ser");
        } catch(IOException ex) {
            System.out.println("Board not loaded.");
            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            System.out.println("Class not found.");
            ex.printStackTrace();
        }

        for (int id : board.getPostIDs()) {
            System.out.println(board.getFormattedPost(id));
        }

        // Save Windows post as file
        int[] ids = board.searchPostsBySubject("Windows");
        if(ids.length > 0) {
            try {
                board.savePostAsTextFile(ids[0], "windowspost.txt");
            } catch(IOException e) {
                System.out.println("File not saved.");
                e.printStackTrace();
            }
        }
    }
}