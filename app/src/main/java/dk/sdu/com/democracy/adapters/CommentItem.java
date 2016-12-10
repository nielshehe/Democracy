package dk.sdu.com.democracy.adapters;

public class CommentItem {
    public String name;
    public String comment;
    public int upvotes;
    public String readMore;

    public CommentItem(String name, String comment, int upvotes, String readMore) {
        this.name = name;
        this.comment = comment;
        this.upvotes = upvotes;
        this.readMore = readMore;
    }
}
