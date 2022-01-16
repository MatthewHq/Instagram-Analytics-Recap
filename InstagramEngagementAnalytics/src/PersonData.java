
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matthew
 */
public class PersonData {

    int likeCount;
    String id;
    String userName;
    String earliestLike;
    String latestLike;

    public PersonData(String id, String userName) {
        this.id = id;
        this.userName = userName;
        likeCount = 1;
        earliestLike = "";
        latestLike = "";
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEarliestLike() {
        if (likeCount != 0) {
            java.util.Date time1 = new java.util.Date((long) ((int) Integer.parseInt(earliestLike)) * 1000);
            String pattern = "MMM dd yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(time1);
        } else {
            return "NONE";
        }

    }

    public void setEarliestLike(String earliestLike) {
        this.earliestLike = earliestLike;
    }

    public String getLatestLike() {

        if (likeCount != 0) {
            java.util.Date time1 = new java.util.Date((long) ((int) Integer.parseInt(latestLike)) * 1000);
            String pattern = "MMM dd yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(time1);
        } else {
            return "NONE";
        }
    }

    public void setLatestLike(String latestLike) {
        this.latestLike = latestLike;
    }

}
