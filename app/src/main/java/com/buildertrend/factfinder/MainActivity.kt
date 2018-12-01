package com.buildertrend.factfinder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.buildertrend.factfinder.injection.DaggerFragmentComponent
import com.buildertrend.factfinder.injection.FragmentComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var component: FragmentComponent
    private var fabConfiguration: FabConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = DaggerFragmentComponent.builder()
            .applicationComponent((application as? FactFinderApplication)?.component)
            .build()

        // TODO
//        fab.clicks()
//            .map { fabConfiguration?.clickHandler?.invoke() }
//            .subscribe()
    }

    fun setFabClickListener(listener: View.OnClickListener) {
        fab.setOnClickListener(listener)
    }

    fun getFragmentComponent() = component
}
