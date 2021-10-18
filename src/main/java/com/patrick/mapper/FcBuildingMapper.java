package com.patrick.mapper;

import com.patrick.bean.FcBuilding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 楼宇信息表 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2021-10-06
 */
@Component
public interface FcBuildingMapper extends BaseMapper<FcBuilding> {
    public List<FcBuilding> selectByEstateCode(@Param("estateCode") String estateCode);
}
