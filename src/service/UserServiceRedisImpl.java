package service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;
import redis.clients.jedis.Jedis;
import util.JedisClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceRedisImpl {
    private static final String DB_PREFIX = "#u";

    private static final List<User> userDB = new ArrayList<>();
    private final Jedis client;


    public UserServiceRedisImpl() {
        client = JedisClient.getInstance().getClient();
    }

    public void saveUser(User user) {
        if (!client.exists(DB_PREFIX + user.getUsername())) {

        }

        System.out.println(DB_PREFIX + user.getUsername());
        System.out.println(user.getPassword());

        String key = DB_PREFIX + user.getUsername();
        Map<String, String> userInfo = new HashMap<>();

        userInfo.put("usertype", user.getUserType());
        userInfo.put("fullname", user.getFullname());
        userInfo.put("password", user.getPassword());
        userInfo.put("address", user.getAddress());
        userInfo.put("contact-number", user.getContactNumber());
        userInfo.put("email", user.getEmailAddress());

        client.hset(key, userInfo);
    }

    public void updateUser(User user) {

    }

    public void deleteUser(String nic) {

    }

    public List<User> findAllUsers() {

        return userDB;
    }

    public List<User> findUsers(String query) {
        return null;
    }

    public User findUser(String nic) {
        for (User user : userDB) {
          /*  if (user.getNic().equals(nic)) {
                return user;
            }*/
        }
        return null;
    }

    public boolean authenticate(String username, String inputPw) {
        String originalPwdHash = "";
        String pwdHash = "";


        if (!client.exists(DB_PREFIX + username)) {
            return false;
        }

            originalPwdHash = client.hget(DB_PREFIX + username, "password");
            System.out.println(originalPwdHash);
            pwdHash = DigestUtils.sha256Hex(inputPw);

        return (originalPwdHash.equals(pwdHash));
    }


}
