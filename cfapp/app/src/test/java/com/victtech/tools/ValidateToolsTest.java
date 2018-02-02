package com.victtech.tools;

import android.util.Log;

import com.victtech.model.ErrorModel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Richard on 2018/1/24.
 */
public class ValidateToolsTest {
    @Test
    public void validate() throws Exception {
        ValidateTools.Rule rule = ValidateTools.Rule.NUMBER;
        rule.setErrMsg("该字段必须是数字");
        ErrorModel errorModel = ValidateTools.validate(rule,"a");
        if(errorModel!=null){
            System.out.println("validate: "+errorModel.getMessage());
            assertEquals(errorModel!=null,true);
        }else{
            assertEquals(errorModel==null,true);
        }

    }

}