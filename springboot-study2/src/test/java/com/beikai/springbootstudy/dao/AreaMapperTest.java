package com.beikai.springbootstudy.dao;

import com.beikai.springbootstudy.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaMapperTest {

    @Autowired
    private AreaMapper areaMapper;

    @Test
    public void getAreaList() {
        List<Area> areaList = areaMapper.getAreaList();
        // 验证预期值和实际值是否相符
        if (areaList != null && areaList.size() >0){
            System.out.println("返回的值是 : " + areaList.size());
        }else {
            System.out.println("没有返回数据");
        }

}}