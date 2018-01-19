package com.yucheng.byxf.mini.message.weichuzhangdan;

import java.util.List;

/**
 * 类名: Myresult</br>
 * 描述: 未出帐单返回结果</br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-8
 */

public class Myresult
{
    /**
     * 本期利息
     */
    private String curNormInt;
    
    /**
     * 总金额
     */
    private String totalAmt;
    
    /**
     * 总利息
     */
    private String totalInt;
    
    /**
     * 未出帐单列表
     */
    List<Details> details;
    
    public String getCurNormInt()
    {
        return curNormInt;
    }
    
    public void setCurNormInt(String curNormInt)
    {
        this.curNormInt = curNormInt;
    }
    
    public String getTotalAmt()
    {
        return totalAmt;
    }
    
    public void setTotalAmt(String totalAmt)
    {
        this.totalAmt = totalAmt;
    }
    
    public String getTotalInt()
    {
        return totalInt;
    }
    
    public void setTotalInt(String totalInt)
    {
        this.totalInt = totalInt;
    }
    
    public List<Details> getDetails()
    {
        return details;
    }
    
    public void setDetails(List<Details> details)
    {
        this.details = details;
    }
    
    public Myresult()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
}
