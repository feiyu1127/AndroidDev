package com.victtech.tools;

import com.victtech.model.ErrorModel;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Richard on 2018/1/24.
 */

public class ValidateTools {
    public enum Rule{
        REQUIRED(){
            @Override
            public String getReg() {
                return "required";
            }

            @Override
            public String getErrMsg() {
                if(this.errMsg.equals("")){
                    return "必填字段";
                }else{
                    return this.errMsg;
                }
            }
        },
        NUMBER(){
            @Override
            public String getReg() {
                return "-?\\d*";
            }

            @Override
            public String getErrMsg() {
                if(this.errMsg.equals("")){
                    return "必填是数字";
                }else{
                    return this.errMsg;
                }
            }
        };

        String errMsg="";
        String reg="";

        public String getReg(){
            return "";
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }
    }

    public static ErrorModel validate(Rule rule, String value){
        ErrorModel errorModel = new ErrorModel();
        if(compile(rule,value)){
            return null;
        }else{
            errorModel.setCode(-1);
            errorModel.setMessage(rule.getErrMsg());
            return errorModel;

        }
    }

    public static ErrorModel validate(List<Rule> rules, String value){
        for(Rule rule:rules){
            return validate(rule,value);
        }
        return null;
    }
    private static boolean compile(Rule rule,String value){
        if(rule == Rule.REQUIRED){
            if(value==null || value.equals("")){
                return false;
            }else{
                return true;
            }
        }else{
            Pattern pattern = Pattern.compile(rule.getReg());
            Matcher matcher = pattern.matcher(value);
            System.out.println(matcher.matches());
            return matcher.matches();
        }

    }



}
