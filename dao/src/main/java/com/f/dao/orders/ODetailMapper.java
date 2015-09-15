package com.f.dao.orders;

import com.f.dto.orders.ODetail;
import com.f.dto.orders.ODetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ODetailMapper {
    int countByExample(ODetailExample example);

    int insert(ODetail record);

    int insertSelective(ODetail record);

    List<ODetail> selectByExample(ODetailExample example);

    ODetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ODetail record, @Param("example") ODetailExample example);

    int updateByExample(@Param("record") ODetail record, @Param("example") ODetailExample example);

    int updateByPrimaryKeySelective(ODetail record);

    int updateByPrimaryKey(ODetail record);
}