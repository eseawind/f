package com.f.dao.goods;

import com.f.dto.goods.Dynpage;
import com.f.dto.goods.DynpageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DynpageMapper {
    int countByExample(DynpageExample example);

    int insert(Dynpage record);

    int insertSelective(Dynpage record);

    List<Dynpage> selectByExample(DynpageExample example);

    Dynpage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Dynpage record, @Param("example") DynpageExample example);

    int updateByExample(@Param("record") Dynpage record, @Param("example") DynpageExample example);

    int updateByPrimaryKeySelective(Dynpage record);

    int updateByPrimaryKey(Dynpage record);
}