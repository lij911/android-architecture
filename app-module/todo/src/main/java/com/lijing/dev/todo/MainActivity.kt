package com.lijing.dev.todo

import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.lijing.dev.core.RouterConstants
import com.lijing.dev.mvvm.core.ToolBarActivity
import com.lijing.dev.mvvm.di.AppComponent
import com.lijing.dev.todo.di.DaggerTodoComponent
import com.lijing.dev.utils.BitmapUtils
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@Route(path = RouterConstants.Todo.ACTIVITY_MAIN)
class MainActivity : ToolBarActivity<MainViewModel>() {

    override val menuRes: Int? get() = R.menu.toolbar

    override val mItemSelected: ((itemId: Int?) -> Unit)?
        get() = {
            when (it) {
                R.id.item_settings ->
                    Toast.makeText(this, "item_settings", Toast.LENGTH_SHORT).show()
                R.id.item_about_us ->
                    Toast.makeText(this, "item_about_us", Toast.LENGTH_SHORT).show()
            }
        }

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
        initStatusBar(R.color.res_black) // 太爽了吧
        val url = "https://b-gold-cdn.xitu.io/v3/static/img/android.fef4da1.png"
        BitmapUtils.loadImage(url) {
            iv_image.setImageBitmap(it)
        }
    }

    override fun setupToolBar(toolbar: Toolbar) {
    }

}
