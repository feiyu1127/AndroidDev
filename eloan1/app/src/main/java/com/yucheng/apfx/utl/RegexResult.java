/**
 * 
 */
package com.yucheng.apfx.utl;

/**
 * 匹配结果
 */
public class RegexResult {
  /**
   * 
   */
  public RegexResult(boolean match, String msg) {
    this.match = match;
    this.msg = msg;
  }

  @Override
  public String toString() {
    return new StringBuilder("match:").append(match).append(",msg:").append(msg).toString();
  }

  // 是否匹配
  public boolean match = false;
  // 验证消息
  public String msg = null;
}
