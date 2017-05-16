package test;

import redis.clients.jedis.Jedis;

/**
 * Created by jason on 2017/5/14.
 */
public class JedisDemo {

    public static void main(String args[]){
        Jedis test = new Jedis("121.42.153.133",6379);
        test.auth("jasonlin");
        test.set("hw","hello world");

        System.out.println(test.info());

        String hello = test.get("hw");
        System.out.println(hello);
    }

}
