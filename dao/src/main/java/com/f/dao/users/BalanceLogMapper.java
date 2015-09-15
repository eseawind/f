package com.f.dao.users;

import com.f.dto.users.BalanceLog;
import com.f.dto.users.BalanceLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BalanceLogMapper {
    int countByExample(BalanceLogExample example);

    int insert(BalanceLog record);

    int insertSelective(BalanceLog record);

    List<BalanceLog> selectByExample(BalanceLogExample example);

    BalanceLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BalanceLog record, @Param("example") BalanceLogExample example);

    int updateByExample(@Param("record") BalanceLog record, @Param("example") BalanceLogExample example);

    int updateByPrimaryKeySelective(BalanceLog record);

    int updateByPrimaryKey(BalanceLog record);
}