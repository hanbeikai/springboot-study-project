package com.beikai.springbootthread.service;

import com.beikai.springbootthread.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *  测试线程的接口
 */
public interface IThreadService {

    public Object getUserInfoByRunnable();

    public Object getUserInfoByCallable() throws ExecutionException, InterruptedException;

    public List<User> getUser();

}
