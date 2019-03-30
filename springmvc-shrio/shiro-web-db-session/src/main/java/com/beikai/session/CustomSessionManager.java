package com.beikai.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * 自定义的 sessionmanager对象
 *  使用原来的sessionmanager的时候 当进行一个操作的时候,会多次访问redis,对redis造成压力,现在通过自定义的sessionmanager的方式,
 *  把session存进request中,当下一次需要使用session的时候,会调用request中的session,减少对redis的访问次数
 *  当request中没有session中,把session存进request中
 *  如果request中有session,从request中把session取出来
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        // 通过sessionkey获取sessionid
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        // 通过sessionkey 获取 request
        if (sessionKey instanceof WebSessionKey){
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        // 判断 request中是否有session  如果有 返回session
        if (request != null && sessionId != null){
            Session session = (Session) request.getAttribute(sessionId.toString());
            if (session != null){
                return session;
            }
        }
        // 获取session
        Session session = super.retrieveSession(sessionKey);
        // 如果request中没有session 则在request中添加session  返回session
        if (request != null && sessionId != null){
            // 在request中添加session
            request.setAttribute(sessionId.toString(),session);
        }
        return session;
    }
}
