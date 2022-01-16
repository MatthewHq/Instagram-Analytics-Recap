
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matthew
 */
public class Post {

    String id;
    String postCode;
    java.util.Date timeStampDate;
    String smallUrl;
    String timestamp;
    int likeCount;
    Post NextPost;

    public Post(String id, String smallUrl, String timestamp, int likeCount) {
        this.id = id;
        this.smallUrl = smallUrl;
        this.timestamp = timestamp;
        this.timeStampDate = new java.util.Date((long) ((int) Integer.parseInt(timestamp)) * 1000);
        this.likeCount = likeCount;
        NextPost = null;

//        smallUrl.indexOf(".jpg?");
        Pattern p = Pattern.compile("\\d*_\\d*_\\d*_n");   // the pattern to search for
        Matcher m = p.matcher(smallUrl);
        m.find();
//        postCode = smallUrl.substring(smallUrl.indexOf("\\d*_\\d*_\\d*_n") + 7, smallUrl.indexOf(".jpg?"));
        postCode = m.group();
    }

    // ===================GETERS AND SETTERS===================
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        this.timeStampDate = new java.util.Date((long) ((int) Integer.parseInt(timestamp)) * 1000);
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getTimeStampDate() {
        return timeStampDate;
    }

    public void setTimeStampDate(String timestamp) {
        this.timeStampDate = new java.util.Date((long) ((int) Integer.parseInt(timestamp)) * 1000);
    }

    public Post getNextPost() {
        return NextPost;
    }

    public void setNextPost(Post NextPost) {
        this.NextPost = NextPost;
    }

    public static void sortPost(Post insert) {
        Post holder = main.root;
        Post prev = null;
        boolean done = false;
        System.out.println("A");
        while (holder != null && !done) {
            System.out.println(holder.timestamp);
            if (holder.getNextPost() == null) {
                System.out.println("B");
                holder.setNextPost(insert);
                done = true;
            } else {
                System.out.println("C " + holder.getNextPost().timestamp);
                System.out.println("Insert after holder " + insert.getTimeStampDate().after(holder.getTimeStampDate()));

                if (insert.getTimeStampDate().after(holder.getTimeStampDate())) {
                    System.out.println("D");
                    if (prev == null) {
                        System.out.println("E");
                        main.root = insert;
                    } else if (insert.getTimeStampDate().before(prev.getTimeStampDate())) {
                        System.out.println("F");
                        insert.setNextPost(holder);
                        prev.setNextPost(insert);
                        done = true;
                    }
                }
            }

            System.out.println("G");
            prev = holder;
            holder = holder.getNextPost();
        }

    }
}
