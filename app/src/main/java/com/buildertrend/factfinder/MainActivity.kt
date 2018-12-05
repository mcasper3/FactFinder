package com.buildertrend.factfinder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.buildertrend.factfinder.injection.DaggerFragmentComponent
import com.buildertrend.factfinder.injection.FragmentComponent
import com.buildertrend.factfinder.list.FactListFragment
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fabClickObservable: Observable<Unit>? = null

    private lateinit var component: FragmentComponent
    private var fabConfiguration: FabConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = DaggerFragmentComponent.builder()
            .applicationComponent((application as? FactFinderApplication)?.component)
            .build()

        supportFragmentManager.beginTransaction()
            .replace(R.id.content, FactListFragment.newInstance())
            .commit()
        // TODO in a different way
        fabClickObservable = fab.clicks()

//            .map { fabConfiguration?.clickHandler?.invoke() }
    }

    fun setFabClickListener(listener: View.OnClickListener) {
        fab.setOnClickListener(listener)
    }

    fun getFragmentComponent() = component
}
