package com.company.mapper;

import com.company.entity.SeckillGood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 *
 */
@Mapper
public interface GoodsMapper {

    //stock_count > 0 和 版本号实现乐观锁 防止超卖
    @Update("update goods_seckill set stock_count = stock_count - 1, version= version + 1 where good_id = #{goodId} and stock_count > 0 and version = #{version}")
    public int reduceStockByVersion(SeckillGood seckillGoods);


    @Select("select * from goods_seckill where id = #{id}")
    public SeckillGood getById(@Param("id") long id);



}
