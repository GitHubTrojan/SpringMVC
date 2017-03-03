package cn.springmvc.service.impl;

import cn.springmvc.dao.UserMapper;
import cn.springmvc.model.User;
import cn.springmvc.service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Vincent on 2017/2/27.
 * Description 登录操作实现类
 */
@Service("loginService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public int countByExample(Object o) {
        return 0;
    }

    public int deleteByExample(Object o) {
        return 0;
    }

    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    public int insert(Object o) {
        return 0;
    }

    public int insertSelective(Object o) {
        return 0;
    }

    public List selectByExampleWithBLOBs(Object o) {
        return null;
    }

    public List selectByExample(Object o) {
        return null;
    }

    public Object selectFirstByExample(Object o) {
        return null;
    }

    public Object selectByPrimaryKey(Integer id) { return null; }

    public int updateByExampleSelective(@Param("record") Object o, @Param("example") Object o2) {
        return 0;
    }

    public int updateByExampleWithBLOBs(@Param("record") Object o, @Param("example") Object o2) {
        return 0;
    }

    public int updateByExample(@Param("record") Object o, @Param("example") Object o2) {
        return 0;
    }

    public int updateByPrimaryKeySelective(Object o) {
        return 0;
    }

    public int updateByPrimaryKeyWithBLOBs(Object o) {
        return 0;
    }

    public int updateByPrimaryKey(Object o) {
        return 0;
    }

    public int deleteByPrimaryKeys(String ids) {
        return 0;
    }

    public void initMapper() {}

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public User selectByPrimaryKey(int uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Transactional(readOnly = false)
    public int updateByPrimaryKey(User user) {
        int result = userMapper.updateByPrimaryKey(user);
        System.out.println("service ：\t" + userMapper.selectByPrimaryKey(user.getUid()).getUname());
        if (result == 1)
        throw new IllegalArgumentException("DIY error.");
        return result;
    }
}
