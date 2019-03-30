package com.beikai.springbootstudy.controller;

import com.beikai.springbootstudy.entity.Area;
import com.beikai.springbootstudy.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AreaController
 * @Description TODO
 * @Author Admin
 * @Date 2018/11/20 21:06
 * @Version 1.0
 **/
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 获取所有区域信息
     * @return
     */
    @RequestMapping(value = "/getAreaList",method = RequestMethod.GET)
    public Map<String,Object> getAreaList(){
        Map<String,Object> map = new HashMap<>();
        List<Area> areaList = areaService.getAreaList();
        map.put("areaList",areaList);
        return map;
    }

    /**
     * 获取单个区域信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getArea",method = RequestMethod.GET)
    public Map<String,Object> getArea(Integer id){
        Map<String,Object> map = new HashMap<>();
        Area areaList = areaService.getArea(id);
        map.put("area",areaList);
        return map;
    }

    /**
     * 添加区域信息
     * @param area
     * @return
     */
    @RequestMapping(value = "/addArea",method = RequestMethod.POST)
    public Map<String,Object> addArea(@RequestBody Area area){
        Map<String,Object> map = new HashMap<>();
        boolean b = areaService.addArea(area);
        map.put("success",b);
        return map;
    }

    /**
     * 修改区域信息
     * @param area
     * @return
     */
    @RequestMapping(value = "/updateArea",method = RequestMethod.POST)
    public Map<String,Object> updateArea(@RequestBody Area area){
        Map<String,Object> map = new HashMap<>();
        boolean b = areaService.updateArea(area);
        map.put("success",b);
        return map;
    }

    /**
     * 删除区域信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteArea",method = RequestMethod.GET)
    public Map<String,Object> deleteArea(Integer id){
        Map<String,Object> map = new HashMap<>();
        boolean b = areaService.deleteArea(id);
        map.put("success",b);
        return map;
    }

}
