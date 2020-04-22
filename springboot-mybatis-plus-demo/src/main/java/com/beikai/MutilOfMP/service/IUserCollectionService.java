package com.beikai.MutilOfMP.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beikai.MutilOfMP.model.UserCollectionModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author beikai
 * @since 2019-09-01
 */
public interface IUserCollectionService extends IService<UserCollectionModel> {

    JSONObject add(Long prodId, String userId);

    JSONObject delete(Long id);

    JSONObject updateDemo(Long id, Long prodId, String userId);

    JSONObject get(Long id);
}
