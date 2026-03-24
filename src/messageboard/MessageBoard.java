package messageboard;

import java.util.*;
import java.time.LocalDate;
import java.io.*;

public class MessageBoard implements MessageBoardInterface {

    private List<Post> posts = new ArrayList<>();
    private String boardName;

    public MessageBoard(String boardName) {
        this.boardName = boardName;
    }

    private int getPostIndex(int postID) {
        for (int i = 0; i < posts.size(); i++)
            if (posts.get(i).getPostID() == postID) return i;
        throw new IDInvalidException("Invalid post ID.");
    }

    public int[] getPostIDs() {
        return posts.stream().mapToInt(Post::getPostID).toArray();
    }

    public int addPost(String author, String subject, String message) {
        if (author == null || author.isEmpty() ||
            subject == null || subject.isEmpty() ||
            message == null || message.isEmpty())
            throw new IllegalArgumentException("Invalid input.");
        Post p = new Post(author, subject, message);
        posts.add(p);
        return p.getPostID();
    }

    public int addPostAdvanced(String author, String subject, String message, String tagString, int parentID) {
        if (author == null || author.isEmpty() ||
            subject == null || subject.isEmpty() ||
            message == null || message.isEmpty())
            throw new IllegalArgumentException("Invalid input.");

        String[] tags = (tagString == null || tagString.isEmpty()) ? new String[0] : Arrays.stream(tagString.split(",")).map(String::trim).toArray(String[]::new);

        if (parentID != -1) getPostIndex(parentID);

        Post p = new Post(author, subject, message, tags, parentID);
        posts.add(p);
        return p.getPostID();
    }

    public void deletePost(int postID) {
        posts.remove(getPostIndex(postID));
    }

    public int[] searchPostsBySubject(String keyword) {
        return posts.stream().filter(p -> p.getSubject().toLowerCase().contains(keyword.toLowerCase()))
                    .mapToInt(Post::getPostID).toArray();
    }

    public int[] searchPostsByDate(int startDate, int endDate) {
        return posts.stream().filter(p -> p.getDate() >= startDate && p.getDate() <= endDate)
                    .mapToInt(Post::getPostID).toArray();
    }

    public String getFormattedPost(int postID) {
        return posts.get(getPostIndex(postID)).toFormattedString();
    }

    public void saveMessageBoard(String filename) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(posts.toArray(new Post[0]));
        out.close();
    }

    public void loadMessageBoard(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        Post[] arr = (Post[]) in.readObject();
        posts = new ArrayList<>(Arrays.asList(arr));
        in.close();
    }

    public void savePostAsTextFile(int postID, String filename) throws IOException {
        posts.get(getPostIndex(postID)).saveAsTextFile(filename);
    }

    public int[] getReplyPosts(int parentID) {
        return posts.stream().filter(p -> p.getParentID() == parentID).mapToInt(Post::getPostID).toArray();
    }

    public int[] searchPostsByTag(String tag) {
        return posts.stream()
                    .filter(p -> Arrays.stream(p.getTags()).anyMatch(t -> t.equalsIgnoreCase(tag)))
                    .mapToInt(Post::getPostID).toArray();
    }

    public String[] getAllTags() {
        return posts.stream().flatMap(p -> Arrays.stream(p.getTags()))
                    .map(String::trim)
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
    }
}
