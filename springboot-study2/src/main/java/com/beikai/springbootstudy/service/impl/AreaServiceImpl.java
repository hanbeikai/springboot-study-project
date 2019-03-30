package com.beikai.springbootstudy.service.impl;

import com.beikai.springbootstudy.dao.AreaMapper;
import com.beikai.springbootstudy.entity.Area;
import com.beikai.springbootstudy.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AreaServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2018/11/22 0:50
 * @Version 1.0
 **/
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    /**
     * @Transactional(rollbackFor = RuntimeException.class) 添加事务的注解
     * @param area
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean addArea(Area area) {
        if (area.getAreaName() != null && !"".equals(area.getAreaName()) ){
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());

            try {
                int insert = areaMapper.insert(area);
                if (insert > 0){
                    return true;
                }else {
                    throw new RuntimeException("插入区域信息失败");
                }
            }catch (Exception e){
                throw new RuntimeException("插入区域信息失败 : " + e.getMessage());
            }
        }else{
            throw new RuntimeException("插入区域信息失败 : ");
        }
    }

    /**
     * 修改地域
     * @param area
     * @return
     */
    @Override
    public boolean updateArea(Area area) {
        if (area.getAreaId() != null && area.getAreaId() > 0){
            area.setLastEditTime(new Date());
            try {
                int i = areaMapper.updateByPrimaryKeySelective(area);
                if (i > 0){
                    return true;
                }else {
                    throw new RuntimeException("更新区域信息失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("修改区域信息出错 : " + e.getMessage());
            }
        }else {
            throw new RuntimeException("区域信息不能为空");
        }
    }

    /**
     * 删除区域信息
     * @param id
     * @return
     */
    @Override
    public boolean deleteArea(int id) {
        if (id > 0){
            try {
                int i = areaMapper.deleteByPrimaryKey(id);
                if (i > 0){
                    return true;
                }else {
                    throw new RuntimeException("删除失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("删除失败");
            }
        }else {
            throw new RuntimeException("要删除的信息不能为空");
        }
    }

    /**
     * 获取区域对象 根据id
     * @param id
     * @return
     */
    @Override
    public Area getArea(int id) {
        if (id > 0){
            Area area = areaMapper.selectByPrimaryKey(id);
            return area;
        }else {
            throw new RuntimeException("要查找的信息不能为空");
        }
    }

    /**
     * 获取所有区域对象
     * @return
     */
    @Override
    public List<Area> getAreaList() {

        return areaMapper.getAreaList();
    }
}
