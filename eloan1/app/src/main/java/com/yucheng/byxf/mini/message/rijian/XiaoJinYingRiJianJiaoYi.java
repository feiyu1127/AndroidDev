package com.yucheng.byxf.mini.message.rijian;

import com.yucheng.byxf.mini.message.Response;

public class XiaoJinYingRiJianJiaoYi extends Response {
	
	private String flag;
	
	private RiJianResult result;

	public XiaoJinYingRiJianJiaoYi() {
		super();
	}

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public RiJianResult getResult()
    {
        return result;
    }

    public void setResult(RiJianResult result)
    {
        this.result = result;
    }

    public XiaoJinYingRiJianJiaoYi(Integer code, String msg)
    {
        super(code, msg);
        
    }

  

	
	
	
	
}
