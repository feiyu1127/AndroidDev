package com.yucheng.byxf.mini.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名: TypeUtil</br>
 * 描述: 日间交易类型工具类 </br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-14
 */

public class TypeUtil
{
    
    /*
     * CASHOUT取现
     * CONSUME消费
     * REPAYMENT存款
     * TRANSFER转账
     * CASHOUTREVSE取现冲正
     * CASHOUTCANCEL取款撤销
     * AFFIRM存款确认
     * PAYREVSE存款冲正
     * PAYCANCEL存款撤销
     * STAGES分期
     * REPAYM还款
     * RETURN退货
     */
    
    Map<String, String> mMap;
    
    public TypeUtil()
    {
        super();
        mMap = new HashMap<String, String>();
        mMap.put("CASHOUT", "取现");
        mMap.put("CONSUME", "消费");
        mMap.put("REPAYMENT", "存款");
        mMap.put("TRANSFER", "转账");
        mMap.put("CASHOUTREVSE", "取现冲正");
        mMap.put("CASHOUTCANCEL", "取款撤销");
        
        mMap.put("AFFIRM", "存款确认");
        mMap.put("PAYREVSE", "存款冲正");
        mMap.put("PAYCANCEL", "存款撤销");
        mMap.put("STAGES", "分期");
        mMap.put("REPAYM", "还款");
        mMap.put("RETURN", "退货");
        
    }
    
    /**
     * 描述: 根据类型码返回类型字符串</br>
     * 开发人员：Weiyb</br>
     * 创建时间：2015-7-14</br>
     * @param type
     * @return
     */
    public String getType(String type)
    {
        
        String mtype = "";
        
        if (mMap.get(type) != null)
        {
            mtype = mMap.get(type);
        }
        
        return mtype;
        
    }
    
}
