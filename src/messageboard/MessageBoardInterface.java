package messageboard;

import java.io.*;

public interface MessageBoardInterface extends Serializable {

    int[] getPostIDs();

    int addPost(String author, String subject, String message);
    int addPostAdvanced(String author, String subject, String message, String tags, int parentID);

    void deletePost(int postID);
    int[] searchPostsBySubject(String keyword);
    int[] searchPostsByDate(int startDate, int endDate);

    String getFormattedPost(int postID);

    void saveMessageBoard(String filename) throws IOException;
    void loadMessageBoard(String filename) throws IOException, ClassNotFoundException;

    void savePostAsTextFile(int postID, String filename) throws IOException;

    int[] getReplyPosts(int parentID);
    int[] searchPostsByTag(String tag);
    String[] getAllTags();
}