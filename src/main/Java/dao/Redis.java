package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;


public class Redis {

    public static Jedis jedis;//非切片额客户端连接
    public static JedisPool jedisPool;//非切片连接池
    public static ShardedJedis shardedJedis;//切片额客户端连接
    public static ShardedJedisPool shardedJedisPool;//切片连接池


    public Redis() {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();
    }


    public static Jedis getJedis() {
        if (jedis == null) {
            initialPool();
            jedis = jedisPool.getResource();
        }
        return jedis;
    }


    /**
     * 初始化非切片池
     */
    private static void initialPool() {
        if (jedisPool == null) {
            // 池基本配置
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxWaitMillis(60 * 1000);
//        config.setMaxActive(20);
            config.setMaxIdle(5);
//        config.setMaxWait(1000l);
            config.setTestOnBorrow(false);

            jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        }
    }

    /**
     * 初始化切片池
     */
    private static void initialShardedPool() {
        if (shardedJedisPool == null) {
            // 池基本配置
            JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxActive(20);
            config.setMaxIdle(5);
            config.setMaxWaitMillis(60 * 1000);
//        config.setMaxWait(1000l);
            config.setTestOnBorrow(false);
            // slave链接
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
            shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

            // 构造池
            shardedJedisPool = new ShardedJedisPool(config, shards);
        }
    }

    public void show() {
        KeyOperate();
        StringOperate();
        ListOperate();
        SetOperate();
        SortedSetOperate();
        HashOperate();
    }

    private void KeyOperate() {
    }

    private void StringOperate() {
    }

    private void ListOperate() {
    }

    private void SetOperate() {
    }

    private void SortedSetOperate() {
    }

    private void HashOperate() {
    }

    public void test() {
        Set keys = jedis.keys("*");
        System.out.println(keys);
    }

    public static void main(String[] args) {
        Redis hl = new Redis();
        hl.test();
    }
}