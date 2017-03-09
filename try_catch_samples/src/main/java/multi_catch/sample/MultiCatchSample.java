package multi_catch.sample;

import java.io.IOException;

import multi_catch.sample.model.ApplicationException;
import multi_catch.sample.model.ExceptionA;
import multi_catch.sample.model.ExceptionB;
import multi_catch.sample.model.ExceptionC;
import multi_catch.sample.model.SubExceptionA;

public class MultiCatchSample {
  public void targetMethod() throws ExceptionA, ExceptionB, ExceptionC {
    throw new ExceptionA("A");
    // throw new ExceptionB("B");
    // throw new ExceptionC("C");
    
    // throw new RuntimeException("runtime");
  }

  public void targetMethod2() throws ApplicationException {
    try {
      throw new ExceptionA("A");
      // throw new ExceptionB("B");
      // throw new ExceptionC("C");
    } catch(Exception e) { 
      throw new ApplicationException(e);
    }
  }

  public void targetMethod3() throws ExceptionA, ExceptionB, ExceptionC {
    throw new SubExceptionA("A");
  }

  // マルチキャチせずに、例外処理を個別に行う例
  // 実際やりたいことよりも例外処理がずっと長くなってしまう
  public void exceptionHandling1() {
    try {
      targetMethod();
    } catch(ExceptionA e) {
      // ExceptionA用の例外処理
      System.out.println(e.getMessage());
    } catch(ExceptionB e) {
      // ExceptionB用の例外処理
      System.out.println(e.getMessage());
    } catch(ExceptionC e) {
      // ExceptionC用の例外処理
      System.out.println(e.getMessage());
    }
  }

  // Exceptionでまとめて捕まえる例
  // ただし、こうするとRuntimeExceptionも捕まえてしまう
  public void exceptionHandling2() {
    try {
      throw new IOException();
    } catch(Exception e) {
      if (e instanceof RuntimeException) {
        throw e;
      }
      System.out.println(e.getMessage());
    }
  }

  // 呼び出し先のメソッドで例外をwrapして返す例
  // コードは短くなるが何の例外が発生するか分かりづらくなってしまう
  public void exceptionHandling3() {
    try {
      targetMethod2();
    } catch(ApplicationException e) {
      // また、個別に処理をしたい時に、中身を取り出して例外を調べる必要がある
      if(e.getCause() instanceof ExceptionA) {
        // ExceptionA用の処理
        System.out.println(e.getMessage());
      } else {
        // その他の処理
        System.out.println(e.getMessage());
      }
    }
  }
  
  // multi catchを使う例
  // 複数の例外をまとめてcatchするか、個別にcatchするか選ぶことが出来る
  public void exceptionHandling4() {
    try {
      targetMethod();
    } catch(ExceptionA | ExceptionB e) {
      System.out.println(e.getMessage());
    } catch(ExceptionC e) {
      System.out.println(e.getMessage());
    }
  }

  // multi catchを使う例その２
  // 例外に継承関係があり、サブクラスの場合だけの処理をしたい場合は先にcatchに書く
  public void exceptionHandling5() {
    try {
      targetMethod3();
    } catch(SubExceptionA e) {
      System.out.println(e.getMessage() + "!");
    } catch(ExceptionA | ExceptionB | ExceptionC e) {
      System.out.println(e.getMessage());
    }
  }
  
  public static void main(String...args)  {
    // new MultiCatchSample().exceptionHandling1();
    // new MultiCatchSample().exceptionHandling2();
    // new MultiCatchSample().exceptionHandling3();
    // new MultiCatchSample().exceptionHandling4();
    // new MultiCatchSample().exceptionHandling5();
    
    System.out.println(Integer.MAX_VALUE);
  }
}
