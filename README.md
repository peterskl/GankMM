# GankMM -- 干货营
![](https://github.com/maning0303/GankMM/raw/master/screenshots/icon.png)<br>

##### 干货营 是干货集中营（Gank.io）的三方客户端，每天一张美女图片，一个视频短片，若干程序干货，周一到周五每天更新。
#####（Material Design & MVP & Retrofit + OKHttp & RecyclerView ...)


# 知识点：
    1.Retrofit和OKHttp的使用，实现网络自动缓存；
    2.MVP模式的基本使用；
    3.RecycleView控件的使用；
    4.状态的的颜色的设置（Android 4.4 +）；
    5.Android Material Design 风格控件的基本使用；
    6.SQLite的基本使用；
    7.WebView的使用，自动缓存,夜间模式；
    8.Jsoup抓取网页数据；
    9.Android 6.0权限适配
    10.夜间模式的使用
    11.三方开源库的使用


## 所有的数据来自：
#### [干货集中营(http://gank.io/)](http://gank.io/)<br>


## 注意：
#### 若是出现保存图片失败，或者更新应用失败请查看是不是存储权限关闭了。


## APK下载地址：
###  应用宝 市场已经通过审核，请到市场下载--->搜索：干货营
#### [点击下载(http://fir.im/gankmm)](http://fir.im/gankmm)<br>
#### 扫描下载:
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_ewm.png)


### GankMM夜间模式实现：
#### [AndroidChangeSkinDemo](https://github.com/maning0303/AndroidChangeSkinDemo)

### GankMM图片浏览器库：
#### [MNImageBrowser](https://github.com/maning0303/MNImageBrowser)

### GankMM日期选择库：
#### [MNCalendar](https://github.com/maning0303/MNCalendar)

### GankMM崩溃日志监听库：
#### [CrashMonitor](https://github.com/maning0303/CrashMonitor)

### GankMM版本更新库：
#### [MNUpdateAPK](https://github.com/maning0303/MNUpdateAPK)

### GankMM首页滚动条库：
#### [SwitcherView](https://github.com/maning0303/SwitcherView)



##  项目截图：

![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_day_01.png)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_night_01.png)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_day_02.jpg)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_night_02.jpg)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_day_03.jpg)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_night_03.jpg)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_001.jpg)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_002.jpg)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_003.png)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_004.png)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_005.png)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_006.png)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_007.png)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_008.png)
![](https://github.com/maning0303/GankMM/raw/master/screenshots/gank_009.jpg)



## 更新日志：
### V1.6.3:
    1.使用Vector替换图片
    2.首页添加工具合集
    3.图片浏览器更换新的UI界面
    4.修复7.0升级安装APK失败Bug

### V1.6.1:
    1.分类浏览添加图片展示
    2.升级数据库添加图片字段
    3.去掉广告SDK,升级推送SDK
    4.优化界面展示
    5.修复部分Bug

### V1.6.0:
    1.天气页面信息展示完善
    2.优化部分界面显示
    3.修复Bug

### V1.5.1:
    1.修复小Bug,优化体验
    2.添加意见反馈回复提示框(之前忘记调用方法了)
    3.增加城市天气信息展示

### V1.5.0:
    1.首页添加干货搜索功能
    2.完善权限管理,修复之前由于权限造成的升级失败的问题
    3.添加了广告SDK,不影响正常使用,放在设置->广告栏
    4.优化体验

### V1.4.3:
    1.Gank头条可以在设置界面配置
    2.修复泡在网上的日子数据异常问题
    3.添加CocoaChina代码展示
    4.更新Gradle和support版本

### V1.4.2:
    1.修复历史浏览可能出现的一个崩溃问题

### V1.4.1:
    1.紧急修复夜间模式设置selector错误导致部分手机崩溃的问题
    2.希望大家有问题和意见都可以反馈回来

### V1.4.0:
    1.去掉Umeng自动更新，使用fir.im 接口更新检测
    2.升级Umeng反馈到阿里百川反馈界面
    3.修复Umeng统计添加错误
    4.图片浏览添加分享功能
    5.修复已知Bug,优化用户体验

### V1.3.1:
    1.修复item点击跳转错乱问题
    2.优化MVP模式，添加判空处理

### V1.3.0:
    1.添加夜间模式（设置页面）
    2.妹子图瀑布流去掉假的随机高度，计算图片真实高度
    3.修复已知Bug，优化代码
    4.首页添加Gank滚动头条（暂时头条全部属于Android头条）

### V1.2.4:
    1.修复上一版本重构MVP造成的Bug
    2.适配6.0权限
    3.页面小调整

### V1.2.3:
    1.使用MVP模式重构页面
    2.修复已知和反馈的相关Bug
    3.SnackBar完全替换Toast（上一版本没有替换完全）
    4.更改反馈界面，支持反馈图片和语音
    5.添加泡在网上的日子的数据（利用Jsoup抓取网页数据）

### V1.2.2:
    1.更新网络框架：Retrofit升级到2.2.1，OKHttp升级到3.3.1，实现网络请求自动缓存功能
    2.修复部分页面ToolBar显示问题
    3.修复保存图片和设置壁纸的Bug
    4.使用SnackBar替换Toast

### V1.2.1:
    1.修复首页图片缓存问题
    2.设置界面添加手动检查版本更新功能

### V1.2.0:
    1.界面大更改，添加时间浏览和分类浏览，图片浏览改为瀑布流形式（假瀑布流形式，高度随机给的）。
    2.添加推送功能 
    3.修复部分Bug

### V1.1.0:
    1.所有ListView替换为RecycleView
    2.修复Bug
    3.更换图标，APP名字

    
## 关于
##### 亲，喜欢就start一下吧
##### 您可以在App意见反馈反馈您的意见和想法，也可以在这里提出您宝贵的[意见和想法](https://github.com/maning0303/GankMM/issues)


## 感谢：
###### [代码家-干货集中营](https://github.com/daimajia)
###### [drakeet](https://github.com/drakeet/Meizhi)
###### [xingrz](https://github.com/xingrz/GankMeizhi)
###### [dongjunkun](https://github.com/dongjunkun/GanK)
###### [xiongwei-git](https://github.com/xiongwei-git/GankApp)
###### [zzhoujay](https://github.com/zzhoujay/Gank4Android)
###### [所有的开源的人](https://github.com)


## 依赖库：
###### 注解框架 [butterknife](https://github.com/JakeWharton/butterknife)
###### Json解析 [Gson](https://github.com/google/gson)
###### 网络框架 [retrofit](https://github.com/square/retrofit)  [okhttp](https://github.com/square/okhttp)
###### 打印日志框架 [klog](https://github.com/ZhaoKaiQiang/KLog)
###### 图片加载 [glide](https://github.com/bumptech/glide)
###### 刷新框架 [SwipeToLoadLayout](https://github.com/Aspsine/SwipeToLoadLayout)
###### 解析Html [jsoup](https://github.com/jhy/jsoup)
###### 权限管理库 [AndPermission](https://github.com/yanzhenjie/AndPermission)
###### 提示框  [material-dialogs](https://github.com/afollestad/material-dialogs)  [Android-SVProgressHUD](https://github.com/saiwu-bigkoo/Android-SVProgressHUD)
###### RecycleView分割线 [RecyclerView-FlexibleDivider](https://github.com/yqritc/RecyclerView-FlexibleDivider)
###### ViewPager的标题控件 [smarttablayout](https://github.com/ogaclejapan/SmartTabLayout)
###### 广告轮播控件 [SwitcherView](https://github.com/maning0303/SwitcherView)
###### 收藏按钮 [ThumbUp](https://github.com/ldoublem/ThumbUp)
###### 模糊控件 [Blurry](https://github.com/wasabeef/Blurry)
###### 网络请求监控 [chuck](https://github.com/jgilfelt/chuck)
###### 表格控件 [scrollablepanel](https://github.com/Kelin-Hong/ScrollablePanel)
###### 可以展开的文字 [expandableTextView](https://github.com/Manabu-GT/ExpandableTextView)
###### 自定义日历控件 [MNCalendar](https://github.com/maning0303/MNCalendar)
###### 日志监听 [CrashMonitor](https://github.com/maning0303/CrashMonitor)
###### 图片缩放 [PhotoView](https://github.com/chrisbanes/PhotoView)
###### APK升级安装 [MNUpdateAPK](https://github.com/maning0303/MNUpdateAPK)



