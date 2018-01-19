package com.yucheng.byxf.mini.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名: Utils4JiaoYiJieGuo</br>
 * 描述: 日间交易查询 </br>
 * 开发人员： Weiyb </br>
 * 创建时间： 2015-7-16
 */

public class Utils4JiaoYiJieGuo
{
    
    // REFU拒绝
    // INIT未处理
    // UPAV完成
    // ERRO错误
    // OVER完成
    
    Map<String, String> mMap;
    
    public Utils4JiaoYiJieGuo()
    {
        super();
        mMap = new HashMap<String, String>();
        
        mMap.put("REFU", "失败");
        mMap.put("INIT", "未处理");
        mMap.put("UPAV", "成功");
        mMap.put("ERRO", "失败");
        mMap.put("OVER", "成功");
        
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
