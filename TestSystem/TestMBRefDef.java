import messageboard.*;

public class TestMBRefDef {
    public static void main(String[] args) {

        MessageBoard board = new MessageBoard("Advanced Board");

        int p1 = board.addPostAdvanced("Alex", "Java Help", "Need help", "java, help, urgent", -1);
        int p2 = board.addPostAdvanced("Belinda", "Re: Java Help", "I can help", "java, reply", p1);
        int p3 = board.addPostAdvanced("Cindy", "IDE question", "Best IDE?", "java, IDE", -1);

        System.out.println("=== POSTS WITH TAG 'java' ===");
        for(int id : board.searchPostsByTag("java"))
            System.out.println(board.getFormattedPost(id));

        System.out.println("=== REPLIES TO POST " + p1 + " ===");
        for(int id : board.getReplyPosts(p1))
            System.out.println(board.getFormattedPost(id));

        System.out.println("=== ALL TAGS ===");
        for(String tag : board.getAllTags())
            System.out.println(tag);
    }
}