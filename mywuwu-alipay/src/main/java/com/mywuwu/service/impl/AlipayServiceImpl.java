package com.mywuwu.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.mywuwu.common.config.AlipayConfig1;
import com.mywuwu.common.config.AlipayConfig;
import com.mywuwu.dao.WuwuPayMapper;
import com.mywuwu.dto.AjaxResult;
import com.mywuwu.entity.WuwuPay;
import com.mywuwu.service.IAlipayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @Package: com.mywuwu.quartz.service.impl
 * @Description： 支付宝支付实现类
 * @Author: 梁乐乐
 * @Date: Created in 2018/5/20 10:52
 * @Company: clinbrain
 * @Copyright: Copyright (c) 2018
 * @Version: 0.0.1
 * @Modified By:
 */
@Service
public class AlipayServiceImpl implements IAlipayService {

    /**
     * 日志记录
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(AlipayServiceImpl.class);

    /**
     * 支付记录
     */
    @Autowired
    private WuwuPayMapper payMapper;

//    /**
//     * 播放号
//     */
//    @Autowired
//    private WuwuCodeMapper codeMapper;


    /**
     * 支付宝手机网站支付
     *
     * @param model 订单信息
     * @return 支付表单
     */
    @Override
    public AjaxResult alipayTradeWapPay(AlipayTradeWapPayModel model) {
        //返回对象
        AjaxResult result = new AjaxResult();
        try {
            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            //调用RSA签名方式
            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);


            //阿里支付请求类
            AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

            alipay_request.setBizModel(model);
            // 设置异步通知地址
            alipay_request.setNotifyUrl(AlipayConfig.notify_url);
            // 设置同步地址
            alipay_request.setReturnUrl(AlipayConfig.return_url);

            // form表单生产   // 调用SDK生成表单
            AlipayTradeWapPayResponse response = client.pageExecute(alipay_request);
            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("支付成功，订单号为：" + response.getOutTradeNo());
                System.out.println("调用成功,支付宝流水号：" + response.getTradeNo());
            } else {
                System.out.println("调用失败");
                result.setCode("-10000");
//                result.setMessage("退款失败，申请退款订单号：" + response.getOutTradeNo());
                //10000:	接口调用成功，调用结果请参考具体的API文档所对应的业务返回参数
                //20000: isp.unknow-error 服务暂不可用（业务系统不可用）  aop.unknow-error 服务暂不可用（网关自身的未知错误）
                //20001 授权权限不足
                //40001 缺少必选参数
                //40002 非法的参数
                //40004 业务处理失败
                //40006 权限不足
                result.setMessage(response.getSubMsg());
            }
           /* {"code":"40004","msg":"Business Failed","subCode":"ACQ.SELLER_BALANCE_NOT_ENOUGH","subMsg":"卖家余额不足",
                    "body":"{\"alipay_trade_refund_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.SELLER_BALANCE_NOT_ENOUGH\",\"sub_msg\":\"卖家余额不足\",\"" +
                    "out_trade_no\":\"TEST2018-05-20 19:37:21\",\"refund_fee\":\"0.00\",\"send_back_fee\":\"0.00\"}*/
            result.setData(response.getBody());
            return result;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            LOGGER.error("支付出错。", e);
            result.setCode("1");
            result.setMessage("支付败,系统出错。");
            return result;
        }
    }


    /**
     * 支付宝网站支付
     *
     * @param model 订单信息
     * @return 支付表单
     */
    @Override
    public AjaxResult alipayTradePagePay(AlipayTradePagePayModel model) {
        //返回对象
        AjaxResult result = new AjaxResult();

        boolean upType = false;

        try {

            //根据订单号查询是否有订单信息
            WuwuPay wuwuPay = payMapper.selectByPayCode(model.getOutTradeNo());

            if (wuwuPay == null) {
                //支付记录
                wuwuPay = new WuwuPay();
                upType = true;
            }



            //自己支付订单号
            wuwuPay.setMypayCode(model.getOutTradeNo());

            //1：支付宝 2：微信 3：银联 0：其他
            wuwuPay.setPayType(1);

            /**
             * 0：待支付
             */
            wuwuPay.setPayStatus(0);

            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            //调用RSA签名方式
            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);


            //阿里支付请求类
            AlipayTradePagePayRequest alipay_request = new AlipayTradePagePayRequest();

            alipay_request.setBizModel(model);
            // 设置异步通知地址
            alipay_request.setNotifyUrl(AlipayConfig.notify_url);
            // 设置同步地址
            alipay_request.setReturnUrl(AlipayConfig.return_url);

            // form表单生产   // 调用SDK生成表单
            AlipayTradePagePayResponse response = client.pageExecute(alipay_request);
            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("调用成功，订单号为：" + response.getOutTradeNo());
                wuwuPay.setPayStatus(1);
                System.out.println("调用成功,支付宝流水号：" + response.getTradeNo());
            } else {
                System.out.println("调用失败");
                result.setCode("-10000 ");
                result.setMessage(response.getSubMsg());
                wuwuPay.setPayStatus(0);
            }

            result.setData(response.getBody());
            //创建支付记录
            wuwuPay.setPayMoney(model.getTotalAmount());
            wuwuPay.setRemark(model.getSubject());
            wuwuPay.setRedatetime(new Date());
            wuwuPay.setUserId("lianglele");
            if (upType) {
                payMapper.insert(wuwuPay);
            } else {
                payMapper.updateByPrimaryKey(wuwuPay);
            }

            return result;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            LOGGER.error("支付出错。", e);
            result.setCode("1");
            result.setMessage("支付败,系统出错。");
            return result;
        }
    }


    /**
     * @param fasModel 退款对象
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝退款
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/20 20:42
     */
    @Override
    public AjaxResult alipayTradeFastpayRefundQuery(AlipayTradeFastpayRefundQueryModel fasModel) {

        //返回对象
        AjaxResult result = new AjaxResult();

        try {
            //验证前面
            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

            request.setBizModel(fasModel);

            AlipayTradeFastpayRefundQueryResponse response = client.execute(request);

            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("退款查询成功");
            } else {
                result.setCode(response.getErrorCode());
                result.setMessage("退款查询失败。本次查询退款订单：" + response.getOutTradeNo());
            }
            result.setData(response);
            return result;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            result.setCode("1");
            result.setMessage("退款失败,系统出错。");
            LOGGER.error("退款出错。", e);
            return result;
        }

    }

    /**
     * @param queryModel 退款订单查询对象
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 退款订单查询
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/20 20:49
     */
    @Override
    public AjaxResult alipayFundTransOrderQuery(AlipayFundTransOrderQueryModel queryModel) {
        //返回对象
        AjaxResult result = new AjaxResult();
        try {
            //验证前面
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
            request.setBizModel(queryModel);
            AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
            /**
             * 转账单据状态。
             SUCCESS：成功（配合"单笔转账到银行账户接口"产品使用时, 同一笔单据多次查询有可能从成功变成退票状态）；
             FAIL：失败（具体失败原因请参见error_code以及fail_reason返回值）；
             INIT：等待处理；
             DEALING：处理中；
             REFUND：退票（仅配合"单笔转账到银行账户接口"产品使用时会涉及, 具体退票原因请参见fail_reason返回值）；
             UNKNOWN：状态未知。
             */
            if (response.isSuccess()) {
                if ("SUCCESS".equalsIgnoreCase(response.getStatus())) {
                    result.setCode("0");
                    result.setMessage("退款成功，预计到账时间：" + response.getArrivalTimeEnd() + ";具体到账时间已银行为准。");

                } else if ("FAIL".equalsIgnoreCase(response.getStatus())) {
                    result.setCode("-10000");
                    result.setMessage("退款失败，失败原因：" + response.getFailReason() + ";错误码：[" + response.getErrorCode() + "]");
                } else if ("INIT".equalsIgnoreCase(response.getStatus())) {
                    result.setCode("1");
                    result.setMessage("退款等待处理，请赖心等待。");
                } else if ("DEALING".equalsIgnoreCase(response.getStatus())) {
                    result.setCode("2");
                    result.setMessage("退款处理中，请赖心等待。");
                } else if ("REFUND".equalsIgnoreCase(response.getStatus())) {
                    result.setCode("3");
                    result.setMessage("订单被退票，原因：" + response.getFailReason());
                } else {
                    result.setCode("4");
                    result.setMessage("未知错误，请赖心等待系统处理。");
                }


            } else {
                result.setCode("-10001");
                result.setMessage(response.getSubMsg());
            }
            result.setData(response);
            return result;
        } catch (Exception e) {
            LOGGER.error("查询退款订单出错。", e);
            result.setCode("-10001");
            result.setMessage("系统调用错误，请联系管理员.");
            return result;
        }
    }

    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝退款接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    @Override
    public AjaxResult alipayTradeRefund(AlipayTradeRefundApplyModel model) {
        //返回对象
        AjaxResult result = new AjaxResult();

        try {
            //验证前面
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            request.setBizModel(model);

            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("退款成功，退款金额：" + response.getRefundFee());
                System.out.println("调用成功" + response.getRefundFee());
            } else {
                System.out.println("调用失败");
                result.setCode("-10000");
//                result.setMessage("退款失败，申请退款订单号：" + response.getOutTradeNo());
                //10000:	接口调用成功，调用结果请参考具体的API文档所对应的业务返回参数
                //20000: isp.unknow-error 服务暂不可用（业务系统不可用）  aop.unknow-error 服务暂不可用（网关自身的未知错误）
                //20001 授权权限不足
                //40001 缺少必选参数
                //40002 非法的参数
                //40004 业务处理失败
                //40006 权限不足
                result.setMessage(response.getSubMsg());
            }
           /* {"code":"40004","msg":"Business Failed","subCode":"ACQ.SELLER_BALANCE_NOT_ENOUGH","subMsg":"卖家余额不足",
                    "body":"{\"alipay_trade_refund_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.SELLER_BALANCE_NOT_ENOUGH\",\"sub_msg\":\"卖家余额不足\",\"" +
                    "out_trade_no\":\"TEST2018-05-20 19:37:21\",\"refund_fee\":\"0.00\",\"send_back_fee\":\"0.00\"}*/
            result.setData(response);
            return result;
        } catch (AlipayApiException e) {
            LOGGER.error("申请订单退款出错。", e);
            result.setCode("-10001");
            result.setMessage("系统调用错误，请联系管理员.");
            return result;
        }

    }


    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝交易关闭接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    @Override
    public AjaxResult alipayTradeClose(AlipayTradeCloseModel model) {
        //返回对象
        AjaxResult result = new AjaxResult();

        try {
            //验证前面
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig1.URL, AlipayConfig1.APPID, AlipayConfig1.RSA_PRIVATE_KEY, AlipayConfig1.FORMAT, AlipayConfig1.CHARSET, AlipayConfig1.ALIPAY_PUBLIC_KEY, AlipayConfig1.SIGNTYPE);

            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
            request.setBizModel(model);

            AlipayTradeCloseResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("撤销成功，支付宝交易号：" + response.getTradeNo());
                System.out.println("调用成功" + response.getOutTradeNo());
            } else {
                System.out.println("调用失败");
                result.setCode("-10000");
//                result.setMessage("退款失败，申请退款订单号：" + response.getOutTradeNo());
                //10000:	接口调用成功，调用结果请参考具体的API文档所对应的业务返回参数
                //20000: isp.unknow-error 服务暂不可用（业务系统不可用）  aop.unknow-error 服务暂不可用（网关自身的未知错误）
                //20001 授权权限不足
                //40001 缺少必选参数
                //40002 非法的参数
                //40004 业务处理失败
                //40006 权限不足
                result.setMessage(response.getSubMsg());
            }
           /* {"code":"40004","msg":"Business Failed","subCode":"ACQ.SELLER_BALANCE_NOT_ENOUGH","subMsg":"卖家余额不足",
                    "body":"{\"alipay_trade_refund_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.SELLER_BALANCE_NOT_ENOUGH\",\"sub_msg\":\"卖家余额不足\",\"" +
                    "out_trade_no\":\"TEST2018-05-20 19:37:21\",\"refund_fee\":\"0.00\",\"send_back_fee\":\"0.00\"}*/
            result.setData(response);
            return result;
        } catch (AlipayApiException e) {
            LOGGER.error("申请订单退款出错。", e);
            result.setCode("-10001");
            result.setMessage("系统调用错误，请联系管理员.");
            return result;
        }

    }


    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝结算接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    @Override
    public AjaxResult alipayTradeOrderSettle(AlipayTradeOrderSettleModel model) {
        //返回对象
        AjaxResult result = new AjaxResult();

        try {
            //验证前面
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
            request.setBizModel(model);

            AlipayTradeOrderSettleResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("撤销成功，支付宝交易号：" + response.getTradeNo());
                System.out.println("调用成功" + response.getTradeNo());
            } else {
                System.out.println("调用失败");
                result.setCode("-10000");
//                result.setMessage("退款失败，申请退款订单号：" + response.getOutTradeNo());
                //10000:	接口调用成功，调用结果请参考具体的API文档所对应的业务返回参数
                //20000: isp.unknow-error 服务暂不可用（业务系统不可用）  aop.unknow-error 服务暂不可用（网关自身的未知错误）
                //20001 授权权限不足
                //40001 缺少必选参数
                //40002 非法的参数
                //40004 业务处理失败
                //40006 权限不足
                result.setMessage(response.getSubMsg());
            }
           /* {"code":"40004","msg":"Business Failed","subCode":"ACQ.SELLER_BALANCE_NOT_ENOUGH","subMsg":"卖家余额不足",
                    "body":"{\"alipay_trade_refund_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.SELLER_BALANCE_NOT_ENOUGH\",\"sub_msg\":\"卖家余额不足\",\"" +
                    "out_trade_no\":\"TEST2018-05-20 19:37:21\",\"refund_fee\":\"0.00\",\"send_back_fee\":\"0.00\"}*/
            result.setData(response);
            return result;
        } catch (AlipayApiException e) {
            LOGGER.error("申请订单退款出错。", e);
            result.setCode("-10001");
            result.setMessage("系统调用错误，请联系管理员.");
            return result;
        }

    }

    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝统一收单线下交易预创建接口（这个接口需要返回二维码信息）
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    @Override
    public AjaxResult alipayTradePrecreate(AlipayTradePrecreateModel model) {
        //返回对象
        AjaxResult result = new AjaxResult();

        try {
            //验证前面
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            request.setBizModel(model);

            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("撤销成功，支付宝交易号：" + response.getOutTradeNo());
                System.out.println("调用成功" + response.getOutTradeNo());
            } else {
                System.out.println("调用失败");
                result.setCode("-10000");
//                result.setMessage("退款失败，申请退款订单号：" + response.getOutTradeNo());
                //10000:	接口调用成功，调用结果请参考具体的API文档所对应的业务返回参数
                //20000: isp.unknow-error 服务暂不可用（业务系统不可用）  aop.unknow-error 服务暂不可用（网关自身的未知错误）
                //20001 授权权限不足
                //40001 缺少必选参数
                //40002 非法的参数
                //40004 业务处理失败
                //40006 权限不足
                result.setMessage(response.getSubMsg());
            }
           /* {"code":"40004","msg":"Business Failed","subCode":"ACQ.SELLER_BALANCE_NOT_ENOUGH","subMsg":"卖家余额不足",
                    "body":"{\"alipay_trade_refund_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.SELLER_BALANCE_NOT_ENOUGH\",\"sub_msg\":\"卖家余额不足\",\"" +
                    "out_trade_no\":\"TEST2018-05-20 19:37:21\",\"refund_fee\":\"0.00\",\"send_back_fee\":\"0.00\"}*/
            result.setData(response);
            return result;
        } catch (AlipayApiException e) {
            LOGGER.error("申请订单退款出错。", e);
            result.setCode("-10001");
            result.setMessage("系统调用错误，请联系管理员.");
            return result;
        }

    }


    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝统一收单交易创建接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    @Override
    public AjaxResult alipayTradeCreate(AlipayTradeCreateModel model) {
        //返回对象
        AjaxResult result = new AjaxResult();

        try {
            //验证前面
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
            request.setBizModel(model);

            AlipayTradeCreateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("撤销成功，支付宝交易号：" + response.getOutTradeNo());
            } else {
                result.setCode("-10000");
                //10000:	接口调用成功，调用结果请参考具体的API文档所对应的业务返回参数
                //20000: isp.unknow-error 服务暂不可用（业务系统不可用）  aop.unknow-error 服务暂不可用（网关自身的未知错误）
                //20001 授权权限不足
                //40001 缺少必选参数
                //40002 非法的参数
                //40004 业务处理失败
                //40006 权限不足
                result.setMessage(response.getSubMsg());

            }
           /* {"code":"40004","msg":"Business Failed","subCode":"ACQ.SELLER_BALANCE_NOT_ENOUGH","subMsg":"卖家余额不足",
                    "body":"{\"alipay_trade_refund_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.SELLER_BALANCE_NOT_ENOUGH\",\"sub_msg\":\"卖家余额不足\",\"" +
                    "out_trade_no\":\"TEST2018-05-20 19:37:21\",\"refund_fee\":\"0.00\",\"send_back_fee\":\"0.00\"}*/
            result.setData(response);
            return result;
        } catch (AlipayApiException e) {
            LOGGER.error("申请订单退款出错。", e);
            result.setCode("-10001");
            result.setMessage("系统调用错误，请联系管理员.");
            return result;
        }

    }


    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝统一收单交易支付接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    @Override
    public AjaxResult alipayTradePay(AlipayTradePayModel model) {
        //返回对象
        AjaxResult result = new AjaxResult();

        try {
            //验证前面
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            AlipayTradePayRequest request = new AlipayTradePayRequest();
            request.setBizModel(model);

            AlipayTradePayResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("撤销成功，支付宝交易号：" + response.getOutTradeNo());
            } else {
                result.setCode("-10000");
//                result.setMessage("退款失败，申请退款订单号：" + response.getOutTradeNo());
                //10000:	接口调用成功，调用结果请参考具体的API文档所对应的业务返回参数
                //20000: isp.unknow-error 服务暂不可用（业务系统不可用）  aop.unknow-error 服务暂不可用（网关自身的未知错误）
                //20001 授权权限不足
                //40001 缺少必选参数
                //40002 非法的参数
                //40004 业务处理失败
                //40006 权限不足
                result.setMessage(response.getSubMsg());
            }
           /* {"code":"40004","msg":"Business Failed","subCode":"ACQ.SELLER_BALANCE_NOT_ENOUGH","subMsg":"卖家余额不足",
                    "body":"{\"alipay_trade_refund_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.SELLER_BALANCE_NOT_ENOUGH\",\"sub_msg\":\"卖家余额不足\",\"" +
                    "out_trade_no\":\"TEST2018-05-20 19:37:21\",\"refund_fee\":\"0.00\",\"send_back_fee\":\"0.00\"}*/
            result.setData(response);
            return result;
        } catch (AlipayApiException e) {
            LOGGER.error("申请订单退款出错。", e);
            result.setCode("-10001");
            result.setMessage("系统调用错误，请联系管理员.");
            return result;
        }

    }


    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝统一收单线下交易查询
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    @Override
    public AjaxResult lipayTradeQuery(AlipayTradeQueryModel model) {
        //返回对象
        AjaxResult result = new AjaxResult();

        try {
            //验证前面
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            request.setBizModel(model);

            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode("0");
                result.setMessage("撤销成功，支付宝交易号：" + response.getOutTradeNo());
            } else {
                System.out.println("调用失败");
                result.setCode("-10000");
//                result.setMessage("退款失败，申请退款订单号：" + response.getOutTradeNo());
                //10000:	接口调用成功，调用结果请参考具体的API文档所对应的业务返回参数
                //20000: isp.unknow-error 服务暂不可用（业务系统不可用）  aop.unknow-error 服务暂不可用（网关自身的未知错误）
                //20001 授权权限不足
                //40001 缺少必选参数
                //40002 非法的参数
                //40004 业务处理失败
                //40006 权限不足
                result.setMessage(response.getSubMsg());
            }
           /* {"code":"40004","msg":"Business Failed","subCode":"ACQ.SELLER_BALANCE_NOT_ENOUGH","subMsg":"卖家余额不足",
                    "body":"{\"alipay_trade_refund_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.SELLER_BALANCE_NOT_ENOUGH\",\"sub_msg\":\"卖家余额不足\",\"" +
                    "out_trade_no\":\"TEST2018-05-20 19:37:21\",\"refund_fee\":\"0.00\",\"send_back_fee\":\"0.00\"}*/
            result.setData(response);
            return result;
        } catch (AlipayApiException e) {
            LOGGER.error("申请订单退款出错。", e);
            result.setCode("-10001");
            result.setMessage("系统调用错误，请联系管理员.");
            return result;
        }

    }

    /**
     * @param alipayMap   订单信息
     * @return com.mywuwu.dto.AjaxResult
     * @Description： 支付宝支付回调
     * @Author: 梁乐乐
     * @Date: Created in 2018/9/16 15:33
     */
    @Override
    public AjaxResult alipayNotify(Map alipayMap) {
        //返回对象
        AjaxResult result = new AjaxResult();


        try {
            //循环装支付宝返回值
            Map<String, String> params = new HashMap<>();
            for (Iterator iter = alipayMap.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) alipayMap.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                params.put(name, valueStr);
            }

            //自己的订单号
            String outTradeNo = params.get("out_trade_no");

            //订单状态
            String tradeStatus = params.get("trade_status");

            //支付宝订单号
            String tradeNo = params.get("trade_no");

            //支付状态
            int payStatus = 0;

            System.err.println(tradeNo);
            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);

            if (flag) {//验证成功

                //查询订单号
                WuwuPay wuwuPay = payMapper.selectByPayCode(outTradeNo);

                AlipayTradeQueryModel model = new AlipayTradeQueryModel();
                model.setOutTradeNo(outTradeNo);
                model.setTradeNo(tradeNo);
                //查询订单
                AjaxResult showResult = this.lipayTradeQuery(model);
                if (showResult.getCode().equals("0")) {
                    AlipayTradeQueryResponse res = (AlipayTradeQueryResponse) showResult.getData();
                    tradeStatus = res.getTradeStatus();
                    wuwuPay.setPayMoney(res.getTotalAmount());
                    wuwuPay.setUserId(res.getBuyerUserId());
                    wuwuPay.setPayCode(tradeNo);
                    wuwuPay.setRemark(res.getSubMsg());
                    wuwuPay.setPayAccount(res.getTotalAmount());
                }


                if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {
                    payStatus = 1;
                    //要写的逻辑。自己按自己的要求写
                    result.setMessage("支付宝支付回调成功，订单号：" + outTradeNo + ";支付成功！");
                } else if (tradeStatus.equals("TRADE_CLOSED")) {
                    payStatus = 2;
                    result.setMessage("支付宝支付回调成功，订单号：" + outTradeNo + ";支付失败！");
//                交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
                } else if (tradeStatus.equals("WAIT_BUYER_PAY")) {
                    payStatus = 3;
                    result.setMessage("支付宝支付回调成功，订单号：" + outTradeNo);
                }

                if (wuwuPay != null) {
                    wuwuPay.setPayStatus(payStatus);
                    payMapper.updateByPrimaryKey(wuwuPay);
//                    WuwuCode code = new WuwuCode();
//                    code.setCode("");
                    result.setCode("0");

                } else {
                    result.setCode("-10000");
                    result.setMessage("支付宝支付回调,没有查询到对应支付宝订单：" + outTradeNo);
                }
            } else {//验证失败

            }


        } catch (Exception e) {
            result.setCode("-10001");
            result.setMessage("系统调用错误，请联系管理员.");
            LOGGER.error("支付宝回调，" + e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void aliGetToken(){
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig1.URL,AlipayConfig1.APPID,AlipayConfig1.RSA_PRIVATE_KEY,AlipayConfig1.FORMAT,AlipayConfig1.CHARSET, AlipayConfig1.ALIPAY_PUBLIC_KEY, AlipayConfig1.SIGNTYPE);
            AlipayUserInfoAuthRequest request = new AlipayUserInfoAuthRequest();
            request.setBizContent("{" +
                    "      \"scopes\":[" +
                    "        \"auth_base\"" +
                    "      ]," +
                    "\"state\":\"init\"," +
                    "\"is_mobile\":\"true\"" +
                    "  }");
            AlipayUserInfoAuthResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }

        } catch (Exception e){

        }

    }

}
