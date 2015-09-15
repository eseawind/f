package com.f.dao.users;

import com.f.dto.users.UAddress;
import com.f.dto.users.UAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UAddressMapper {
    int countByExample(UAddressExample example);

    int insert(UAddress record);

    int insertSelective(UAddress record);

    List<UAddress> selectByExample(UAddressExample example);

    UAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UAddress record, @Param("example") UAddressExample example);

    int updateByExample(@Param("record") UAddress record, @Param("example") UAddressExample example);

    int updateByPrimaryKeySelective(UAddress record);

    int updateByPrimaryKey(UAddress record);
}