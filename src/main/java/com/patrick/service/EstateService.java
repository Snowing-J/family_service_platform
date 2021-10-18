package com.patrick.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.patrick.bean.*;
import com.patrick.mapper.*;
import com.patrick.service.vo.CellMessage;
import com.patrick.service.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private FcCellMapper fcCellMapper;

    public List<TblCompany> selectConmpany() {
        List<TblCompany> companies = tblCompanyMapper.selectCompanyName();
        return companies;
    }

    /**
     * 在插入数据之前，最好对当前信息做判断，判断他的编码是否存在，如果存在则不允许插入
     *
     * @param fcEstate
     * @return
     */
    public Integer insertEstate(FcEstate fcEstate) {
        //定义查询包装类
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate findResult = fcEstateMapper.selectOne(queryWrapper);
        int result = 0;
        if (findResult != null) {
//            System.out.println("已拒绝！ 需要插入的数据为重复值！ ");
            return result;
        } else {
            result = fcEstateMapper.insert(fcEstate);
//            System.out.println("已接受！ ");
            return result;
        }
    }

    public List<FcBuilding> selectEstate(String fcEstate){
        Map<String, Object> buildingMap = new HashMap<>();
        buildingMap.put("estate_code", fcEstate);
        List<FcBuilding> fcBuildings = fcBuildingMapper.selectByMap(buildingMap);
        return fcBuildings;
    }


    public Integer insertBuilding(Integer buildingNumber, String estateCode) {
        Integer result = 0;
        for (int i = 0; i < buildingNumber; i++) {
            FcBuilding fcBuilding = new FcBuilding();
            fcBuilding.setBuildingCode("B" + (i + 1));
            fcBuilding.setBuildingName("第" + (i + 1) + "号楼");
            fcBuilding.setEstateCode(estateCode);
            result = fcBuildingMapper.insert(fcBuilding);
            // 只要有一个插入失败立即返回失败
            // TODO: 做事件控制，避免在批量插入的时候出现部分成功部分失败问题
            if (result == 0){
                return result;
            }
        }
        return result;
    }

    public List<FcUnit> selectBuilding(String buildingCode) {
        // 4、通过map封装进行条件查询---- Mybatis-plus 使用方法
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("e_name","zhangsan");
//        map.put("sal",1000.0);
//        List<Emp> list = empDao.selectByMap(map);
//        for (Emp emp : list) {
//            System.out.println(emp);
//        }

        Map<String, Object> unitMap = new HashMap<>();
        unitMap.put("building_code", buildingCode);
        System.out.println("---unitMap: " + unitMap);
        List<FcUnit> fcUnits = fcUnitMapper.selectByMap(unitMap);
        System.out.println("---fcUnits:" + fcUnits);
        return fcUnits;
    }

    /**
     * 完成楼宇的更新功能
     *
     * @param fcBuilding
     * @return
     */
    public Integer updateBuilding(FcBuilding fcBuilding) {
        int result = fcBuildingMapper.updateById(fcBuilding);
        return result;
    }

    public Integer insertUnit(UnitMessage unitMessage) {
        List<FcUnit>
        //操作插入数据
        for (int i = 0; i < unitMessage.getUnitCount(); i++) {
            FcUnit fcUnit = new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode("U" + (i + 1));
            fcUnit.setUnitName("第" + (i + 1) + "单元");
            result = fcUnitMapper.insert(fcUnit);
            if(result == 0){
                return result;
            }
        }
        return result;
    }

    public List<FcCell> selectUnit(UnitMessage unitMessage) {
        //定义返回的集合
        Map<String, Object> cellMap = new HashMap<>();
        cellMap.put("building_code", unitMessage.getBuildingCode());
        cellMap.put("unit_code", unitMessage.getUnitCode());
        List<FcCell> fcCells = fcCellMapper.selectByMap(cellMap);
        return fcCells;
    }

    public Integer updateUnit(FcUnit fcUnit) {
        int result = fcUnitMapper.updateById(fcUnit);
        return result;
    }

    public List<FcCell> insertCell(CellMessage[] cellMessages) {
        //定义返回的集合
        List<FcCell> list = new ArrayList<>();
        //操作插入数据
        for (CellMessage cellMessage : cellMessages) {
            for (int i = cellMessage.getStartFloor(); i <= cellMessage.getStopFloor(); i++) {
                for (int j = cellMessage.getStartCellId(); j <= cellMessage.getStopCellId(); j++) {
                    FcCell fcCell = new FcCell();
                    fcCell.setUnitCode(cellMessage.getUnitCode());
                    fcCell.setCellName(i + "0" + j);
                    fcCell.setCellCode("C" + i + "0" + j);
                    fcCell.setFloorNumber(i);
                    fcCellMapper.insert(fcCell);
                    list.add(fcCell);
                }
            }
        }
        System.out.println("---------------------------------------------------------");
        System.out.println(list);
        return list;
    }
}