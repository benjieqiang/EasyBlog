package com.ben.infrastructure.persistent.dao;

import com.ben.infrastructure.persistent.dao.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName: IUserDao
 * @Description: 用户角色Dao
 * @Author: benjieqiang
 * @LastChangeDate: 2024/12/3 3:11 PM
 * @Version: v1.0
 */
@Mapper
public interface IUserRoleDao {
    void insert(UserRole userRoleReq);

    int updateRoleByUsername(UserRole userRoleReq);

    List<UserRole> queryByUsername(String username);
}

