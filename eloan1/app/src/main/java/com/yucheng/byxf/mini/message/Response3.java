package com.yucheng.byxf.mini.message;

public class Response3
{
    protected Integer code;
    
    protected String msg;
    
    protected String flag;
    
    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    protected String result;
    
    public String getFlag()
    {
        return flag;
    }
    
    public void setFlag(String flag)
    {
        this.flag = flag;
    }
    
    public Response3()
    {
    }
    
    public Response3(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
    
    public Integer getCode()
    {
        return code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
