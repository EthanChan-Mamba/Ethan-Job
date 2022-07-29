package com.ruoyi.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * projectItemsMapper接口
 *
 * @author ruoyi
 * @date 2022-01-08
 */
@Mapper
public interface TestMapper
{
    @Update("update test set  "
            + "name = #{name}  "
            + "where id = #{id} ")
    int testUp(@Param("name") String name, @Param("id") int id);

    @Select("select * from test where id = #{id} ")
    Map testSelct(@Param("id") int id);

}
