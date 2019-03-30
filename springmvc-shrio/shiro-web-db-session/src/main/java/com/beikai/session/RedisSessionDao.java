package com.beikai.session;

import com.beikai.utils.JedisUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 重写 redis的方法
 */
public class RedisSessionDao extends AbstractSessionDAO {

    @Resource
    private JedisUtils jedisUtils;

    private final String shiro_session_prefix = "beikai-session";

    // 创建一个转换字节对象的方法
    public byte[] getKey(String key){
        return (shiro_session_prefix + key).getBytes();
    }

    /**
     * 保存session中到redis中的方法
     * @param session
     */
    public void saveSession(Session session){
        if (session != null && session.getId() != null) {
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            // 存到redis中
            jedisUtils.set(key, value);
            // 设置超时时间
            jedisUtils.expire(key, 600);
        }
    }

    /**
     * 增加session的方法
     * @param session
     * @return
     */
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        // 生成sessionid后 把sessionid与session进行捆绑
        assignSessionId(session,sessionId);
        // 保存session到redis中
        saveSession(session);
        return sessionId;
    }

    /**
     * 读取session的方法
     * @param serializable
     * @return
     */
    protected Session doReadSession(Serializable serializable) {
        if (serializable == null){
            return null;
        }
        byte[] key = getKey(serializable.toString());
        byte[] value = jedisUtils.get(key);
        // 获取值之后 通过反序列化方法 将value数组反序列化成session对象
        return (Session) SerializationUtils.deserialize(value);
    }

    /**
     * 修改session的方法
     * @param session
     * @throws UnknownSessionException
     */
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    /**
     * 删除session的方法
     * @param session
     */
    public void delete(Session session) {
        if (session == null && session.getId() == null){
            return;
        }
        byte[] key = getKey(session.getId().toString());
        jedisUtils.del(key);
    }

    /**
     * 获取所有活动的session的方法
     * @return
     */
    public Collection<Session> getActiveSessions() {
        // 通过之前定义的前缀获取所有的key
        Set<byte[]> set = jedisUtils.keys(shiro_session_prefix);
        // 创建session的集合
        Set<Session> sessions = new HashSet<Session>();
        // 判断是否有活动的session  没有返回为null
        if (CollectionUtils.isEmpty(set)){
            return sessions;
        }
        // 遍历获取的session 通过反序列化的方式获取session,添加到集合中
        for (byte[] key : set) {
            Session session = (Session) SerializationUtils.deserialize(jedisUtils.get(key));
            sessions.add(session);
        }
        // 返回session集合
        return sessions;
    }
}
