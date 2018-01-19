/**
 * 
 */
package com.yucheng.byxf.mini.utils;

import com.yucheng.apfx.utl.RegexUtil;
import com.yucheng.apfx.utl.RegexResult;

/**
 * 匹配规则
 */
public class RegexCust extends RegexUtil {
  /**
   * 
   */
  public RegexCust() {
    super();
  }

  public static boolean userName(String value) {
    return isNotNull(value) && value.matches(userName);
  }

  public static RegexResult userName(String label, String value) {
    boolean match = userName(value);
    String msg = null;
    if (!match) {
      msg = label + "的长度应在4到20之间，并以字母开头";
    }
    return new RegexResult(match, msg);
  }

  public static boolean userPass(String value) {
    return isNotNull(value) && value.matches(userPass);
  }

  public static RegexResult userPass(String label, String value) {
    boolean match = userPass(value);
    String msg = null;
    if (!match) {
      msg = label + "的长度应在8到20之间，并以字母开头";
    }
    return new RegexResult(match, msg);
  }

  /**
   * 规则表达
   */
  // 用户名
  private static final String userName = "[a-zA-Z][a-zA-Z0-9]{3,19}";
  // 密码
//  private static final String userPass = "[a-zA-Z][a-zA-Z0-9]{7,19}";
  private static final String userPass = "[a-zA-Z0-9]{8,20}";
}
