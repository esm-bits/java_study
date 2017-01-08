package annotation.sample;

import java.io.Serializable;
import java.util.List;

// @SuppressWaringsは警告が出るのを抑制する
public class SuppressWarningsSamples {
  // 使用されていないprivate変数やローカル変数の場合に出る警告を抑制する
  @SuppressWarnings("unused")
  private String unusedSample = "hoge";

  // ブロックに対して指定することも出来る
  @SuppressWarnings("unused")
  private class UnusedSample {
    String unusedSample = "hoge";
  }
 
  // 不要なCastをした場合に出る警告を抑制する
  @SuppressWarnings("cast")
  public String castSample = (String) "String";
 
  // finally区内でreturnした場合に出る警告を抑制する 
  @SuppressWarnings("finally")
  public int finallySample() {
    try{
      return 0;
    } finally {
      return 1;
    }
  }
 
  // 直列化可能なクラスでは、直列化した際のバージョン管理に使うserialVersionUIDという定数を
  // 持っていないと警告が出る。これを抑制する。
  @SuppressWarnings({"unused", "serial"})
  private class SerializeSample implements Serializable {
  }
 
  @SuppressWarnings("unused")
  private class UncheckedSample {
    // 型チェック無しでキャストしようとした際に uncheckedの警告が出る。
    // また、raw型のジェネリクスを使うとrawtypesの警告が出る。
    @SuppressWarnings({"unchecked", "rawtypes"})
    void test(List list) {
      list.add("abc");
    }
  }
}
