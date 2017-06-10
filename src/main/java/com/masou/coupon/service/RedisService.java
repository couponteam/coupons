package com.masou.coupon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * Created by Paul on 16/4/10.
 */
@Component
public class RedisService {

    private static int DEFAULT_TIME=30*60;
    //操作redis客户端
    private static Jedis jedis;
    @Autowired
    @Qualifier("redisConnectionFactory")
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * 通过key删除（字节）
     *
     * @param key
     */
    public void del(byte[] key) {
        this.getJedis().del(key);
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public void del(String key) {
        this.getJedis().del(key);
    }

    /**
     * 添加key value 并且设置存活时间(byte)
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte[] key, byte[] value, int liveTime) {
        this.set(key, value);
        this.getJedis().expire(key, liveTime);
    }

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, int liveTime) {
        this.set(key, value);
        this.getJedis().expire(key, liveTime);
    }

    /**
     * 存入数组
     * @param key
     * @param map
     * @param expire
     */
    public void mapSet(String key, Map<String, String> map, int expire){
        this.getJedis().hmset(key, map);
        this.getJedis().expire(key, expire);
    }

    /**
     * 从redis的map中获取一个值
     * @param key
     * @param field
     * @return
     */
    public String mapGet(String key, String field){
        return this.getJedis().hget(key, field);
    }

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        this.getJedis().set(key, value);
    }

    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        this.getJedis().set(key, value);
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String value = this.getJedis().get(key);
        return value;
    }

    /**
     * 获取redis value (byte [] )(反序列化)
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        return this.getJedis().get(key);
    }

    /**
     * 通过正则匹配keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return this.getJedis().keys(pattern);
    }

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return this.getJedis().exists(key);
    }

    /**
     * 清空redis 所有数据
     *
     * @return
     */
    public String flushDB() {
        return this.getJedis().flushDB();
    }

    /**
     * 查看redis里有多少数据
     */
    public long dbSize() {
        return this.getJedis().dbSize();
    }

    /**
     * 检查是否连接成功
     *
     * @return
     */
    public String ping() {
        return this.getJedis().ping();
    }

    /**
     * 获取一个jedis 客户端
     *
     * @return
     */
    private Jedis getJedis() {
        if (jedis == null) {
            return jedisConnectionFactory.getShardInfo().createResource();
        }
        return jedis;
    }

    private RedisService() {

    }

}