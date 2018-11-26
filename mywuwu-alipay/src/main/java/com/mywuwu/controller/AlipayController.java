package com.mywuwu.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.mywuwu.common.config.AlipayConfig;
import com.mywuwu.common.config.AlipayConfig1;
import com.mywuwu.dto.AjaxResult;
import com.mywuwu.service.IAlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;


@RestController
@CrossOrigin
@RequestMapping("/alipay")
public class AlipayController {

    /**
     * 支付宝支付处理类
     */
    @Autowired
    private IAlipayService alipayService;

    /**
     * Description： 支付宝手机网站支付
     * Author: 梁乐乐
     * Date: Created in 2018/5/20 11:07
     */

    @PostMapping("alipayTradeWapPay") //正式调用
    public AjaxResult alipayTradeWapPay(@RequestBody AlipayTradeWapPayModel wapModel) {
        return alipayService.alipayTradeWapPay(wapModel);
    }

    @GetMapping("alipayTradeWapPay") //测试专用
    public String alipayTradeWapPay() {
        AlipayTradeWapPayModel orderDto = new AlipayTradeWapPayModel();
        orderDto.setBody("苹果64G，标准版8p");
        orderDto.setOutTradeNo("TEST" + new Date().getTime());
        orderDto.setProductCode("MYWUWU_IPHONE_8P" + new Date().getTime());
        orderDto.setSubject("苹果手机");
        orderDto.setTimeoutExpress("90m");
        orderDto.setTotalAmount("0.01");
        AjaxResult result = alipayService.alipayTradeWapPay(orderDto);
        if ("0".equals(result.getCode())) {
            return result.getData().toString();
        } else {
            return JSON.toJSONString(result);
        }
    }

    /**
     * Description： 支付宝网站支付
     * Author: 梁乐乐
     * Date: Created in 2018/5/20 11:07
     */

    @PostMapping("alipayTradePagePay") //正式调用
    public AjaxResult alipayTradePagePay(@RequestBody AlipayTradePagePayModel model) {
        return alipayService.alipayTradePagePay(model);
    }

    @GetMapping("alipayTradePagePay") //测试专用
    public String alipayTradePagePay(String title, String amount, String remark) {
        AlipayTradePagePayModel orderDto = new AlipayTradePagePayModel();
        orderDto.setBody(remark);
        orderDto.setOutTradeNo("TEST" + new Date().getTime() + "TRADENO");
//        orderDto.setOutTradeNo("TEST15370776702821TRADENO");
        orderDto.setProductCode("FAST_INSTANT_TRADE_PAY");
        orderDto.setSubject(title);
        orderDto.setTimeoutExpress("90m");
        orderDto.setTotalAmount(amount);
        orderDto.setIntegrationType("PCWEB");
        orderDto.setRequestFromUrl(AlipayConfig1.return_url);
        orderDto.setTimeoutExpress("10m");
        AjaxResult result = alipayService.alipayTradePagePay(orderDto);
        if ("0".equals(result.getCode())) {
            return result.getData().toString();
        } else {
            return JSON.toJSONString(result);
        }
    }


    /**
     * @param fasModel
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 交易退款查询
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:10
     */
    @PostMapping("alipayTradeFastpayRefundQuery")
    public AjaxResult alipayTradeFastpayRefundQuery(@RequestBody AlipayTradeFastpayRefundQueryModel fasModel) {
        return alipayService.alipayTradeFastpayRefundQuery(fasModel);
    }

    @GetMapping("alipayTradeFastpayRefundQuery") //测试通过
    public AjaxResult alipayTradeFastpayRefundQuery() {
        AlipayTradeFastpayRefundQueryModel fasModel = new AlipayTradeFastpayRefundQueryModel();
        fasModel.setOutTradeNo("TEST2018-05-20 19:37:21");
        fasModel.setTradeNo("2018052021001004620569307644");
        fasModel.setOutRequestNo("2018052021001004620569307644");
        return alipayService.alipayTradeFastpayRefundQuery(fasModel);
    }

    /**
     * @param queryModel 查询订单关键信息
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝订单查询接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/22 18:47
     */
    @PostMapping("alipayFundTransOrderQuery")
    public AjaxResult alipayFundTransOrderQuery(@RequestBody AlipayFundTransOrderQueryModel queryModel) {
        return alipayService.alipayFundTransOrderQuery(queryModel);
    }

    @GetMapping("alipayFundTransOrderQuery") //无权限，暂时无法使用
    public AjaxResult alipayFundTransOrderQuery() {
        AlipayFundTransOrderQueryModel queryModel = new AlipayFundTransOrderQueryModel();
        queryModel.setOrderId("2018052021001004620569307644");
        queryModel.setOutBizNo("TEST2018-05-20 19:37:21");
        return alipayService.alipayFundTransOrderQuery(queryModel);
    }

    /**
     * @param model 申请退款信息
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝退款接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    @PostMapping("alipayTradeRefund")
    public AjaxResult alipayTradeRefund(@RequestBody AlipayTradeRefundApplyModel model) {
        return alipayService.alipayTradeRefund(model);
    }

    @GetMapping("alipayTradeRefund") //测试通过
    public AjaxResult alipayTradeRefund() {
        AlipayTradeRefundApplyModel model = new AlipayTradeRefundApplyModel();
        model.setOperatorId("T001");
        model.setOutRequestNo(new Date().getTime() + "OUT");
        model.setRefundAmount("0.01");
        model.setRefundReason("测试专用");
        model.setTerminalId("");
        model.setTradeNo("2018052221001004620281491942");
        return alipayService.alipayTradeRefund(model);
    }


    /**
     * @param model
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝交易关闭接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45AlipayTradePrecreateRequest
     */
    @PostMapping("alipayTradeClose")
    public AjaxResult alipayTradeClose(@RequestBody AlipayTradeCloseModel model) {
        return alipayService.alipayTradeClose(model);
    }


    @GetMapping("alipayTradeClose")//测试通过
    public AjaxResult alipayTradeClose() {
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
//        model.setOutTradeNo("TEST1527083559803TRADENO");
        model.setTradeNo("2018052321001004470200286439");
        return alipayService.alipayTradeClose(model);
    }


    /**
     * @param model 创建订单参数
     * @return com.mywuwu.quartz.dto.AjaxResult
     * @Description： 支付宝统一收单交易创建接口
     * @Author: 梁乐乐
     * @Date: Created in 2018/5/21 22:45
     */
    @PostMapping("alipayTradeCreate")
    public AjaxResult alipayTradeCreate(@RequestBody AlipayTradeCreateModel model) {
        return alipayService.alipayTradeCreate(model);

    }

    @GetMapping("alipayTradeCreate")
    public AjaxResult alipayTradeCreate() {
        AlipayTradeCreateModel model = new AlipayTradeCreateModel();
        model.setBody("苹果64G，标准版8p");
        model.setOutTradeNo("TEST" + new Date().getTime());
        model.setDiscountableAmount("0.2");
        model.setSubject("苹果手机");
        model.setTimeoutExpress("90m");
        model.setTotalAmount("0.2");
        return alipayService.alipayTradeCreate(model);

    }


    /**
     * 异步通知付款状态的Controller
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("alipayNotify")
    public AjaxResult notify(HttpServletRequest request,
                             HttpServletResponse response) throws AlipayApiException {
        return alipayService.alipayNotify(request.getParameterMap());
    }


    @GetMapping("aliGetToken")
    public String aliGetToken(){
//        alipayService.aliGetToken();
//        return "<a href=\"alipays://platformapi/startapp?appId=" + AlipayConfig.APPID + "&url=" +  URLEncoder.encode("https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?app_id="+ AlipayConfig.APPID +"&scope=auth_user&redirect_uri=http://www.ywuwu.com")+"\">点击此处拉起支付宝进行授权+"+URLEncoder.encode("https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?app_id="+ AlipayConfig.APPID +"&scope=auth_user&redirect_uri=http://www.ywuwu.com")+"</a>";
//        return "https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?app_id="+ AlipayConfig.APPID +"&redirect_uri="+ URLEncoder.encode("http://www.ywuwu.com");
        return "<a href=\"alipays://platformapi/startapp?appId=" + AlipayConfig.APPID + "&url=" + AlipayConfig.login_app_url+"\">点击此处拉起支付宝进行授权+"+URLEncoder.encode("https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?app_id="+ AlipayConfig.APPID +"&scope=auth_user&redirect_uri=http://www.ywuwu.com")+"</a>";
//        return AlipayConfig.login_app_url;
    }

}
