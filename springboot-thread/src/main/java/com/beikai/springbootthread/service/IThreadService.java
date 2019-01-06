package com.beikai.springbootthread.service;

import java.util.concurrent.ExecutionException;

/**
 *  测试线程的接口
 */
public interface IThreadService {

    public Object getUserInfoByRunnable();

    public Object getUserInfoByCallable() throws ExecutionException, InterruptedException;
}
