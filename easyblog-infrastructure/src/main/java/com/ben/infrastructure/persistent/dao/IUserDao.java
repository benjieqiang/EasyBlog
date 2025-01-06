package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.dao.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName: IUserDao
 * @Description: 用户Dao
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/3 3:11 PM
 * @Version: v1.0
 */
@Mapper
public interface IUserDao {
    void insert(User user);

    int updatePasswordByUsername(User user);

    User queryByUsername(String username);
}

