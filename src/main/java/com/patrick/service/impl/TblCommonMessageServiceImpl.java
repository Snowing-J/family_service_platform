package com.patrick.service.impl;

import com.patrick.bean.TblCommonMessage;
import com.patrick.mapper.TblCommonMessageMapper;
import com.patrick.service.base.TblCommonMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 常用短信 服务实现类
 * </p>
 *
 * @author lian
 * @since 2021-10-06
 */
@Service
public class TblCommonMessageServiceImpl extends ServiceImpl<TblCommonMessageMapper, TblCommonMessage> implements TblCommonMessageService {

}
