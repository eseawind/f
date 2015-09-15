package com.f.dao.goods;

import com.f.dto.goods.GDetail;
import com.f.dto.goods.GDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GDetailMapper {
    int countByExample(GDetailExample example);

    int insert(GDetail record);

    int insertSelective(GDetail record);

    List<GDetail> selectByExample(GDetailExample example);

    GDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GDetail record, @Param("example") GDetailExample example);

    int updateByExample(@Param("record") GDetail record, @Param("example") GDetailExample example);

    int updateByPrimaryKeySelective(GDetail record);

    int updateByPrimaryKey(GDetail record);
}