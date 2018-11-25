package com.mywuwu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "wuwu_order")
public class WuwuOrder {
    @Id
    private Integer id;

    @Column(name = "goods_id")
    private String goodsId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "order_remark")
    private String orderRemark;

    @Column(name = "reorder_id")
    private String reorderId;

    @Column(name = "otherGood_id")
    private String othergoodId;

    @Column(name = "img_id")
    private String imgId;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "order_type")
    private Integer orderType;

    @Column(name = "pay_Type")
    private Integer payType;

    @Column(name = "order_No")
    private String orderNo;

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
     * @return goods_id
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
     * @return order_remark
     */
    public String getOrderRemark() {
        return orderRemark;
    }

    /**
     * @param orderRemark
     */
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    /**
     * @return reorder_id
     */
    public String getReorderId() {
        return reorderId;
    }

    /**
     * @param reorderId
     */
    public void setReorderId(String reorderId) {
        this.reorderId = reorderId;
    }

    /**
     * @return otherGood_id
     */
    public String getOthergoodId() {
        return othergoodId;
    }

    /**
     * @param othergoodId
     */
    public void setOthergoodId(String othergoodId) {
        this.othergoodId = othergoodId;
    }

    /**
     * @return img_id
     */
    public String getImgId() {
        return imgId;
    }

    /**
     * @param imgId
     */
    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    /**
     * @return order_status
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return order_time
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * @return order_type
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * @param orderType
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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
     * @return order_No
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}