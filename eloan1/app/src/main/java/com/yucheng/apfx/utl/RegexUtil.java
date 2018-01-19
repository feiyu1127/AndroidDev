/**
 * 
 */
package com.yucheng.apfx.utl;

/**
 * 匹配规则
 */
public class RegexUtil {
  /**
   * 
   */
  public RegexUtil() {
  }

  /**
   * 验证不为空
   * 
   * @param value
   *          待验证值
   * @return 验证结果
   */
  public static boolean isNotNull(String value) {
    return null != value;
  }

  /**
   * 验证不为"null"字符串
   * 
   * @param value
   *          待验证值
   * @return 验证结果
   */
  public static boolean isNotNullStr(String value) {
    return isNotNull(value) && !("null".equalsIgnoreCase(value));
  }

  /**
   * 验证不为空字符串、空格
   * 
   * @param value
   *          待验证值
   * @return 验证结果
   */
  public static boolean isNotBlank(String value) {
    return isNotNull(value) && 0 != value.trim().length();
  }

  /**
   * 验证不为空、"null"字符串、空字符串、空格
   * 
   * @param value
   *          待验证值
   * @return 验证结果
   */
  public static boolean required(String value) {
    return isNotNull(value) && isNotNullStr(value) && isNotBlank(value);
  }

  /**
   * 验证是否满足必须项
   * 
   * @param label
   *          域段标签
   * @param value
   *          待验证值
   * @return 验证结果
   */
  public static RegexResult required(String label, String value) {
    boolean match = required(value);
    String msg = null;
    if (!match) {
      msg = label + "为必填项";
    }
    return new RegexResult(match, msg);
  }

  public static boolean range(String value, long min, long max) {
    long val = 0;
    try {
      val = Long.parseLong(value);
    }
    catch (NumberFormatException e) {
      // val = 0;
    }
    return min <= val && val <= max;
  }

  public static RegexResult range(String label, String value, long min, long max) {
    boolean match = range(value, min, max);
    String msg = null;
    if (!match) {
      msg = label + "应在" + min + "到" + max + "之间";
    }
    return new RegexResult(match, msg);
  }

  public static boolean min(String value, long min) {
    long val = 0;
    try {
      val = Long.parseLong(value);
    }
    catch (NumberFormatException e) {
      // val = 0;
    }
    return min <= val;
  }

  public static RegexResult min(String label, String value, long min) {
    boolean match = min(value, min);
    String msg = null;
    if (!match) {
      msg = label + "应不小于" + min;
    }
    return new RegexResult(match, msg);
  }

  public static boolean max(String value, long max) {
    long val = 0;
    try {
      val = Long.parseLong(value);
    }
    catch (NumberFormatException e) {
      // val = 0;
    }
    return val <= max;
  }

  public static RegexResult max(String label, String value, long max) {
    boolean match = max(value, max);
    String msg = null;
    if (!match) {
      msg = label + "应不大于" + max;
    }
    return new RegexResult(match, msg);
  }

  public static boolean length(String value, int min, int max) {
    boolean b = false;
    try {
      int n = value.length();
      b = min <= n && n <= max;
    }
    catch (Exception e) {
      // b=false;
    }
    return b;
  }

  public static RegexResult length(String label, String value, int min, int max) {
    boolean match = length(value, min, max);
    String msg = null;
    if (!match) {
      msg = label + "的长度应在" + min + "到" + max + "之间";
    }
    return new RegexResult(match, msg);
  }

  public static boolean lengthMin(String value, int min) {
    return isNotNull(value) && min <= value.length();
  }

  public static RegexResult lengthMin(String label, String value, int min) {
    boolean match = lengthMin(value, min);
    String msg = null;
    if (!match) {
      msg = label + "的长度应不小于" + min;
    }
    return new RegexResult(match, msg);
  }

  public static boolean lengthMax(String value, int max) {
    return isNotNull(value) && value.length() <= max;
  }

  public static RegexResult lengthMax(String label, String value, int max) {
    boolean match = lengthMax(value, max);
    String msg = null;
    if (!match) {
      msg = label + "的长度应不大于" + max;
    }
    return new RegexResult(match, msg);
  }

  public static boolean startsWith(String value, String prefix) {
    return isNotNull(value) && value.startsWith(prefix);
  }

  public static RegexResult startsWith(String label, String value, String prefix) {
    boolean match = startsWith(value, prefix);
    String msg = null;
    if (!match) {
      msg = label + "应以" + prefix + "开头";
    }
    return new RegexResult(match, msg);
  }

  public static boolean endsWith(String value, String suffix) {
    return isNotNull(value) && value.endsWith(suffix);
  }

  public static RegexResult endsWith(String label, String value, String suffix) {
    boolean match = endsWith(value, suffix);
    String msg = null;
    if (!match) {
      msg = label + "应以" + suffix + "结尾";
    }
    return new RegexResult(match, msg);
  }

  public static boolean alpha(String value) {
    return isNotNull(value) && value.matches(alpha);
  }

  public static RegexResult alpha(String label, String value) {
    boolean match = alpha(value);
    String msg = null;
    if (!match) {
      msg = label + "应该为英文字母";
    }
    return new RegexResult(match, msg);
  }

  public static boolean alphaUpper(String value) {
    return isNotNull(value) && value.matches(alphaUpper);
  }

  public static RegexResult alphaUpper(String label, String value) {
    boolean match = alphaUpper(value);
    String msg = null;
    if (!match) {
      msg = label + "应为大写英文字母";
    }
    return new RegexResult(match, msg);
  }

  public static boolean alphaLower(String value) {
    return isNotNull(value) && value.matches(alphaLower);
  }

  public static RegexResult alphaLower(String label, String value) {
    boolean match = alphaLower(value);
    String msg = null;
    if (!match) {
      msg = label + "应为小写英文字母";
    }
    return new RegexResult(match, msg);
  }

  public static boolean date(String value) {
    return false;
  }

  public static boolean numberNatural(String value) {
    return isNotNull(value) && value.matches(numberNatural);
  }

  public static RegexResult numberNatural(String label, String value) {
    boolean match = numberNatural(value);
    String msg = null;
    if (!match) {
      msg = label + "应为数字";
    }
    return new RegexResult(match, msg);
  }

  public static boolean numberPositive(String value) {
    return isNotNull(value) && value.matches(numberPositive);
  }

  public static RegexResult numberPositive(String label, String value) {
    boolean match = numberPositive(value);
    String msg = null;
    if (!match) {
      msg = label + "应为正整数";
    }
    return new RegexResult(match, msg);
  }

  public static boolean numberNegative(String value) {
    return isNotNull(value) && value.matches(numberNegative);
  }

  public static RegexResult numberNegative(String label, String value) {
    boolean match = numberNegative(value);
    String msg = null;
    if (!match) {
      msg = label + "应为负整数";
    }
    return new RegexResult(match, msg);
  }

  public static boolean number(String value) {
    return isNotNull(value) && value.matches(number);
  }

  public static RegexResult number(String label, String value) {
    boolean match = number(value);
    String msg = null;
    if (!match) {
      msg = label + "应为整数";
    }
    return new RegexResult(match, msg);
  }

  public static boolean doubled(String value) {
    return isNotNull(value) && value.matches(doubled);
  }

  public static RegexResult doubled(String label, String value) {
    boolean match = doubled(value);
    String msg = null;
    if (!match) {
      msg = label + "应为浮点数";
    }
    return new RegexResult(match, msg);
  }

  public static boolean currency(String value) {
    return isNotNull(value) && value.matches(currency);
  }

  public static RegexResult currency(String label, String value) {
    boolean match = currency(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合货币格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean qq(String value) {
    return isNotNull(value) && value.matches(qq);
  }

  public static RegexResult qq(String label, String value) {
    boolean match = qq(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合QQ号码格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean email(String value) {
    return isNotNull(value) && value.matches(email);
  }

  public static RegexResult email(String label, String value) {
    boolean match = email(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合电子邮箱格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean phoneFix(String value) {
    return isNotNull(value) && value.matches(phoneFix);
  }

  public static RegexResult phoneFix(String label, String value) {
    boolean match = phoneFix(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合固话号码格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean phoneMob(String value) {
    return isNotNull(value) && value.matches(phoneMob);
  }
  public static boolean nosymbol(String value) {
	  return isNotNull(value) && value.matches(nosymbol);
  }

  public static RegexResult phoneMob(String label, String value) {
    boolean match = phoneMob(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合手机号码格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean phone(String value) {
    return isNotNull(value) && value.matches(phone);
  }

  public static RegexResult phone(String label, String value) {
    boolean match = phone(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合电话号码格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean passport(String value) {
    return isNotNull(value) && value.matches(passport);
  }

  public static RegexResult passport(String label, String value) {
    boolean match = passport(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合护照号码格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean idcard(String value) {
    return isNotNull(value) && value.matches(idcard);
  }

  public static RegexResult idcard(String label, String value) {
    boolean match = idcard(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合身份证号码格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean creditcard(String value) {
    return isNotNull(value) && value.matches(creditcard);
  }

  public static RegexResult creditcard(String label, String value) {
    boolean match = creditcard(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合信用卡号码格式";
    }
    return new RegexResult(match, msg);
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
      msg = label + "的长度应在6到20之间，并以字母开头";
    }
    return new RegexResult(match, msg);
  }

  public static boolean chinese(String value) {
    return isNotNull(value) && value.matches(chinese);
  }

  public static RegexResult chinese(String label, String value) {
    boolean match = chinese(value);
    String msg = null;
    if (!match) {
      msg = label + "应为汉字";
    }
    return new RegexResult(match, msg);
  }

  public static boolean url(String value) {
    return isNotNull(value) && value.matches(url);
  }

  public static RegexResult url(String label, String value) {
    boolean match = url(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合网址格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean ip(String value) {
    return isNotNull(value) && value.matches(ip);
  }

  public static RegexResult ip(String label, String value) {
    boolean match = ip(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合IP地址格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean zip(String value) {
    return value.matches(zip);
  }

  public static RegexResult zip(String label, String value) {
    boolean match = zip(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合邮编格式";
    }
    return new RegexResult(match, msg);
  }

  public static boolean isbn(String value) {
    return isNotNull(value) && value.matches(isbn);
  }

  public static RegexResult isbn(String label, String value) {
    boolean match = isbn(value);
    String msg = null;
    if (!match) {
      msg = label + "应符合国际书刊号格式";
    }
    return new RegexResult(match, msg);
  }

  /**
   * 规则表达
   */
  // 英文字母
  protected static final String alpha = "[A-Za-z]+";
  // 大写字母
  protected static final String alphaUpper = "[A-Z]+";
  // 小字字母
  protected static final String alphaLower = "[a-z]+";
  // 自然数
  protected static final String numberNatural = "\\d+";
  // 正整数
  protected static final String numberPositive = "[1-9][0-9]*";
  // 负整数
  protected static final String numberNegative = "-[1-9][0-9]*";
  // 整数
  protected static final String number = "-?\\d+";
  // 浮点数
  protected static final String doubled = "(-?\\d+)(\\.\\d+)?";
  // 货币
  protected static final String currency = "\\d+(\\.\\d+)?";
  // QQ号码
  protected static final String qq = "[1-9]\\d{4,9}";
  // 电子邮箱
  protected static final String email = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
  // 固话号码
  protected static final String phoneFix = "[1-9](\\d{6,7})";
  // 手机号码
  protected static final String phoneMob = "((\\(\\d{2,3}\\))|(\\d{3}\\-))?(1[34578]\\d{9})";
  // 电话号码
  protected static final String phone = "(" + phoneFix + ")" + "|" + "(" + phoneMob + ")";
  // 护照号码
  protected static final String passport = " (P\\d{7})|(G\\d{8})";
  // 身份证号
  protected static final String idcard = "\\d{15}|\\d{18}";
  // 信用卡号
  protected static final String creditcard = "((?:4\\d{3})|(?:5[1-5]\\d{2})|(?:6011)|(?:3[68]\\d{2})|(?:30[012345]\\d))[ -]?(\\d{4})[ -]?(\\d{4})[ -]?(\\d{4}|3[4,7]\\d{13})";
  // 用户名
  protected static final String userName = "[a-zA-Z][a-zA-Z0-9_\\-]{3,19}";
  // 密码
  protected static final String userPass = "[a-zA-Z][a-zA-Z0-9_\\-]{5,19}";
  // 汉字
  protected static final String chinese = "[\\u0391-\\uFFE5]+";
  // 网址
  protected static final String url = "http://[A-Za-z0-9]+\\.[A-Za-z0-9]+[/=\\?%\\-&_~`@[\\]\':+!]*([^<>\"])*";
  // IP地址
  protected static final String ip = "(0|[1-9]\\d?|[0-1]\\d{2}|2[0-4]\\d|25[0-5]).(0|[1-9]\\d?|[0-1]\\d{2}|2[0-4]\\d|25[0-5]).(0|[1-9]\\d?|[0-1]\\d{2}|2[0-4]\\d|25[0-5]).(0|[1-9]\\d?|[0-1]\\d{2}|2[0-4]\\d|25[0-5])";
  // 邮政编码
  protected static final String zip = "[1-9]\\d{5}";
  // 国际书刊号
  protected static final String isbn = "(\\d[- ]*){9}[\\dxX]";
  //只能输入汉字字母
  protected static final String nosymbol = "^[\u4E00-\u9FA5A-Za-z0-9]+$";

}
