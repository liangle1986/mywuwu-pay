package com.mywuwu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "wuwu_pay")
public class WuwuPay {
    @Id
    private Integer id;

    @Column(name = "pay_Status")
    private Integer payStatus;

    @Column(name = "mypay_code")
    private String mypayCode;

    @Column(name = "reDateTime")
    private Date redatetime;

    @Column(name = "pay_Type")
    private Integer payType;

    @Column(name = "pay_code")
    private String payCode;

    @Column(name = "pay_account")
    private String payAccount;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "pay_money")
    private String payMoney;

    private String remark;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return pay_Status
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * @param payStatus
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * @return mypay_code
     */
    public String getMypayCode() {
        return mypayCode;
    }

    /**
     * @param mypayCode
     */
    public void setMypayCode(String mypayCode) {
        this.mypayCode = mypayCode;
    }

    /**
     * @return reDateTime
     */
    public Date getRedatetime() {
        return redatetime;
    }

    /**
     * @param redatetime
     */
    public void setRedatetime(Date redatetime) {
        this.redatetime = redatetime;
    }

    /**
     * @return pay_Type
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * @param payType
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * @return pay_code
     */
    public String getPayCode() {
        return payCode;
    }

    /**
     * @param payCode
     */
    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    /**
     * @return pay_account
     */
    public String getPayAccount() {
        return payAccount;
    }

    /**
     * @param payAccount
     */
    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return pay_money
     */
    public String getPayMoney() {
        return payMoney;
    }

    /**
     * @param payMoney
     */
    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}