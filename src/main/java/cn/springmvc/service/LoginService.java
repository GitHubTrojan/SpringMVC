package cn.springmvc.service;

import cn.springmvc.base.BaseService;
import cn.springmvc.model.User;

/**
 * Created by Vincent on 2017/2/27.
 * Version 1.0.0
 * Description This is an Interface defined to handle with the login actions.
 */
public interface LoginService extends BaseService {
    User selectByPrimaryKey(int uid);
}
