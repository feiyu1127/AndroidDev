package com.yucheng.byxf.mini.message.rijian;

import java.util.List;

public class MsgBody
{
    
    private String countNum;
    
    private String countPage;
    
    private List<Details> details;
    
    public String getCountNum()
    {
        return countNum;
    }
    
    public void setCountNum(String countNum)
    {
        this.countNum = countNum;
    }
    
    public String getCountPage()
    {
        return countPage;
    }
    
    public void setCountPage(String countPage)
    {
        this.countPage = countPage;
    }
    
    public List<Details> getDetails()
    {
        return details;
    }
    
    public void setDetails(List<Details> details)
    {
        this.details = details;
    }
    
    /**
     * 描述:构造 </br>
     * 开发人员：longtaoge</br>
     * 创建时间：2015-7-13</br>
     */
    public MsgBody()
    {
        super();
        
    }

    @Override
    public String toString()
    {
        return "MsgBody [countNum=" + countNum + ", countPage=" + countPage + ", details=" + details + "]";
    }
    
    
    
    
    
}
