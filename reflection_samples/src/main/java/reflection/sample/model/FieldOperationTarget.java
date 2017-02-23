package reflection.sample.model;

  @SuppressWarnings("unused")
public class FieldOperationTarget {
  public String public_field       = "public field";
  String package_private_field     = "package private field";
  protected String protected_field = "protected field";
  private String private_field     = "private field";

  public static String public_static_field       = "public static field";
  static String package_private_static_field     = "package private static field";
  protected static String protected_static_field = "protected static field";
  private static String private_static_field     = "private static field";

  public final String public_final_field       = "public final field";
  final String package_private_final_field     = "package private final field";
  protected final String protected_final_field = "protected final field";
  private final String private_final_field     = "private final field";
    
  public static final String public_static_final_field       = "public static final field";
}
