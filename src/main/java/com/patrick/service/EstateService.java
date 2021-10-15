package com.patrick.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.patrick.bean.*;
import com.patrick.mapper.FcBuildingMapper;
import com.patrick.mapper.FcEstateMapper;
import com.patrick.mapper.FcUnitMapper;
import com.patrick.mapper.TblCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileVisitor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EstateService {

    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;

    @Autowired
    private FcBuildingMapper fcBuildingMapper;

    @Autowired
    private FcUnitMapper fcUnitMapper;

    public List<TblCompany> selectConmpany(){
        List<TblCompany> companies = tblCompanyMapper.selectCompanyName();
        return companies;
    }

    /**
     * 在插入数据之前，最好对当前信息做判断，判断他的编码是否存在，如果存在则不允许插入
     * @param fcEstate
     * @return
     */
    public Integer insertEstate(FcEstate fcEstate){
        //定义查询包装类
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate findResult = fcEstateMapper.selectOne(queryWrapper);
        int result = 0;
        if(findResult != null){
//            System.out.println("已拒绝！ 需要插入的数据为重复值！ ");
            return result;
        }else{
            result = fcEstateMapper.insert(fcEstate);
//            System.out.println("已接受！ ");
            return result;
        }
    }

    /**
     * 先插入数据，再查询数据
     * @return
     */
    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode){
        List<FcBuilding> fcBuildings = new ArrayList<>();
        for(int i = 0; i < buildingNumber; i++){
            FcBuilding fcBuilding = new FcBuilding();
            fcBuilding.setBuildingCode("B" + ( i + 1 ));
            fcBuilding.setBuildingName("第" + ( i + 1 ) + "号楼");
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
    }

    /**
     * 完成楼宇的更新功能
     * @param fcBuilding
     * @return
     */
    public Integer updateBuilding(FcBuilding fcBuilding){
        int result = fcBuildingMapper.updateById(fcBuilding);
        return result;
    }

    public List<FcUnit> selectUnit(UnitMessage unitMessage) {
        //定义返回的集合
        List<FcUnit> fcUnits = new ArrayList<>();
        //操作插入数据
        for (int i = 0; i < unitMessage.getUnitCount(); i++){
            FcUnit fcUnit = new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode("U"+(i+1));
            fcUnit.setUnitName("第"+(i+1)+"单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }
}
