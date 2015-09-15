package com.f.dao.orders;

import com.f.dto.orders.OAddress;
import com.f.dto.orders.OAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OAddressMapper {
    int countByExample(OAddressExample example);

    int insert(OAddress record);

    int insertSelective(OAddress record);

    List<OAddress> selectByExample(OAddressExample example);

    OAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OAddress record, @Param("example") OAddressExample example);

    int updateByExample(@Param("record") OAddress record, @Param("example") OAddressExample example);

    int updateByPrimaryKeySelective(OAddress record);

    int updateByPrimaryKey(OAddress record);
}