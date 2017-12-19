package Cy.WeChatSpringboot.dao;

import Cy.WeChatSpringboot.pojo.users;

public interface usersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(users record);

    int insertSelective(users record);

    users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(users record);

    int updateByPrimaryKey(users record);
}