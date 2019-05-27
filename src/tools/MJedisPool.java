package tools;

import redis.clients.jedis.Jedis;

public class MJedisPool {
    public static void main(String[] args) {
        Jedis jedis = null;
       try{
           jedis = new Jedis("127.0.0.1",6379);
           jedis.auth("syk");
           jedis.set("hello","word");
           String value = jedis.get("hello");
           System.out.println(value);
           System.out.println(jedis.keys("*"));
       }catch (Exception e){
            throw e;
       }finally {
           if(jedis != null){
               jedis.close();
           }
       }
    }
}
