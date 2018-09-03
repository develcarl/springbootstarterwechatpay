package org.develcarl.common.enums;

/**
 * @author yichen
 * @description
 *
 * 1-SUCCESS—支付成功
 *
 * 2-REFUND—转入退款
 *
 * 3-NOTPAY—未支付
 *
 * 4-CLOSED—已关闭
 *
 * 5-REVOKED—已撤销（刷卡支付）
 *
 * 6-USERPAYING--用户支付中
 *
 * 7-PAYERROR--支付失败(其他原因，如银行返回失败)
 *
 * @date 2018/8/7
 **/
public enum TradeStateEnum {

    SUCCESS("SUCCESS", 1),

    REFUND("REFUND", 2),

    NOT_PAY("NOTPAY", 3),

    CLOSED("CLOSED", 4),

    REVOKED("REVOKED", 5),

    USER_PAYING("USERPAYING", 6),

    PAY_ERROR("PAYERROR", 7);

    private String tradeStateString;

    private Integer state;

    TradeStateEnum(String tradeStateString, Integer state) {
        this.tradeStateString = tradeStateString;
        this.state = state;
    }

    public Integer getState(String tradeStateString){
        for (TradeStateEnum tradeStateEnum : TradeStateEnum.values()){
            if (tradeStateEnum.getTradeStateString().equals(tradeStateString)){
                return tradeStateEnum.getState();
            }
        }
        return 0;
    }

    public String getTradeStateString() {
        return tradeStateString;
    }

    public Integer getState() {
        return state;
    }
}
