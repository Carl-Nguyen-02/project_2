package DAO;

import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import model.Customer;
import model.Product;
import org.bson.Document;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import model.TransactionInfo;
import org.bson.Document;
import redis.clients.jedis.*;

import java.util.*;
import java.util.function.Supplier;

public class Redis {
    private UnifiedJedis jedisClient;
    private MongoDatabase mongoDatabase;
    private static final int CACHE_EXPIRY_SECONDS = 600; // 10 minutes
    private static final Gson gson = new Gson();

    public Redis(MongoDB mongoDB) {
        JedisClientConfig config = DefaultJedisClientConfig.builder()
                .user("default")
                .password("GFbvOkgD4sGHcSeoG0RbsfAenzR3un3M")
                .build();
        this.jedisClient = new UnifiedJedis(
                new HostAndPort("redis-17985.c292.ap-southeast-1-1.ec2.redns.redis-cloud.com", 17985),
                config
        );
        this.mongoDatabase = mongoDB.getDatabase();
    }
}
