package com.patrick.service.impl;

import com.patrick.bean.TblEmployeeContact;
import com.patrick.mapper.TblEmployeeContactMapper;
import com.patrick.service.base.TblEmployeeContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工通讯录 服务实现类
 * </p>
 *
 * @author lian
 * @since 2021-10-06
 */
@Service
public class TblEmployeeContactServiceImpl extends ServiceImpl<TblEmployeeContactMapper, TblEmployeeContact> implements TblEmployeeContactService {

}
