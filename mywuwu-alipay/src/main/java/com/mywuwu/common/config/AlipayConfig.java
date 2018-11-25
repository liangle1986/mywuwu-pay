package com.mywuwu.common.config;

/**
 * 正式环境
 * @Package: com.mywuwu.quartz.pay.alipay
 * @Description： 阿里支付的公共参数
 * @Author: 梁乐乐
 * @Date: Created in 2018/5/20 10:22
 * @Company: clinbrain
 * @Copyright: Copyright (c) 2018
 * @Version: 0.0.1
 * @Modified By:
 */
public class AlipayConfig {

    // 商户appid 沙箱：2016091500515364   应用：2018051760144755
    public static String APPID = "2018051760144755";
    // 私钥 pkcs8格式的 沙箱：MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPI/Mmj/o3UyR9iI3vRpPFLTMdT+XQfzzYG6T6eRWl/ZSvhePB0zSxvkfrAGwKQdq54DYIj61Crrgi9EJ4lJzxY6MIExxIYmNLEIYjpuOsdckqxgjZZmUcytFzSe2ZZJedntX9VU5faW3QRyJA5n9DyuPMPtmCXAU6fjHZ7SISbuNaNy4mKLUbCKm0hc9iHSDZ0WbXx2lkRhlL/NKeVp8K9qsYRDHGcPdl3t72UFo2/tkGJ9o8omBX0kQxDQsUCTvYDw0mpQP+R+HWrb3j4u9oJdsFIeuGZzvbm8n18Alo/sEmFkwS2Ft7hO0tr8HTtjxYXEZdoRAfYtLMuTooA3yhAgMBAAECggEAX7A1pMrNB10SYMT/tTwmHsMHj4nQ5N+aKhHY6QQ2/58UXX4Q8oqhiEzewO+oSfcaI/YJyRFbTt+EAwHsybT3cbscypIT7yOuowip032GZ8zZrTlzwvlbkLLY78w1BL0lSd5byR3U5Z2SJpDXCjHMU+J3BBvjxeyEJkUkSD3o8A6hXNfcqsB/2UMQrnjytxiqh0ijXtCQHvyq7ko5W8E9a0ad6DsEiRcVf2w9eMqLrAr5jHiBRj6GzkK6yTStXvJ8FtW1p7ik3ctcu7lD3s/lCFSuwTE0OaCwQaEeFejl1jbDi6hhLXkf/euB60nMIg30ZzxeMxo49cwDqr/9/2fgkQKBgQDsK9hqgET/T2a7w7U7IvsWvusLoKbqkthKW+vJK725Nfjf64+LgLTotULpkgO475JyluR0ad3woy+1KA8MYjWOvSxNVTyiwpZKE+UA+0DoAQ59sVfbTjbWL8wo2OZyHl2bSVVTw/9mPcgG1m6NBN9l/bBhKPqf2oBNR/fNFPMbdQKBgQCbKIsJKvOkKWxtv2T61Lx8RXqVuLI9J2cB2zp/PhMIdM4PDpKCMgKJHYeN77JRYt8TsO+DWdMCWw3qxOjNxWKaRq6rzV/GazFCdE2TW1CFmsVahfRBWE3iDiI+SqxwWer3rK/5Cg2CfZQjUpBwT5tqELzeFfbw6iaI3j9WrMKy/QKBgDLclnUJPtLFJNjXIxajR/P0FeahKJFoIpCRD0x50RgsGXcP4hAnHc7oCosG2Spg3eczu+ueSR/j5QhcojEGjYY6E2psKuzaf0dg1XbKpYXRhG9pXARs6b5i+NLrM2XsSDiDKI8rrLs2HvfqAlD4dawfYHbsPl1izzLVhvZxxpFpAoGBAJYPqCi9udZTmeKq8WcGySUHrX+QmhI7QYyyEulth8rt7Tzyww/YfktOnAPSh4vfLBFHVt6ayVHF7rfYqbAZ7zt2kQjoIHEuyv2SrlSORFpzTdw3IfzxAqJXORc18YDX9kCEa81Yw6go/FUNTVTSKWVzurawV5y5WXuWN3wEqmmhAoGAeHzxHtlmihqmqkViLQbpBoGJPzU5x7UfyzS9PdsjvE2WZghBPCI0EQS2REgQiRvrVmWIYIm26J3WEsYbfEDWndAMYOKN1nJTJCJT4qh34mm3lCaxFeNa6m6tnX7CIRqLxH1kdBr267UaM8Vfr653P7Qg3ALnvC1H84SOzQwFes4=
    //   应用：MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPPofMI4Ezy3X0WQsNStiotHECGLWku5+wODEOowHUxpewc2rOu2nQxKkG6Iaq45fYERj3MXC8ek8IS7dkpJhJ1NW9FcBj8cZn8dSoyix6i0RFMuJnh33bBnmBh1nEGNE+3+NTCdYbFsMF4eX9nsIJmIB51+/rtdAxgukURasGzK71Dq2F8PMkFBZNsJE2cbusZGQXe4speaBsI9hAYB57Rl9JXLLaCh3mNDu+I42xuIwXp8sfZ0YGHUMj/CUaSU5ev3TA6Mvi3hfTVyllpqOCxauWmhmLSLEQAaava+c38QJMuKxekaHNz0GLeTnnYH6eQ/ZK9m5NEbGMyY0VsmedAgMBAAECggEAdBUii4ZCUC0T+96lHDKJfb4szpwjp8n80RvYdZoYA8ykZPRDnCE40Vxt9hE+AQLLrOe+KaTBTZx4q6pghoj0ePCn/cHA1b27IcVSpy6f+PcfbkycOXYNwg9vLE75qfbKq39Yg5S3E+DQTtYQQgYEDS6u/w7FeWkvvM/v/uOug6Tm66XS8xarmRgFozvA3CWOEN5k4UM8QhFeuROqkOohdfBeFrKQJU1etn0pC/8iAziSkZWJzvnFVYTQjMnFmicXsiV5t02y85pqFDSP1s5zuxBXzRpTFsexJKEPMV/5TXdgtjavmgVyuKT77s2iISWokCtZx73xJhBgny67I8iGQQKBgQDbZ9xMEG2bmerdWrQODw/+PfhRqQtPfRCr2SSaXz1mB0KLIdpq6FJKokJDie0a0A4E4XaPTjDZYr9A4iva89BGI62DKlIBLesMiE6Af46f8JnU4pnHaUgv+JlADkMf/+qxcgGi3diOeuvFiMEgKsNCmwSbRkJrh1FFt9D0CvYbGQKBgQCnIr6Hjan8pExC/QZiNBrmyVaYLvwlZd+JqQ1lP3aZfOxpgedMY5oiAjVH5TMJOKrqMEhB/oY8SRWec8cyaAsQsGNCyQo2+LbwHSlv0ju1q2PdRVk2uE0bZMVTRQLSH/MLO8vhZ7X1Q8Ew2vbfxm3aeOYiWNYEi9gfovXAmoEFJQKBgQDZcaKEBBgNCUsaW8Vs1YK9Lz+F9uoNul4mYksDb3ZTQsixSTjFkT/VVeAkPXKuixoJvkWZGx4Qo+v16vEZy19T1BInl260WoTD4+chNMnuOy7KLFXJqs7vNAr1z+y4rxIzEFEMz57K/oniOHlmXtsrjKTRGp3DIdXrKZS9+IsWKQKBgGrmwDL3m2hqthSKvlCFv0cqNGb2dnR9KnNkW5sM8pDN5HC/k++6LVXfT7ECmpglVLyh0TXU1Isuu9Mzfo9xGNK9319SM2RhxLDkFiLdty9FxoQ63Qsw6rDAza2rEFjR8qSWxJsRuMJ9ac4T35/VzWHVvmztoUYbJEtn8gQVTry5AoGAb2hlfqH8kZ7UG66/zOyXsDZ0M6a1OnvubKxSfAT+6Q6CEwzu6i88dTl/f+8BozjHbAcUyadnCaCdno/21Hc4tf9agNTe49qM1dAkNPmDm9XiBEbNyo9Wm7VKaPzMAedK4Jjk+h+ginJ5Q7jA/pkf3qZo0MhoklfEd/WpbEdyL1g=
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPPofMI4Ezy3X0WQsNStiotHECGLWku5+wODEOowHUxpewc2rOu2nQxKkG6Iaq45fYERj3MXC8ek8IS7dkpJhJ1NW9FcBj8cZn8dSoyix6i0RFMuJnh33bBnmBh1nEGNE+3+NTCdYbFsMF4eX9nsIJmIB51+/rtdAxgukURasGzK71Dq2F8PMkFBZNsJE2cbusZGQXe4speaBsI9hAYB57Rl9JXLLaCh3mNDu+I42xuIwXp8sfZ0YGHUMj/CUaSU5ev3TA6Mvi3hfTVyllpqOCxauWmhmLSLEQAaava+c38QJMuKxekaHNz0GLeTnnYH6eQ/ZK9m5NEbGMyY0VsmedAgMBAAECggEAdBUii4ZCUC0T+96lHDKJfb4szpwjp8n80RvYdZoYA8ykZPRDnCE40Vxt9hE+AQLLrOe+KaTBTZx4q6pghoj0ePCn/cHA1b27IcVSpy6f+PcfbkycOXYNwg9vLE75qfbKq39Yg5S3E+DQTtYQQgYEDS6u/w7FeWkvvM/v/uOug6Tm66XS8xarmRgFozvA3CWOEN5k4UM8QhFeuROqkOohdfBeFrKQJU1etn0pC/8iAziSkZWJzvnFVYTQjMnFmicXsiV5t02y85pqFDSP1s5zuxBXzRpTFsexJKEPMV/5TXdgtjavmgVyuKT77s2iISWokCtZx73xJhBgny67I8iGQQKBgQDbZ9xMEG2bmerdWrQODw/+PfhRqQtPfRCr2SSaXz1mB0KLIdpq6FJKokJDie0a0A4E4XaPTjDZYr9A4iva89BGI62DKlIBLesMiE6Af46f8JnU4pnHaUgv+JlADkMf/+qxcgGi3diOeuvFiMEgKsNCmwSbRkJrh1FFt9D0CvYbGQKBgQCnIr6Hjan8pExC/QZiNBrmyVaYLvwlZd+JqQ1lP3aZfOxpgedMY5oiAjVH5TMJOKrqMEhB/oY8SRWec8cyaAsQsGNCyQo2+LbwHSlv0ju1q2PdRVk2uE0bZMVTRQLSH/MLO8vhZ7X1Q8Ew2vbfxm3aeOYiWNYEi9gfovXAmoEFJQKBgQDZcaKEBBgNCUsaW8Vs1YK9Lz+F9uoNul4mYksDb3ZTQsixSTjFkT/VVeAkPXKuixoJvkWZGx4Qo+v16vEZy19T1BInl260WoTD4+chNMnuOy7KLFXJqs7vNAr1z+y4rxIzEFEMz57K/oniOHlmXtsrjKTRGp3DIdXrKZS9+IsWKQKBgGrmwDL3m2hqthSKvlCFv0cqNGb2dnR9KnNkW5sM8pDN5HC/k++6LVXfT7ECmpglVLyh0TXU1Isuu9Mzfo9xGNK9319SM2RhxLDkFiLdty9FxoQ63Qsw6rDAza2rEFjR8qSWxJsRuMJ9ac4T35/VzWHVvmztoUYbJEtn8gQVTry5AoGAb2hlfqH8kZ7UG66/zOyXsDZ0M6a1OnvubKxSfAT+6Q6CEwzu6i88dTl/f+8BozjHbAcUyadnCaCdno/21Hc4tf9agNTe49qM1dAkNPmDm9XiBEbNyo9Wm7VKaPzMAedK4Jjk+h+ginJ5Q7jA/pkf3qZo0MhoklfEd/WpbEdyL1g=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8086/alipay/alipayNotify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:8086/alipay/alipayNotify";
    // 请求网关地址 沙箱：https://openapi.alipaydev.com/gateway.do 网站支付:https://openapi.alipay.com/gateway.do
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥 沙箱：MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvbmibHAKUsRNJXKq9LmndvbWaBZXTEfKHTu8hE1/jZs5pQ25g4svLppho+sXikLXKhqzNFg5uXNrytzS1j5SOvCQrIODgi+iVBwAQTBye2qrboB/3FyvK2c6SLXT2XqgveVuNSJkEGIKOt4Omw3zKvoLBCQvvAajVkv54jhGZRHcPtCtbO5O+Ef8Jnazj8vpnby10RorBXSKiEw0CiLJcWBiaa5SBa+syt2rH+EPs7zDcltkio81C5tGzBUjkUGPfCnhxsbDRVg3XRxQQIVAi4uvbvejH9DpyjhQehPBq7s9NIwupaspclHLSoG4vUIyQxk0doBKe12AKBq89YWDkwIDAQAB
    //应用：MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApY1VVzeUKnF2LK7UjORLf6R32vdbamarfFFAosLVRZYHrvGVxuQIsQHbxTfegEKqV6C/mzVrW5Gc0SnU4Ke1bUtbtHlwenv2WKW/sPmSag5UNz3VjW8xGF0m014VdvW4xaliDz16ys1wvh3EPgTaKMszGF8np/RPqa9p11WYPsGgM9LDv8+wD5VwQVXXurUD193aOvepXF9QvpiRFLj7ZeIXSJDMLb6ShJ26zmrCpFJyC/ppmBlczgOI4l464cbCQYwrLSIvXwzqKKf31CnPRbfwzBS59ZRzpAKnrbgde5NNLTYB6hXl5PV450x6PomPQiUWPM13x7R2W8CiUj2v0wIDAQAB
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApY1VVzeUKnF2LK7UjORLf6R32vdbamarfFFAosLVRZYHrvGVxuQIsQHbxTfegEKqV6C/mzVrW5Gc0SnU4Ke1bUtbtHlwenv2WKW/sPmSag5UNz3VjW8xGF0m014VdvW4xaliDz16ys1wvh3EPgTaKMszGF8np/RPqa9p11WYPsGgM9LDv8+wD5VwQVXXurUD193aOvepXF9QvpiRFLj7ZeIXSJDMLb6ShJ26zmrCpFJyC/ppmBlczgOI4l464cbCQYwrLSIvXwzqKKf31CnPRbfwzBS59ZRzpAKnrbgde5NNLTYB6hXl5PV450x6PomPQiUWPM13x7R2W8CiUj2v0wIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}