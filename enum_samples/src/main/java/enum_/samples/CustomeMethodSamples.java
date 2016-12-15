package enum_.samples;

// コンストラクターを使って要素ごとに異なる値を保持し、計算をするサンプル
// (もらったコードをほぼそのまま)
public class CustomeMethodSamples {
  private enum RangeUnit {
    MM(      1.0),
    CM(     10.0),
    M(    1000.0),
    KM(1000000.0);

    // Java の enum はオブジェクトなので、属性を持つことが出来る
    private double v; // 単位がmmの換算値
    
    // 属性をもたせたい場合はコンストラクタを定義する
    // enumのコンストラクタは private しか定義できず、外部から呼ぶことは出来ない
    private RangeUnit(double v){this.v = v; }

    // 属性と同様にメソッドを持つことも出来る
    public double convert(double val, RangeUnit u){
        return val * this.v / u.v;
    }
  }
    
  public static void main(String[] args){
    System.out.println(RangeUnit.KM.convert(1.0,  RangeUnit.MM)); //   1.0km は 1000000.0mm
    System.out.println(RangeUnit.MM.convert(1.0,  RangeUnit.MM)); //   1.0mm は       1.0mm
    System.out.println(RangeUnit.MM.convert(1.0,  RangeUnit.KM)); //   1.0mm は  0.000001km
    System.out.println(RangeUnit.CM.convert(1.0,  RangeUnit.MM)); //   1.0cm は      10.0mm
    System.out.println(RangeUnit.M.convert(1.0,   RangeUnit.CM)); //   1.0m  は     100.0cm
    System.out.println(RangeUnit.M.convert(123.0, RangeUnit.CM)); // 123.0m  は   12300.0cm
  }
}
