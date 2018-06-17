package com.kannabi.simplelifecycleapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kannabi.simplelifecycleapi.di.MainComponent
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.fragment.PresenterFragment

class DummyFragment : PresenterFragment<IDummyView, MainComponent, IDummyPresenter>(), IDummyView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dummy, container, false)
    }

    override fun provideComponent() = (activity!!.application as App).componentProvider.getMainComponent()

    override fun onResume() {
        super.onResume()
    }
}
