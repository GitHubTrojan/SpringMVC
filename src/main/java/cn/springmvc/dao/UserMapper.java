package cn.springmvc.dao;

import cn.springmvc.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent on 2017/2/27.
 * Description Demo Mapper for testing.
 */
@Repository
public interface UserMapper {
    User selectByPrimaryKey(int uid);
}
