package com.f.dao.goods;

import com.f.dto.goods.GStock;
import com.f.dto.goods.GStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GStockMapper {
    int countByExample(GStockExample example);

    int insert(GStock record);

    int insertSelective(GStock record);

    List<GStock> selectByExample(GStockExample example);

    GStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GStock record, @Param("example") GStockExample example);

    int updateByExample(@Param("record") GStock record, @Param("example") GStockExample example);

    int updateByPrimaryKeySelective(GStock record);

    int updateByPrimaryKey(GStock record);
}