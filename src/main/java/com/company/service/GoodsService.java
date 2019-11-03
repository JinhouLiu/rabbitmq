package com.company.service;

import com.company.annotation.IsTryAgain;
import com.company.entity.SeckillGood;
import com.company.exception.ApiResultEnum;
import com.company.exception.TryAgainException;
import com.company.mapper.GoodsMapper;
import com.company.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Transactional
    @IsTryAgain
    public Result reduceStock(Long id) {

        doReduceStock(id);
        return Result.ok();

    }

    
    public  void  doReduceStock(Long id){

        SeckillGood seckillGoods= goodsMapper.getById(id);
        if(goodsMapper.reduceStockByVersion(seckillGoods)<=0){

            throw new TryAgainException(ApiResultEnum.ERROR_TRY_AGAIN);
        }

    }





}
