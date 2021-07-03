package service;

import model.User;
import org.apache.commons.codec.digest.DigestUtils;
import redis.clients.jedis.Jedis;
import util.JedisClient;


import java.util.ArrayList;
import java.util.List;

public class UserServiceRedisImpl {
    private static final String DB_PREFIX = "#u";

    private static final List<User> userDB = new ArrayList<>();
    private final Jedis client;



    public UserServiceRedisImpl(){
        client =  JedisClient.getInstance().getClient();
    }

    public void saveUser(User user){

        userDB.add(user);
    }

    public void updateUser(User user){

    }

    public void deleteUser(String nic){

    }

    public List<User> findAllUsers(){

        return userDB;
    }

    public List<User> findUsers(String query){
       return null;
    }

    public boolean authenticate(String username, String password){

        if (!client.exists(DB_PREFIX + username)){
            return false;
        }

        String originalPwdHash = client.get(DB_PREFIX + username);
        String pwdHash = DigestUtils.sha256Hex(password);

        return (originalPwdHash.equals(pwdHash));
    }



}
