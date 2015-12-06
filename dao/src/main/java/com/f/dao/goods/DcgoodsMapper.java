package com.f.dao.goods;

import com.f.dto.goods.Dcgoods;
import com.f.dto.goods.DcgoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DcgoodsMapper {
    int countByExample(DcgoodsExample example);

    int insert(Dcgoods record);

    int insertSelective(Dcgoods record);

    List<Dcgoods> selectByExample(DcgoodsExample example);

    Dcgoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Dcgoods record, @Param("example") DcgoodsExample example);

    int updateByExample(@Param("record") Dcgoods record, @Param("example") DcgoodsExample example);

    int updateByPrimaryKeySelective(Dcgoods record);

    int updateByPrimaryKey(Dcgoods record);
}