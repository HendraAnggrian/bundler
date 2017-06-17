package com.example.bundlerexample

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes abstract val contentLayout: Int

    private var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayout)
        unbinder = ButterKnife.bind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder!!.unbind()
    }
}