
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matthew
 */
public class main {

    public static Post root;
    public static HashMap<String, Likes> likes = new HashMap();
    public static HashMap<String, Post> posts = new HashMap();
    public static ArrayList<String> orderedPosts = new ArrayList();
    public static HashMap<String, PersonData> persons = new HashMap();
    public static Likes following = new Likes("following", 0);
    public static Likes followers = new Likes("followers", 0);

    public static void main(String args[]) {
        root = null;
        File file = new File("a.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            boolean flag = false;
            String combined = "";
            String st;
            while ((st = br.readLine()) != null) {
                if (!flag && st.contains("&A4d")) {
                    if (st.contains("&9D!")) {
                        combined = (st.substring(st.indexOf("&A4d") + 4, st.indexOf("&9D!")));
                        processJSON(combined);
                    } else {
                        flag = true;
                        combined = st.substring(st.indexOf("&A4d") + 4, st.length());
                    }
                } else if (flag && st.contains("&9D!")) {
                    flag = true;
                    combined = combined + (st.substring(0, st.indexOf("&9D!")));
                    processJSON(combined);
                    flag = false;
                    combined = "";
                } else if (flag) {
                    combined = combined + st;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Post holder = root;
        while (holder != null) {
            orderedPosts.add(holder.getId());
            holder = holder.getNextPost();
        }

//        for (String s : posts.keySet()) {
//            orderedPosts.add(s);
//        }
//        Collections.sort(orderedPosts);
//        Collections.reverse(orderedPosts);
        System.out.println("OK");
        System.out.println("likes size " + likes.size());
        System.out.println("posts size " + posts.size());
        System.out.println("orderedPosts " + orderedPosts.size());

//        for (Likes s : likes.values()) {
//            System.out.println("=========" + s.getId() + "=========" + s.people.size());
//            for (Person p : s.people.values()) {
//                System.out.println(p.getId() + " " + p.getUsername());
//            }
//        }
        for (String s : orderedPosts) {
            Post post = posts.get(s);
            System.out.println("=========" + post.getId() + "=========" + post.getLikeCount());
            Likes like = likes.get(s);
            if (like != null) {
                for (Person p : like.getPeople().values()) {
                    if (persons.containsKey(p.id)) {
                        PersonData pd = persons.get(p.id);
                        pd.setEarliestLike(post.timestamp);
                        pd.setLikeCount(pd.getLikeCount() + 1);

                        if (pd.latestLike.equals("")) {
                            pd.setLatestLike(post.timestamp);
                        }

                    } else {
                        PersonData pd = new PersonData(p.id, p.username);
                        pd.setLatestLike(post.timestamp);
                        pd.setEarliestLike(post.timestamp);
                        persons.put(p.id, pd);

                    }
                }
            }
        }

        try {
            FileWriter myWriter = new FileWriter("csv.txt");
            for (PersonData pd : persons.values()) {
                myWriter.write(pd.id + "\t" + pd.userName + "\t" + pd.likeCount + "\t" + pd.getLatestLike() + "\t" + pd.getEarliestLike() + "\t" + pd.latestLike + "\t" + pd.earliestLike + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

            FileWriter followWriter = new FileWriter("follow.txt");
            boolean isFollowing = false;
            for (int i = following.order - 1; i >= 0; i--) {
                isFollowing = false;
                for (Person p : followers.people.values()) {
                    if (p.getId().equals(following.getPeople().get(i).getId())) {
                        isFollowing = true;
                    }
                }
                if (!isFollowing) {
                    followWriter.write(following.getPeople().get(i).getUsername() + "\n");
                }
            }
            followWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame2().setVisible(true);
            }
        });
    }

    public static void processJSON(String st) {
        GsonBuilder builder = new GsonBuilder();

        LinkedTreeMap o = (LinkedTreeMap) builder.create().fromJson(st, Object.class);

        LinkedTreeMap loc = (LinkedTreeMap) getDeepValue(o, "data:shortcode_media:edge_liked_by");
        if (loc != null) {
            processLikes(o, loc);
        }

        loc = (LinkedTreeMap) getDeepValue(o, "data:shortcode_media:edge_media_preview_like");
        if (loc != null) {
            processPost(o, loc);
        }

        loc = (LinkedTreeMap) getDeepValue(o, "data:user:edge_follow");
        if (loc != null) {
            processFollowing(o, loc);
        }

        loc = (LinkedTreeMap) getDeepValue(o, "data:user:edge_followed_by");
        if (loc != null) {
            processFollowers(o, loc);
        }

    }

    public static void processLikes(LinkedTreeMap o, LinkedTreeMap loc) {
        LinkedTreeMap scm = (LinkedTreeMap) getDeepValue(o, "data:shortcode_media");
        Likes current = null;

        if (likes.containsKey(scm.get("id"))) {
            current = likes.get(scm.get("id"));
        } else {

            String id = (String) scm.get("id");

//            System.out.println("BBB " + id);
            loc = (LinkedTreeMap) getDeepValue(o, "data:shortcode_media:edge_liked_by");

            int count = (int) (double) loc.get("count");
//            System.out.println(count);
            Likes newLike = new Likes(id, count);
            likes.put(id, newLike);
            current = newLike;
        }

        ArrayList x = (ArrayList) getDeepValue(o, "data:shortcode_media:edge_liked_by:edges");
        for (int i = 0; i < x.size(); i++) {
            LinkedTreeMap node = (LinkedTreeMap) x.get(i);
            LinkedTreeMap nodeDeeper = (LinkedTreeMap) node.get("node");
            String id = (String) nodeDeeper.get("id");
            String username = (String) nodeDeeper.get("username");
            current.addPerson(id, username);
        }
    }

    public static void processFollowing(LinkedTreeMap o, LinkedTreeMap loc) {
        ArrayList x = (ArrayList) loc.get("edges");
        for (int i = 0; i < x.size(); i++) {
            LinkedTreeMap node = (LinkedTreeMap) x.get(i);
            LinkedTreeMap nodeDeeper = (LinkedTreeMap) node.get("node");
            String id = (String) nodeDeeper.get("id");
            String username = (String) nodeDeeper.get("username");
            following.addPerson(id, username);
        }
    }

    public static void processFollowers(LinkedTreeMap o, LinkedTreeMap loc) {
        ArrayList x = (ArrayList) loc.get("edges");
        for (int i = 0; i < x.size(); i++) {
            LinkedTreeMap node = (LinkedTreeMap) x.get(i);
            LinkedTreeMap nodeDeeper = (LinkedTreeMap) node.get("node");
            String id = (String) nodeDeeper.get("id");
            String username = (String) nodeDeeper.get("username");
            followers.addPerson(id, username);
        }
    }

    public static void processPost(LinkedTreeMap o, LinkedTreeMap loc) {
        LinkedTreeMap scm = (LinkedTreeMap) getDeepValue(o, "data:shortcode_media");

        if (!posts.containsKey(scm.get("id"))) {
            String id = (String) scm.get("id");
            String timestamp = ((int) (double) scm.get("taken_at_timestamp")) + "";
//            System.out.println(timestamp);

//            System.out.println(loc);
            ArrayList x = (ArrayList) scm.get("display_resources");
            LinkedTreeMap node = (LinkedTreeMap) x.get(0);
            String smallUrl = (String) node.get("src");
//            System.out.println(smallUrl);

            scm = (LinkedTreeMap) getDeepValue(o, "data:shortcode_media:edge_media_preview_like");
            int likeCount = (int) (double) loc.get("count");

            Post newPost = new Post(id, smallUrl, timestamp, likeCount);

            posts.put(id, newPost);
            if (root == null) {
                root = newPost;
            } else {
                Post.sortPost(newPost);
            }

        }

    }

    public static Object getDeepValue(LinkedTreeMap map, String s) {
        Object x = null;
        String[] route = s.split(":");
        LinkedTreeMap deeper = map;
        for (int i = 0; i < route.length - 1; i++) {
            if (deeper != null) {
                deeper = (LinkedTreeMap) deeper.get(route[i]);
            } else {
                return null;
            }
        }
        if (deeper != null) {
            x = deeper.get(route[route.length - 1]);
        } else {
            return null;
        }

        return x;
    }
}
