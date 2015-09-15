package com.f.dao.orders;

import com.f.dto.orders.OPayType;
import com.f.dto.orders.OPayTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OPayTypeMapper {
    int countByExample(OPayTypeExample example);

    int insert(OPayType record);

    int insertSelective(OPayType record);

    List<OPayType> selectByExample(OPayTypeExample example);

    OPayType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OPayType record, @Param("example") OPayTypeExample example);

    int updateByExample(@Param("record") OPayType record, @Param("example") OPayTypeExample example);

    int updateByPrimaryKeySelective(OPayType record);

    int updateByPrimaryKey(OPayType record);
}