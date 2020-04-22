package com.beikai.MutilOfMP.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beikai.MutilOfMP.mapper.UserCollectionMapper;
import com.beikai.MutilOfMP.model.UserCollectionModel;
import com.beikai.MutilOfMP.service.IUserCollectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author beikai
 * @since 2019-09-01
 */
@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollectionModel> implements IUserCollectionService {


    @Override
    //@DS("master")
    public JSONObject add(Long prodId, String userId) {
        if (save(new UserCollectionModel().setProdId(prodId).setUserId(userId).setCreateTime(LocalDateTime.now()))){
            return resultSuccess(true);
        }
        return resultError("添加失败");
    }


    @Override
    //@DS("master")
    public JSONObject delete(Long id) {
        if (removeById(id)){
            return resultSuccess(true);
        }
        return resultError("删除失败");
    }

    @Override
    //@DS("master")
    public JSONObject updateDemo(Long id, Long prodId, String userId) {

        if (update(new UpdateWrapper<UserCollectionModel>().lambda().set(prodId != null,UserCollectionModel::getProdId,prodId).set(StringUtils.isNotEmpty(userId),UserCollectionModel::getUserId,userId).eq(UserCollectionModel::getId,id))){
            return resultSuccess(true);
        }
        return resultError("更新失败");
    }

    @Override
    //@DS("slave_1")
    public JSONObject get(Long id) {
        return resultSuccess(getById(id));
    }

    private JSONObject resultSuccess(Object result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("date",result);
        jsonObject.put("message","成功");
        return jsonObject;
    }

    private JSONObject resultError(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",500);
        jsonObject.put("message",message);
        return jsonObject;
    }


}
