package com.mywuwu.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.AlipayOpenAuthTokenAppQueryRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.mywuwu.common.config.AlipayConfig;
import com.mywuwu.common.config.AlipayConfig1;
import com.mywuwu.common.util.WuwuCodeUtils;
import com.mywuwu.dto.AjaxResult;
import com.mywuwu.service.IAlipayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
@Api(tags = "AlipayController", description = "AlipayController | 支付报支付接口调用")
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
    @ApiOperation(value="支付宝手机网站支付", notes="支付宝手机网站支付")
    @ApiImplicitParam(name = "wapModel", value = "支付参数对象", required = true, dataType = "AlipayTradeWapPayModel")
    @PostMapping("alipayTradeWapPay") //正式调用
    public AjaxResult alipayTradeWapPay(@RequestBody AlipayTradeWapPayModel wapModel) {
        return alipayService.alipayTradeWapPay(wapModel);
    }

    @ApiOperation(value="支付宝手机网站支付", notes="测试专用")
//    @ApiImplicitParam(name = "alipayTradeWapPay", value = "测试专用", required = true, dataType = "AlipayTradeWapPayModel")
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
    @ApiOperation(value="支付宝网站支付", notes="支付宝网站支付")
    @ApiImplicitParam(name = "model", value = "支付宝网站支付对象", required = true, dataType = "AlipayTradePagePayModel")
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
    @ApiOperation(value="交易退款查询", notes="交易退款查询")
    @ApiImplicitParam(name = "fasModel", value = "交易退款查询对象", required = true, dataType = "AlipayTradeFastpayRefundQueryModel")
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
    @ApiOperation(value="支付宝订单查询接口", notes="支付宝订单查询接口")
    @ApiImplicitParam(name = "queryModel", value = "支付宝订单查询接口对象", required = true, dataType = "AlipayFundTransOrderQueryModel")
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
    @ApiOperation(value="支付宝退款接口", notes="支付宝退款接口")
    @ApiImplicitParam(name = "model", value = "支付宝退款接口对象", required = true, dataType = "AlipayTradeRefundApplyModel")
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
    @ApiOperation(value="支付宝交易关闭接口", notes="支付宝交易关闭接口")
    @ApiImplicitParam(name = "model", value = "支付宝交易关闭对象", required = true, dataType = "AlipayTradeCloseModel")
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
    @ApiOperation(value="支付宝统一收单交易创建接口", notes="支付宝统一收单交易创建接口")
    @ApiImplicitParam(name = "model", value = "支付宝统一收单交易创建对象", required = true, dataType = "AlipayTradeCreateModel")
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
    @ApiOperation(value="异步通知付款状态的", notes="异步通知付款状态的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "回调对象", required = true, dataType = "HttpServletRequest"),
            @ApiImplicitParam(name = "response", value = "返回对象", required = true, dataType = "HttpServletResponse")
    })
    @GetMapping("alipayNotify")
    public AjaxResult notify(HttpServletRequest request,
                             HttpServletResponse response) throws AlipayApiException {
        return alipayService.alipayNotify(request.getParameterMap());
    }

    @ApiOperation(value="支付宝手机网站支付", notes="支付宝手机网站支付")
    @ApiImplicitParam(name = "request", value = "获取支付宝openid", required = true, dataType = "HttpServletRequest")
    @GetMapping("aliGetToken")
    public AjaxResult aliGetToken(HttpServletRequest request) throws IOException {
//        alipayService.aliGetToken();
        AjaxResult result = new AjaxResult();
        result.setCode("0");
        //appId=20000067后面这一串数字是固定不是自己的appId否则无法唤起支付宝
        //https://openauth.alipay.com/auth/tokenManage.htm 去掉支付宝授权
        //获取访问设备类型
        if (WuwuCodeUtils.JudgeIsMoblie(request)) {
            result.setData("alipays://platformapi/startapp?appId=20000067&url=" + URLEncoder.encode(AlipayConfig.login_url));
        } else {
            result.setData(URLEncoder.encode(AlipayConfig.login_url));
        }

//        console.log(this.$route.query.A);vue获取get后的地址
//        return "<a href=\"alipays://platformapi/startapp?appId=" + AlipayConfig.APPID + "&url=" +  URLEncoder.encode("https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?app_id="+ AlipayConfig.APPID +"&scope=auth_user&redirect_uri=http://www.ywuwu.com")+"\">点击此处拉起支付宝进行授权+"+URLEncoder.encode("https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?app_id="+ AlipayConfig.APPID +"&scope=auth_user&redirect_uri=http://www.ywuwu.com")+"</a>";
//        return "https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?app_id="+ AlipayConfig.APPID +"&redirect_uri="+ URLEncoder.encode("http://www.ywuwu.com");
//                "<a href=\"alipays://platformapi/startapp?appId=" + AlipayConfig.APPID + "&url=" + AlipayConfig.login_app_url+"\">点击此处拉起支付宝进行授权+"+URLEncoder.encode("https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?app_id="+ AlipayConfig.APPID +"&scope=auth_user&redirect_uri=http://www.ywuwu.com")+"</a>";
//        response.sendRedirect(AlipayConfig.login_app_url);
        return result;
//        return "<a href=\"alipays://platformapi/startapp?appId=20000067&url=" + URLEncoder.encode(AlipayConfig.login_url)+"\">点击此处拉起支付宝进行授权</a>";
    }
    @ApiOperation(value="根据openid获取token", notes="根据openid获取token")
    @ApiImplicitParam(name = "authCode", value = "openid", required = true, dataType = "String")
    @GetMapping("alipayToken")
    public AjaxResult getAlipayCodeToToken(String authCode) {

        AjaxResult result = new AjaxResult();
        result.setCode("0");
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
            AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
            request.setBizContent("{" +
                    "    \"grant_type\":\"authorization_code\"," +
                    "    \"code\":\"" + authCode + "\"" +
                    "  }");
            AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
            System.out.println(response.getUserId());
            Map<String, Object> map = new HashMap<>();
            map.put("code", authCode);
            map.put("token", response.getAppAuthToken());
            result.setData(map);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            result.setCode("1");
        }
        return result;
    }
    @ApiOperation(value="alipayTokenUserInfo", notes="返回支付宝用户信息")
    @ApiImplicitParam(name = "token", value = "支付宝token", required = true, dataType = "String")
    @GetMapping("alipayTokenUserInfo")
    public AjaxResult alipayToken(String token) {

        AjaxResult result = new AjaxResult();
        result.setCode("0");

        try {
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
            AlipayOpenAuthTokenAppQueryRequest requestt = new AlipayOpenAuthTokenAppQueryRequest();
            requestt.setBizContent("{" + " \"app_auth_token\":'" + token + "' }");
            AlipayOpenAuthTokenAppQueryResponse qresponse = alipayClient.execute(requestt);
            System.out.println(qresponse.getBody());
            Map<String, Object> map = new HashMap<>();
            map.put("id", "A0001");
            map.put("name", "ASDFASDF");
            map.put("url", "https://picsum.photos/200/300/?random");
            map.put("loginType", "1");
            result.setData(map);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            result.setCode("1");
        }
        return result;
    }
}
