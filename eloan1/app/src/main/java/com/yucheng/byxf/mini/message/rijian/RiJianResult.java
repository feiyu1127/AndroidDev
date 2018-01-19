package com.yucheng.byxf.mini.message.rijian;

public class RiJianResult
{
    /**
     * 错误码
     */
    private String errorCode;
    
    /**
     * 错误信息
     */
    private String errorMsg;
    
    
    private MsgBody msgBody;


    public String getErrorCode()
    {
        return errorCode;
    }


    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }


    public String getErrorMsg()
    {
        return errorMsg;
    }


    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }


    public MsgBody getMsgBody()
    {
        return msgBody;
    }


    public void setMsgBody(MsgBody msgBody)
    {
        this.msgBody = msgBody;
    }


    /** 
     * 描述:构造 </br>
     * 开发人员：weiyb</br>
     * 创建时间：2015-7-13</br>
     */ 
    public RiJianResult()
    {
        super();
        
    }


    @Override
    public String toString()
    {
        return "RiJianResult [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", msgBody=" + msgBody + "]";
    }
    
    
    
    
    
    
    
}
