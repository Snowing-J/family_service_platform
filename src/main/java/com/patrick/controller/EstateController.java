package com.patrick.controller;

import com.alibaba.fastjson.JSONObject;
import com.patrick.bean.FcBuilding;
import com.patrick.bean.FcEstate;
import com.patrick.bean.TblCompany;
import com.patrick.returnJson.ReturnObject;
import com.patrick.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {}, allowCredentials = "true")
public class EstateController {

    @Autowired
    private EstateService estateService;

    @RequestMapping("/estate/selectCompany")
    public String selectEstate(){
        System.out.println("selectCompany");
        List<TblCompany> tblCompanies = estateService.selectConmpany();
        return JSONObject.toJSONString(new ReturnObject(tblCompanies));
    }

    @RequestMapping("/estate/insertEstate")
    public String insertEstate(FcEstate fcEstate){
        System.out.println("需要插入的数据如下： " + fcEstate);

        Integer result = estateService.insertEstate(fcEstate);
        if(result == 0){
            return JSONObject.toJSONString(new ReturnObject("0", "房产编码已经存在！"));
        }else {
            return JSONObject.toJSONString(new ReturnObject("1", "插入房产信息成功！"));
        }
    }

    /**
     * 此处应该完成的是楼宇的查询功能，但是现在数据表中没有任何楼宇的数据，
     * 因此在编写的时候，需要进行插入且返回插入的数据
     * @param buildingNumber
     * @param estateCode
     * @return
     */
    @RequestMapping("/estate/selectBuilding")
    public String selectBuilding(Integer buildingNumber, String estateCode){
        List<FcBuilding> fcBuildings = estateService.selectBuilding(buildingNumber, estateCode);
        System.out.println("fcBuildings: " + fcBuildings);
        return JSONObject.toJSONString(new ReturnObject(fcBuildings));
    }

    @RequestMapping("/estate/updateBuilding")
    public String updateBuilding(FcBuilding fcBuilding){
        System.out.println("update Building");
        Integer result = estateService.updateBuilding(fcBuilding);
        if (result == 1){
            return JSONObject.toJSONString(new ReturnObject("更新楼宇成功"));
        }else {
            return JSONObject.toJSONString(new ReturnObject("更新楼宇失败"));
        }

    }
}
