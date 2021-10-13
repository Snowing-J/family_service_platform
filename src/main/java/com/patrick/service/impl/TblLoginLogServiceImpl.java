package com.patrick.service.impl;

import com.patrick.bean.TblLoginLog;
import com.patrick.mapper.TblLoginLogMapper;
import com.patrick.service.base.TblLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author lian
 * @since 2021-10-06
 */
@Service
public class TblLoginLogServiceImpl extends ServiceImpl<TblLoginLogMapper, TblLoginLog> implements TblLoginLogService {

}
