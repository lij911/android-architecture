# android-architecture
记录架构过程中遇到的知识点

* 开端
https://juejin.im/post/5bba0f525188255c72285dd8

* 模块化
    https://zhuanlan.zhihu.com/p/26744821
    https://segmentfault.com/a/1190000004247809

* Android 文件存储
    https://blog.csdn.net/linshijun33/article/details/64483252
    https://www.jianshu.com/p/7e7270cd00bd

* Android 高性能日志写入
    https://juejin.im/post/5b6d26016fb9a04f86065cdf


* Type 详解
    http://loveshisong.cn/%E7%BC%96%E7%A8%8B%E6%8A%80%E6%9C%AF/2016-02-16-Type%E8%AF%A6%E8%A7%A3.html


* 精简项目
    https://juejin.im/post/5bea3b01f265da61193b605d

* dagger2
    https://juejin.im/entry/587499ff128fe1006b42f7de
    dagger 的 @Singleton 注解只能保证同个 component 注入的是同一个对象，所以要保证真正的单例需要同时保证 component 是唯一的
    retrofit 的 create 能保证单例，所以可以用不同的 component 注入（但是我现在懒的改了


* recyclerView 复用机制
    https://cloud.tencent.com/developer/article/1128137
    最终调用的是 Recycler 的 tryGetViewHolderForPositionByDeadline 方法, 在该方法中，分4次去复用 ViewHolder
    1. mAttachedScrap: 缓存在屏幕上的ViewHolder。
    2. mCachedViews: 缓存屏幕外的ViewHolder，默认为2个。ListView对于屏幕外的缓存都会调用getView()。
    3. mViewCacheExtensions: 需要用户定制，默认不实现。
    4. mRecyclerPool: 缓存池，多个RecyclerView共用。
* bitmap
    https://juejin.im/post/58c3b29761ff4b005d906730

* LruCache
    https://juejin.im/entry/59f9851251882529c046837e



* Android 协程
    https://www.jianshu.com/p/2659bbe0df16


