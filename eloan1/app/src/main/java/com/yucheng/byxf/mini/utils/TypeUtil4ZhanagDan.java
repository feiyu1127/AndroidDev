package com.yucheng.byxf.mini.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名: TypeUtil4ZhanagDan</br>
 * 描述: Mini卡 日间交易类型工具类</br>
 * 开发人员： Weiyb</br>
 * 创建时间： 2015-7-16
 */

public class TypeUtil4ZhanagDan
{
    
    /*
     * LND4 日终还款
     * REPAYMENT 存款
     * CASHOUT 取现
     * LRX1 提领费
     * LRX4 年费
     * LND3 利息计提
     * LND5 滞纳金
     * LRD7 滞纳金
     * STAGES 分期
     * AFFIRM 还款
     * LRX5 提现费用
     * LCC5 挂失费用
     * LCC2 换卡费用
     * CASHOUTREVSE 取现冲正
     * TRANSFER 转账
     * PAYCANCEL 存款撤销
     * RETURN 退货
     * REPAYM 还款
     */
    
    Map<String, String> mMap;
    
    public TypeUtil4ZhanagDan()
    {
        super();
        mMap = new HashMap<String, String>();
        
        mMap.put("LND4", "日终还款");
        mMap.put("REPAYMENT", "存款");
        mMap.put("CASHOUT", "取现");
        mMap.put("LRX1", "提领费");
        mMap.put("LRX4", "年费");
        
        mMap.put("LND3", "利息计提");
        mMap.put("LND5", "滞纳金");
        mMap.put("LRD7", "滞纳金");
        mMap.put("STAGES", "分期");
        mMap.put("AFFIRM", "还款");
        
        mMap.put("LRX5", "提现费用");
        mMap.put("LCC5", "挂失费用");
        mMap.put("LCC2", "换卡费用");
        mMap.put("CASHOUTREVSE", "取现冲正");
        mMap.put("TRANSFER", "转账");
        
        mMap.put("PAYCANCEL", "存款撤销");
        mMap.put("RETURN", "退货");
        mMap.put("REPAYM", "还款");
        
    }
    
    /**
     * 描述: 根据类型码返回类型字符串</br>
     * 开发人员：Weiyb</br>
     * 创建时间：2015-7-14</br>
     * @param type
     * @return 根据预设字典返回对应值，如果无对应值返回 ""
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
