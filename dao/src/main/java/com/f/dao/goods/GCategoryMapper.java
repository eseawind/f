package com.f.dao.goods;

import com.f.dto.goods.GCategory;
import com.f.dto.goods.GCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GCategoryMapper {
    int countByExample(GCategoryExample example);

    int insert(GCategory record);

    int insertSelective(GCategory record);

    List<GCategory> selectByExample(GCategoryExample example);

    GCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GCategory record, @Param("example") GCategoryExample example);

    int updateByExample(@Param("record") GCategory record, @Param("example") GCategoryExample example);

    int updateByPrimaryKeySelective(GCategory record);

    int updateByPrimaryKey(GCategory record);
}