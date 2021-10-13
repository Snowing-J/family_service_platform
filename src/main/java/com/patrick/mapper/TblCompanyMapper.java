package com.patrick.mapper;

import com.patrick.bean.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 企业档案 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2021-10-06
 */
@Component
public interface TblCompanyMapper extends BaseMapper<TblCompany> {

    public List<TblCompany> selectCompanyName();
}
