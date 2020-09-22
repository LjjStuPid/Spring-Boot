package com.liujj.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liujinjian
 * @Date: 2020/7/27 15:24
 */
@Data
public class CouponCallbackRequest implements Serializable {

    /**
     * 业务细分事件类型：
     * EVENT_TYPE_BUSICOUPON_SEND：商家券用户领券通知
     */
    private String event_type;

    /**
     * 券的唯一标识
     */
    private String coupon_code;

    /**
     * 批次号
     */
    private String stock_id;

    /**
     * 发放时间,格式为：YYYY-MM-DDTHH:mm:ss +TIMEZONE
     * 示例值：2019-12-17T10:35:53+08:00
     */
    private String send_time;

    /**
     * 微信用户在appid下的唯一标识
     */
    private String openid;

    /**
     * 微信用户在同一个微信开放平台账号下的唯一用户标识
     */
    private String unionid;

    /**
     * 发放渠道,枚举值：
     * BUSICOUPON_SEND_CHANNEL_MINIAPP：小程序
     * BUSICOUPON_SEND_CHANNEL_API：API
     * BUSICOUPON_SEND_CHANNEL_PAYGIFT：支付有礼
     * BUSICOUPON_SEND_CHANNEL_H5：H5
     * BUSICOUPON_SEND_CHANNEL_FTOF：面对面
     * BUSICOUPON_SEND_CHANNEL_MEMBER_CARD_ACT：会员卡活动
     */
    private String send_channel;

    /**
     * 发券商户号
     */
    private String send_merchant;

    /***
     * 发券附加信息,仅在支付有礼渠道，才有该信息
     */
    private Object attach_info;


    public static class AttachInfo {
        /**
         * 交易订单编号
         */
        private String transaction_id;

        /**
         * 支付有礼活动编号
         */
        private String act_code;
    }
}
