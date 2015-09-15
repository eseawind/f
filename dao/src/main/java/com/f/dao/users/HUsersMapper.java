package com.f.dao.users;

import com.f.dto.users.HUsers;
import com.f.dto.users.HUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HUsersMapper {
    int countByExample(HUsersExample example);

    int insert(HUsers record);

    int insertSelective(HUsers record);

    List<HUsers> selectByExample(HUsersExample example);

    HUsers selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HUsers record, @Param("example") HUsersExample example);

    int updateByExample(@Param("record") HUsers record, @Param("example") HUsersExample example);

    int updateByPrimaryKeySelective(HUsers record);

    int updateByPrimaryKey(HUsers record);
}