package com.beikai.springbootstudy.service;

import com.beikai.springbootstudy.entity.Area;

import java.util.List;

/**
 * 接口
 */
public interface AreaService {
    public boolean addArea(Area area);
    public boolean updateArea(Area area);
    public boolean deleteArea(int id);
    public Area getArea(int id);
    public List<Area> getAreaList();
}
