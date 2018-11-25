package com.mywuwu.dao;

import com.mywuwu.common.mapper.MyMapper;
import com.mywuwu.entity.WuwuPay;

public interface WuwuPayMapper extends MyMapper<WuwuPay> {

    /**
     * @param code 支付订单号
     * @return 支付记录信息
     * @Description： 根据订单号查询
     * @Author: 梁乐乐
     * @Date: Created in 2018/9/16 17:16
     */
    WuwuPay selectByPayCode(String code);
}