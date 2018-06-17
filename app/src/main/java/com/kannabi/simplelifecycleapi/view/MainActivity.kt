package com.kannabi.simplelifecycleapi.view

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.kannabi.simplelifecycleapi.*
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.activity.PresenterActivity
import com.kannabi.simplelifecycleapi.di.MainComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : PresenterActivity<IDummyView, MainComponent, IDummyPresenter>(), IDummyView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent().inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        open_fragment.setOnClickListener{_ ->
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, DummyFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun provideComponent() = (application as App).componentProvider.getMainComponent()
}
