# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-libraryjars libs/pushservice-5.6.0.30.jar
-dontwarn com.baidu.**
-keep class com.baidu.**{*; }

-libraryjars libs/MobTools-2016.0714.1402.jar
-dontwarn com.mob.tools.**
-keep class com.mob.tools.**{*; }

-libraryjars libs/MobCommons-2016.0714.1402.jar
-dontwarn com.mob.commons.**
-keep class com.mob.commons.**{*; }


-libraryjars libs/AMap_Search_V3.4.0_20160811.jar
-dontwarn com.amap.api.services.**
-keep class com.amap.api.services.**{*; }


-libraryjars libs/AMap_Location_V2.4.1_20160414.jar
-dontwarn com.**
-keep class com.**{*; }

-libraryjars libs/AMap_2DMap_V2.9.0_20160525.jar
-dontwarn com.amap.api.**
-keep class com.amap.api.**{*; }

-libraryjars libs/alipayutdid.jar
-dontwarn com.**
-keep class com.**{*; }

-libraryjars libs/alipaysdk.jar
-dontwarn com.alipay.**
-keep class com.alipay.**{*; }

## 高德地图
-dontwarn com.amap.api.**
-dontwarn com.a.a.**
-dontwarn com.autonavi.**
-keep class com.amap.api.**  {*;}
-keep class com.autonavi.**  {*;}
-keep class com.a.a.**  {*;}
#
-dontwarn com.loc.**
-keep class com.loc.**  {*;}

## 支付宝
-dontwarn com.alipay.android.app.**
-keep class com.alipay.android.app.** {*;}
## 友盟
-dontwarn com.umeng.**
-keep class com.umeng.** { *;}
-keepclassmembers class * {    public <init>(org.json.JSONObject); }
-keep public class com.hyx.maizuo.main.R$*{     public static final int *; }
-keep public class com.umeng.fb.ui.ThreadView { }
## Mob
-dontwarn com.mob.**
-keep class com.mob.**  {*;}
#
#
## OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
## Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
#
## butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
#
-keepclasseswithmembernames class * {   @butterknife.* <fields>;  }
-keepclasseswithmembernames class * {   @butterknife.* <methods>;  }
#
## ormlite
-dontwarn com.j256.**
-keep class com.j256.** { *; }
-keep enum com.j256.** { *; }
-keep interface com.j256.** { *; }
#
-keep class Bloom.model.dao.bean.** { *; }
#
## 不混淆 com.squareup.picasso
-keepattributes SourceFile,LineNumberTable
-keep class com.parse.*{ *; }
-dontwarn com.parse.**
-dontwarn com.squareup.picasso.**
-keepclasseswithmembernames class * {native <methods>;}
#
###---------------Begin: proguard configuration for Gson  ----------
## Gson uses generic type information stored in a class file when working with fields. Proguard
## removes such information by default, so configure it to keep all of it.
-keepattributes Signature
#
## For using GSON @Expose annotation
-keepattributes *Annotation*
#
## Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

## Application classes that will be serialized/deserialized over Gson
-keep class Bloom.model.net.bean.** { *; }

##---------------End: proguard configuration for Gson  ----------

