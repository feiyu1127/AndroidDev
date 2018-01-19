package com.yucheng.byxf.mini.message;

import com.yucheng.byxf.mini.message.weichuzhangdan.Myresult;

/**
 * 类名: XiaoJinYingMingXiresult_Weichu_Response</br>
 * 描述:未出帐单返回值数据模型 </br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-8
 */

public class XiaoJinYingMingXiresult_Weichu_Response extends Response
{
    /**
     * 结果
     */
    private Myresult result;
    
    /**
     * 标志位
     */
    private String flag;
    
    public Myresult getResult()
    {
        return result;
    }
    
    public void setResult(Myresult result)
    {
        this.result = result;
    }
    
    public String getFlag()
    {
        return flag;
    }
    
    public void setFlag(String flag)
    {
        this.flag = flag;
    }
    
    public XiaoJinYingMingXiresult_Weichu_Response()
    {
        super();
        
    }
    
    public XiaoJinYingMingXiresult_Weichu_Response(Integer code, String msg)
    {
        super(code, msg);
        
    }
    
}
