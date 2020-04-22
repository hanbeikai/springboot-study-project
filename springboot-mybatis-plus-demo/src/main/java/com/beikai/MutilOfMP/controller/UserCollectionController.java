package com.beikai.MutilOfMP.controller;


import com.alibaba.fastjson.JSONObject;
import com.beikai.MutilOfMP.service.IUserCollectionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author beikai
 * @since 2019-09-01
 */
@RestController
@RequestMapping("/ReadAndWriterSpilt/user-collection-model")
public class UserCollectionController {

    @Autowired
    IUserCollectionService userCollectionService;

    @ApiOperation("增加")
    @PostMapping("/add")
    public JSONObject add(
            @RequestParam Long prodId,
            @RequestParam String userId
    ){
        return userCollectionService.add(prodId,userId);
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public JSONObject delete(
            @RequestParam Long id
    ){

        return userCollectionService.delete(id);
    }
    @ApiOperation("修改")
    @PostMapping("/update")
    public JSONObject update(
            @RequestParam Long id,
            Long prodId,
            String userId
    ){

        return userCollectionService.updateDemo(id,prodId,userId);
    }
    @ApiOperation("查询")
    @PostMapping("/get")
    public JSONObject get(
            @RequestParam Long id
    ){

        return userCollectionService.get(id);
    }




}

