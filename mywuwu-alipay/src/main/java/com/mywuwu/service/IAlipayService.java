package com.mywuwu.service;

import com.alipay.api.domain.*;
import com.mywuwu.dto.AjaxResult;

import java.util.Map;

/**
 * @Package: com.mywuwu.quartz.service
 * @Description： 支付宝支付实现接口
 * @Author: 梁乐乐
 * @Date: Created in 2018/5/20 10:52
 * @Company: clinbrain
 * @Copyright: Copyright (c) 2018
 * @Version: 0.0.1
 * @Modified By:
 */
public interface IAlipayService {

    /**
     * Description： 支付宝手机网站支付
     *
     * @param model 网站支付对象
     * @Author: 梁乐乐
     * @ Date: Created in 2018/5/20 11:27
     */
    AjaxResult alipayTradeWapPay(AlipayTradeWapPayModel model);


    /**
     * 支付宝网站支付
     *
     * @param model 订单信息
     * @return 支付表单
     */
    AjaxResult alipayTradePagePay(AlipayTradePagePayModel model);

    /**
     * Description： 支付宝退款查询
     *
     * @param fasModel 退款对象
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/20 20:18
     */
    AjaxResult alipayTradeFastpayRefundQuery(AlipayTradeFastpayRefundQueryModel fasModel);

    /**
     * @param queryModel 退款订单查询对象
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 退款订单查询
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/20 20:49
     */
    AjaxResult alipayFundTransOrderQuery(AlipayFundTransOrderQueryModel queryModel);

    /**
     * @param model 申请退款信息
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝退款接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    AjaxResult alipayTradeRefund(AlipayTradeRefundApplyModel model);

    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝交易关闭接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    AjaxResult alipayTradeClose(AlipayTradeCloseModel model);

    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝结算接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    AjaxResult alipayTradeOrderSettle(AlipayTradeOrderSettleModel model);


    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝统一收单线下交易预创建接口（这个接口需要返回二维码信息）
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    AjaxResult alipayTradePrecreate(AlipayTradePrecreateModel model);

    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝统一收单交易创建接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    AjaxResult alipayTradeCreate(AlipayTradeCreateModel model);

    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝统一收单交易支付接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    AjaxResult alipayTradePay(AlipayTradePayModel model);

    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝统一收单线下交易查询
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    AjaxResult lipayTradeQuery(AlipayTradeQueryModel model);


    /**
     * @param alipayMap   订单信息
     * @return com.mywuwu.dto.AjaxResult
     * @Description： 支付宝支付回调
     * @Author: 梁乐乐
     * @Date: Created in 2018/9/16 15:33
     */
    AjaxResult alipayNotify(Map alipayMap);


    void aliGetToken();
}
