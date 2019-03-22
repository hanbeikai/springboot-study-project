package com.beikai.springbootredislock.mapper;

import com.beikai.springbootredislock.model.MiaoSha;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MiaoShaMapper {
    int deleteByPrimaryKey(String goodsCode);

    int insert(MiaoSha record);

    int insertSelective(MiaoSha record);

    MiaoSha selectByPrimaryKey(String goodsCode);

    int updateByPrimaryKeySelective(MiaoSha record);

    int updateByPrimaryKey(MiaoSha record);

    public int buy(@Param("goodsCode") String goodsCode, @Param("userId") String userId);

    public Integer getCount(@Param("goodsCode") String goodsCode);

}