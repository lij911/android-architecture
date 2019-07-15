package com.lijing.dev.todo

import android.animation.ObjectAnimator
import android.support.v7.widget.Toolbar
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.alibaba.android.arouter.facade.annotation.Route
import com.lijing.dev.core.RouterConstants
import com.lijing.dev.mvvm.core.ToolBarActivity
import com.lijing.dev.mvvm.di.AppComponent
import com.lijing.dev.todo.di.DaggerTodoComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@Route(path = RouterConstants.Todo.ACTIVITY_MAIN)
class MainActivity : ToolBarActivity<MainViewModel>() {

    override val menuRes: Int? get() = R.menu.toolbar

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }

    override fun getContentLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun bindViewModel() {
        DaggerTodoComponent.builder()
                .appComponent(AppComponent.instance).build()
                .inject(this)
    }

    override fun initVariables() {
    }

    override fun initViewsAndEvents() {
        // 属性动画
        tv_subTitle.setOnClickListener {
            ObjectAnimator.ofFloat(it, "rotationY", 0f, 360f)
                    .setDuration(2000)
                    .start()
        }
        // View 动画
        tv_title.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.res_view_anim_alpha)
            // 设置动画监听
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
            it.startAnimation(animation)
        }
    }

    override fun setupToolBar(toolbar: Toolbar) {
        initStatusBar(R.color.res_black)
    }

}
