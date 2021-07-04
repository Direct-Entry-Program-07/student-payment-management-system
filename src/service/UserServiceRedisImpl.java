package service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;
import redis.clients.jedis.Jedis;
import util.JedisClient;

import java.time.LocalDate;
import java.util.*;

public class UserServiceRedisImpl {
    private static final String DB_PREFIX = "#u";

    private static final List<User> userDB = new ArrayList<>();
    private final Jedis client;


    public UserServiceRedisImpl() {
        client = JedisClient.getInstance().getClient();
    }

    public void saveUser(User user) {
        if (!client.exists(DB_PREFIX + user.getUsername())) {
           // System.out.println(DB_PREFIX + user.getUsername());
            //System.out.println(user.getPassword());

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

    }

    public void updateUser(User user) {

    }

    public void deleteUser(String nic) {

    }

    public List<User> findAllUsers() {

        return userDB;
    }

    public List<User> findUsers(String query) {
        Long noOfUsers = client.dbSize();
        Set<String> allKeys = new HashSet<>();
        List<User> result = new ArrayList<>();
        User user = new User();


        String fullname;
        String usertype;
        String address;
        String contactNumber;
        String email;

        if (allKeys.isEmpty()){
            allKeys.add(client.randomKey());
        }

        loop1:
        for (int i = 0; i < noOfUsers; i++) {
            String s = client.randomKey();
            loop2:
            for (String key : allKeys) {
                if (key.equals(s)){
                    continue loop2;
                }
            }
            allKeys.add(s);
        }
       // System.out.println("ALL" + allKeys);



        for (String key : allKeys) {
            fullname = client.hget(key, "fullname");
            usertype = client.hget(key, "usertype");
            address = client.hget(key, "address");
            contactNumber = client.hget(key, "contact-number");
            email = client.hget(key, "email");

            if (fullname.contains(query) ||
                usertype.contains(query) ||
                address.contains(query) ||
                contactNumber.contains(query) ||
                email.contains(query)){

                user.setFullname(fullname);
                user.setUserType(usertype);
                user.setUsername("DATA");
                user.setPassword("DATA");
                user.setAddress(address);
                user.setContactNumber(contactNumber);
                user.setEmailAddress(email);
                user.setJoinedDate(LocalDate.now());

                result.add(user);
            }

        }
        return result;
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
