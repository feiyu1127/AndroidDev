package com.yucheng.byxf.mini.message.weichuzhangdan;

/**
 * 类名: Details</br>
 * 描述: 未出帐单详情内部类 </br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-8
 */

public class Details
{
    /**
     * 到期还款日
     */
    private String acctDt;
    
    /**
     * 交易时间
     */
    private String hostTxDt;
    
    /**
     * 交易金额
     */
    private String txAmt;
    
    /**
     * 交易描述
     */
    private String txDesc;
    
    /**
     * 交易类型
     */
    private String txTyp;
    
    public String getAcctDt()
    {
        return acctDt;
    }
    
    public void setAcctDt(String acctDt)
    {
        this.acctDt = acctDt;
    }
    
    public String getHostTxDt()
    {
        return hostTxDt;
    }
    
    public void setHostTxDt(String hostTxDt)
    {
        this.hostTxDt = hostTxDt;
    }
    
    public String getTxAmt()
    {
        return txAmt;
    }
    
    public void setTxAmt(String txAmt)
    {
        this.txAmt = txAmt;
    }
    
    public String getTxDesc()
    {
        return txDesc;
    }
    
    public void setTxDesc(String txDesc)
    {
        this.txDesc = txDesc;
    }
    
    public String getTxTyp()
    {
        return txTyp;
    }
    
    public void setTxTyp(String txTyp)
    {
        this.txTyp = txTyp;
    }
    
    public Details()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
}
