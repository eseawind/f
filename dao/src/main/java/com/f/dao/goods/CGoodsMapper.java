package com.f.dao.goods;

import com.f.dto.goods.CGoods;
import com.f.dto.goods.CGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CGoodsMapper {
    int countByExample(CGoodsExample example);

    int insert(CGoods record);

    int insertSelective(CGoods record);

    List<CGoods> selectByExample(CGoodsExample example);

    CGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CGoods record, @Param("example") CGoodsExample example);

    int updateByExample(@Param("record") CGoods record, @Param("example") CGoodsExample example);

    int updateByPrimaryKeySelective(CGoods record);

    int updateByPrimaryKey(CGoods record);
}