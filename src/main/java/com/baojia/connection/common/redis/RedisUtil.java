package com.baojia.connection.common.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil<K, V> {
    @Autowired
	private RedisTemplate<String,String> redisTemplate;
    @Autowired
	private StringRedisTemplate stringRedisTemplate;
	/**  默认过期时长，单位：秒 */
	public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
	/**  不设置过期时长 */
	public final static long NOT_EXPIRE = -1;
	
    
	/**
     * 
    * @Title: getLock
    * @Description: 获取内存锁
    * @param @param key
    * @param @return    设定文件
    * @return boolean    返回类型
    * @throws
     */
	public boolean getLock(String lockKey) {
	    String key_mutex = lockKey + "_lock";
	    ValueOperations<String, String> operation = stringRedisTemplate.opsForValue();
	    long res=operation.increment(key_mutex,  1);//递增操作 
	    stringRedisTemplate.expire(key_mutex, 5*60, TimeUnit.SECONDS);
	    if(res == 1) {
	        return true;
	    }else {
	        return false;
	    }
	}
	/**
     * 
    * @Title: deleteLock
    * @Description: 删除内存锁
    * @param @param key
    * @param @return    设定文件
    * @return boolean    返回类型
    * @throws
     */
    public void deleteLock(String lockKey) {
        String key_mutex = lockKey + "_lock";
        stringRedisTemplate.delete(key_mutex);
    }
	/**
	 *  
	 * @param key 缓存的键值
	 * @param value 缓存的值
	 * @param s 缓存过期时间,当s=0或s为空时不过去
	 * @return 缓存的对象
	 */
	public void set(String key, String value,Long s) {
		ValueOperations<String, String> operation = stringRedisTemplate.opsForValue();
		if(s == null || s == 0 ) {
		    operation.set(key, value);
		}else {
		    operation.set(key, value, s, TimeUnit.SECONDS);
		}
	}
	/**
	 * 
	* @Title: set
	* @Description: 设置缓存值
	* @param @param key
	* @param @param value    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void set(String key, String value) {
	    ValueOperations<String, String> operation = stringRedisTemplate.opsForValue();
	    operation.set(key, value);
	}
	/**
	* @Description:  根据key删除缓存
	* @author guowei 
	* @date 2017年5月27日 下午5:04:23 
	* @param key 参数
	* @return void 返回类型 
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public void delete(String... key){  
        if(key!=null && key.length > 0){  
            if(key.length == 1){  
                stringRedisTemplate.delete(key[0]);  
            }else{  
                stringRedisTemplate.delete(CollectionUtils.arrayToList(key));               
            }  
        }  
    }  
	/**
	* @Description:  根据key 批量模糊(pattern*)删除(慎用)
	* @author guowei 
	* @date 2017年5月27日 下午5:05:35 
	* @param pattern 
	* @return void 返回类型 
	* @throws
	 */
//	public void batchRemoveValues(String... pattern) {
//		for (String p : pattern) {
//		    Set<String> set=stringRedisTemplate.keys(p + "*");
//			stringRedisTemplate.delete(set);
//		}
//	}
	/**
	* @Description:  根据key取得redis中缓存的字符串型value
	* @author guowei 
	* @date 2017年5月27日 下午5:10:26 
	* @param key
	* @return 参数
	* @return String 返回类型 
	* @throws
	 */
	public String get(String key){  
	    ValueOperations<String, String> valueOps =  stringRedisTemplate.opsForValue();
        return valueOps.get(key);
    } 
	/**
	* @Description:  设置指定的key过期时间(时间单位，秒)
	* @author guowei 
	* @date 2017年5月27日 下午4:54:48 
	* @param key
	* @param seconds
	* @return  如果key不存在，返回FALSE;存在返回TRUE，设置过期时间成功.
	* @return Boolean 返回类型 
	* @throws
	 */
    public Boolean expire(String key, long seconds) {
        return stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
    
	 

	/**
	 * 缓存Set
	 * @param key 缓存键值
	 * @param dataSet 缓存的数据
	 * @return 缓存数据的对象
	 */
	@SuppressWarnings("unchecked")
	public void setCacheSet(String key, Set<String> dataSet) {
		SetOperations<String, String> setOperation=stringRedisTemplate.opsForSet();
		setOperation.add(key, (String[])dataSet.toArray());
	}

	/**
	 * 获得缓存的set
	 * @param key
	 * @param
	 * @return
	 */
	public Set<String> getCacheSet(String key) {
		BoundSetOperations<String, String> operation = stringRedisTemplate.boundSetOps(key);
		return operation.members();
	}

	/** 
	 * 
	* @Title: hset
	* @Description:  缓存Map
	* @param @param key
	* @param @param dataMap
	* @param @return    设定文件
	* @return HashOperations<String,String,String>    返回类型
	* @throws
	 */
	public void hset(String key, Map<String, String> dataMap) {
		HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
		if (null != dataMap) {
			hashOperations.putAll(key, dataMap);
		}
	}

	/** 
	 * 
	* @Title: hgetString
	* @Description: 获得缓存的Map
	* @param @param key
	* @param @return    设定文件
	* @return Map<String,String>    返回类型
	* @throws
	 */
	public Map<String, String> hgetString(String key) {
	    HashOperations<String, String, String> hashOperations=stringRedisTemplate.opsForHash();
	    return hashOperations.entries(key);
	}
	/**
	 * 
	* @Title: hgetObject
	* @Description: 获得缓存的Map
	* @param @param key
	* @param @return    设定文件
	* @return Map<String,Object>    返回类型
	* @throws
	 */
	public Map<String, String> hgetObject(String key) {
	    HashOperations<String, String, String> hashOperations=stringRedisTemplate.opsForHash();
	    return hashOperations.entries(key);
	}

	/**
	 * 
	* @Title: hset
	* @Description: 把对象放入Hash中
	* @param @param key
	* @param @param field
	* @param @param value    设定文件
	* @return void    返回类型
	* @throws
	 */
    public void hset(String key, String field, String value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }
    /**
     * 
    * @Title: hget
    * @Description: 从Hash中获取String对象
    * @param @param key
    * @param @param field
    * @param @return    设定文件
    * @return Object    返回类型
    * @throws
     */
    public String hget(String key, String field) {
        HashOperations<String, String, String> hashOperations=stringRedisTemplate.opsForHash();
        return hashOperations.get(key, field);
    }
    /**
     * 
    * @Title: hget
    * @Description:从Hash中获取对象
    * @param @param key
    * @param @param field
    * @param @return    设定文件
    * @return Object    返回类型
    * @throws
     */
    public Object hget(String key, Object field) {
        return stringRedisTemplate.opsForHash().get(key, field);
    }

    /** 
     * 
    * @Title: hDel
    * @Description: 从Hash中删除对象
    * @param @param key
    * @param @param field
    * @param @return    设定文件
    * @return Long    返回类型
    * @throws
     */
    public Long hdel(String key, String... field) {
        HashOperations<String, String, String> hashOperations=stringRedisTemplate.opsForHash();
        Long res = hashOperations.delete(key, field);
        return res;
    }


    /**
     * 判断key中的field是否存在
     * @param key
     * @return
     */
    public boolean hhaskey(String key, String field) {
        return stringRedisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 判断key是否存在
     * @param
     * @return
     */
    public boolean exist(String key) {
        return stringRedisTemplate.hasKey(key);
    }
 
    /**
     * @Description:  向List尾部添加一个元素
     * @author guowei 
     * @date 2017年5月27日 下午4:46:35 
     * @param key 
     * @param value 参数
     * @return void 返回类型 
     * @throws
      */
     public Long ladd( String key, String value) {
         BoundListOperations<String, String> op=stringRedisTemplate.boundListOps(key);
         return op.rightPush(value);
     }
     
     /**
     * @Description:  获取缓存List集合的长度
     * @author guowei 
     * @date 2017年5月27日 下午4:46:05 
     * @param key
     * @return 返回List集合的长度
     * @return Long 返回类型 
     * @throws
      */
     public Long lsize( String key) {
         return stringRedisTemplate.boundListOps(key).size();
     }
     /**
     * @Description:  从redis中缓存List获取对象
     * @author guowei 
     * @date 2017年5月27日 下午4:48:08 
     * @param key
     * @param index
     * @return 参数
     * @return String 返回类型 
     * @throws
      */
     public String lget( String key, int index) {
         List<String> list = lget(key);
         if(list == null || list.size() ==0) {
             return null;
         }else {
             return list.get(index);
         }
     }

     /**
     * @Description:  删除List集合中的指定元素(对象)
     * @author guowei 
     * @date 2017年5月27日 下午4:49:06 
     * @param key
     * @param index 参数
     * @return void 返回类型 
     * @throws
      */
     public void lremove( String key, int index) {
         List<String> list = lget(key);
         list.remove(index);
         lset(key, list);
     }

     /**
     * @Description:  更新List集合指定索引位置的对象
     * @author guowei 
     * @date 2017年5月27日 下午4:49:57 
     * @param key
     * @param index
     * @param
     * @return void 返回类型 
     * @throws
      */
     public void lset( String key, int index, String value) {
         BoundListOperations<String, String> op = stringRedisTemplate.boundListOps(key);
         op.set(index, value);
     }
     
     
     /**
     * @Description:  获得缓存List集合(返回后redis中并未删除)
     * @author guowei 
     * @date 2017年5月27日 下午5:17:56 
     * @param key
     * @return 参数
     * @return List<String> 返回类型 
     * @throws
      */
     public List<String> lget( String key){
         List<String> dataList = new ArrayList<String>();
         ListOperations<String, String> listOperation = stringRedisTemplate.opsForList();
         Long size = listOperation.size(key);
         if(size != null && size > 0) {
             dataList = listOperation.range(key,0,size);
         }
          
         return dataList;
     }
     
     /**
     * @Description:  缓存List集合(如果key存在，与旧的list合并为新的集合)
     * @author guowei 
     * @date 2017年5月27日 下午5:24:20 
     * @param key
     * @param dataList
     * @return 参数
     * @return ListOperations<String,String> 返回类型 
     * @throws
      */
     public ListOperations<String, String> lset(String key, List<String> dataList) {
         ListOperations<String, String> listOperation = stringRedisTemplate.opsForList();
         if (null != dataList) {
             listOperation.leftPushAll(key, dataList);
         }
         return listOperation;
     }
     /**
      * 
     * @Title: lPopGet
     * @Description: 从列表头去除并移除第一个元素
     * @param key
     * @return
     * @return: String
     * @createUsers: zhangbin
     * @createTime: 2018年3月1日
      */
     public String lLeftPopGet(String key) {
         ListOperations<String, String> listOperation = stringRedisTemplate.opsForList();
         return listOperation.leftPop(key);
     }
     /**
      * 
     * @Title: lRightPopGet
     * @Description: 从列表尾部获取冰移除一个元素
     * @param key
     * @return
     * @return: String
     * @createUsers: zhangbin
     * @createTime: 2018年3月5日
      */
     public String lRightPopGet(String key) {
         ListOperations<String, String> listOperation = stringRedisTemplate.opsForList();
         return listOperation.rightPop(key);
     }
     
    /**
     * 
     * 根据key 模糊(pattern*)查询，返回List(效率低，慎用)
     * 
     * @param pattern key*
     * @return
     */
     public List<String> getList(String pattern){
    	 Set<String> set = stringRedisTemplate.keys(pattern);
    	 List<String> list = new ArrayList<String>();
		 Iterator<String> iterator = set.iterator();
		 while(iterator.hasNext()) {
			 list.add(get(iterator.next()));
		 }
    	 return list;
     }

	public void set(String key, Object value, long expire){
		stringRedisTemplate.opsForValue().set(key, toJson(value));
		if(expire != NOT_EXPIRE){
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
	}

	public void set(String key, Object value){
		set(key, value, DEFAULT_EXPIRE);
	}

	public <T> T get(String key, Class<T> clazz, long expire) {
		String value = stringRedisTemplate.opsForValue().get(key);
		if(expire != NOT_EXPIRE){
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
		return value == null ? null : fromJson(value, clazz);
	}

	public <T> T get(String key, Class<T> clazz) {
		return get(key, clazz, NOT_EXPIRE);
	}

	public  String get(String key, long expire) {
		String value = stringRedisTemplate.opsForValue().get(key);
		if(expire != NOT_EXPIRE){
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
		return value;
	}


	public void delete(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * Object转成JSON数据
	 */
	private String toJson(Object object){
		if(object instanceof Integer || object instanceof Long || object instanceof Float ||
				object instanceof Double || object instanceof Boolean || object instanceof String){
			return String.valueOf(object);
		}
		return JSON.toJSONString(object);
	}

	/**
	 * JSON数据，转成Object
	 */
	private <T> T fromJson(String json, Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}
     
}
