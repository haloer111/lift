package com.gexiao.lift.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gexiao.lift.dao.AuthMapper;
import com.gexiao.lift.entity.Authority;
import com.gexiao.lift.service.IAuthService;
import org.springframework.stereotype.Service;

/**
 * @Auther: gexiao
 * @Date: 2019/4/11 14:28
 * @Description:
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Authority> implements IAuthService {
}
