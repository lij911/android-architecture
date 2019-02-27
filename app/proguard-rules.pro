# 混淆时不使用大小写混合类名
-dontusemixedcaseclassnames
# 不跳过library中的非public的类
-dontskipnonpubliclibraryclasses
# 打印混淆的详细信息
#-verbose

# 不进行预校验，可加快混淆速度
-dontpreverify
# 保留注解中的参数
-keepattributes *Annotation*

# see http://proguard.sourceforge.net/manual/examples.html#beans
# 不混淆View中的setXxx()和getXxx()方法，以保证属性动画正常工作
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# 不混淆Parcelable实现类中的CREATOR字段，以保证Parcelable机制正常工作
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

# 不混淆包含native方法的类的类名以及native方法名
-keepclasseswithmembernames class * {
    native <methods>;
}

# 不混淆Activity中参数是View的方法，例如，一个控件通过android:onClick="clickMethodName"绑定点击事件，混淆后会导致点击事件失效
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# 不混淆R文件中的所有静态字段，以保证正确找到每个资源的id
-keepclassmembers class **.R$* {
    public static <fields>;
}

# 不对android.support包下的代码警告（如果我们打包的版本低于support包下某些类的使用版本，会出现警告的问题）
-dontwarn android.support.**

# 不混淆Keep类
-keep class android.support.annotation.Keep

# 保留用于调试堆栈跟踪的行号信息（为了后期调试方便，建议配置）
-keepattributes SourceFile,LineNumberTable
