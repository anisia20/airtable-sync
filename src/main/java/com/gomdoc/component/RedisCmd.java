package com.gomdoc.component;

import java.util.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.json.bind.Jsonb;

import com.google.gson.reflect.TypeToken;
import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.redis.client.Response;

@Singleton
public class RedisCmd {
	
	@Inject
    RedisClient redisClient; 

    @Inject
    ReactiveRedisClient reactiveRedisClient; 
    
	@Inject
	Jsonb jsonb;
    
	public String keys(String key) {
    	Response result;
    	try {
    		result = redisClient.keys(key);
		} catch (Exception e) {
			result = null;
		}
    	if(result == null) {
    		return null;
    	}
        return result.toString();
    }
	
    public Uni<Void> del(String key) {
        return reactiveRedisClient.del(Arrays.asList(key))
                .map(response -> null);
    }

    public String get(String key) {
    	Response result = null;
    	try {
    		result = redisClient.get(key);
    	}catch(Exception e) {
    		return null;
    	}
    	if(result == null) {
    		return null;
    	}
        return result.toString();
    }

    public void set(String key, Object value) {
        redisClient.set(Arrays.asList(key, jsonb.toJson(value)));
    }
    
    public int push(String key, Object value) {
    	try {
    		redisClient.lpush(Arrays.asList(key, jsonb.toJson(value)));
    	}catch (Exception e) {
    		return -1;
    	}
    	return 0;
    }
    
    public int rpush(String key, Object value) {
    	try {
    		redisClient.rpush(Arrays.asList(key, jsonb.toJson(value)));
    	}catch (Exception e) {
    		return -1;
    	}
    	return 0;
    }
    
    public String pop(String key) {
    	Response result;
    	
    	try {
    		result = redisClient.rpop(key);
		} catch (Exception e) {
			result = null;
		}
    	
    	if(result == null) {
    		return null;
    	}
        return result.toString();
    }
    
    public String lpop(String key) {
    	Response result;
    	
    	try {
    		result = redisClient.lpop(key);
		} catch (Exception e) {
			result = null;
		}
    	
    	if(result == null) {
    		return null;
    	}
        return result.toString();
    }
    
    public String hget(String key, String field) {
    	Response result;
    	try {
    		result = redisClient.hget(key, field);
		} catch (Exception e) {
			result = null;
		}
    	if(result == null) {
    		return null;
    	}
        return result.toString();
    }
    
    public int hput(String key, String field, Object value) {
    	try {
    		redisClient.hset(Arrays.asList(key, jsonb.toJson(value)));
    	}catch (Exception e) {
    		return -1;
    	}
    	return 0;
    }
    
    public int hput(String key, String field, Object value, String expire) {
    	try {
	    	redisClient.hset(Arrays.asList(key, field, jsonb.toJson(value)));
    	}catch (Exception e) {
    		return -1;
    	}
	    if(expire(key, expire) < 0 ) {
	    	redisClient.hdel(Arrays.asList(key, field));
	    	return -1;
	    }
	    return 0;
    }

	public int hputpure(String key, String field, String value) {
		try {
			redisClient.hset(Arrays.asList(key, field, value));
		}catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public int hputallpure(String key, Map<String, String> data) {
		try{
			for(String field: data.keySet()){
				hputpure(key, field, data.get(field));
			}
		}catch (Exception e) {
			return -1;
		}
		return 0;
	}

    public Map<String, Object> hgetall(String key) {
    	Response result;
    	try {
    		result = redisClient.hgetall(key);
		} catch (Exception e) {
			result = null;
		}
    	if(result == null) {
    		return null;
    	}
    	Map<String, Object> resultMap = new HashMap<>();
    	try {
    		String tmpKey = null;
    		Object jsonObj = "";
    		for(int i = 0 ; i < result.size(); i ++) {
    			jsonObj = result.get(i);
    			if(i%2 == 0) {
    				tmpKey = jsonObj.toString();
    			} else {
    				resultMap.put(tmpKey, jsonObj);
    			}
    		}
		} catch (Exception e) {
			result = null;
		}
    	
        return resultMap;
    }
    
    public int zadd(String key, String score, String value, String expire) {
    	try {
    		redisClient.zadd(Arrays.asList(key, score, jsonb.toJson(value)));
    	}catch (Exception e) {
    		return -1;
    	}
    	if(expire(key, expire) < 0 ) {
    		redisClient.zrem(Arrays.asList(key, jsonb.toJson(value)));
    		return -1;
    	}
    	return 0;
    }
    
    public int zincrby(String key, String score, Object value, String expire) {
    	try {
    		redisClient.zincrby(key, score, jsonb.toJson(value));
    	}catch (Exception e) {
    		return -1;
    	}
    	if(expire(key, expire) < 0) {
    		redisClient.zincrby(key, "-"+score, jsonb.toJson(value));
    		return -1;
    	}
    	return 0;
    }
    
    public int zincrby(String key, String score, String value, String expire) {
    	try {
    		redisClient.zincrby(key, score, value);
    	}catch (Exception e) {
    		return -1;
    	}
    	if(expire(key, expire) < 0) {
    		redisClient.zincrby(key, "-"+score, value);
    		return -1;
    	};
    	return 0;
    }
    
    public int expire(String key, String value) {
    	try {
    		redisClient.expire(key, value);
    	} catch (Exception e) {
    		return -1;
    	}
    	return 0;
    }
    
}
