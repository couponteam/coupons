
package com.masou.coupon.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 主键 唯一键生成
 * Created by paul on 16/12/21.
 */
public class CommonKeyUtils {

    //2016-01-01的时间戳
    private final static long twepoch = 1451577600446L;

    // 毫秒内自增位
    private final static long sequenceBits = 12L;

    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static long lastTimestamp = -1L;

    private static long sequence = 0L;

    public static Random random = new Random();

    //随机数序列
    private final static int randBits = 10;

    private static int rand = 0;

    private static int randMask = -1 ^ (-1 << randBits);

    /**
     * 生成主键key
     * <p>
     * 时间戳(42位) + 序列(12位) + 随机数(10位)
     *
     * @return
     */
    public static synchronized Long genUniqueKey() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            try {
                throw new Exception("Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        rand = random.nextInt(randMask);

        return ((timestamp - twepoch) << 22 | sequence << 10 | rand);
    }

    private static long tilNextMillis(final long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }


    /**
     * 生成用户领券时的唯一券号
     * @param uid
     * @return
     */
    public static String genTicketKey(Long uid){
        int rand = random.nextInt(randMask);
        String uid_four = uid.toString().substring(uid.toString().length()-4,uid.toString().length());
        String uuid = UUID.randomUUID().toString().replaceAll("-","").substring(0,6).toUpperCase();
        return uuid + rand + uid_four;
    }

    /**
     * 生成用户领券时的唯一券号
     * @param
     * @return
     */
    public static String fcode(String ip){
        int rand = random.nextInt(randMask);
        int ipInt = 0;
        if (ip != null && ip.trim().length() > 1){
            if (ip.contains(":")){
                ipInt = Integer.parseInt(ip.replaceAll(":",""));
            }
            else {
                ipInt = Integer.parseInt(ip.replaceAll(".",""));
            }
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-","").substring(0,4).toUpperCase();
        return uuid + ipInt + rand;
    }


    private static long timeGen() {
        return System.currentTimeMillis();
    }

}
