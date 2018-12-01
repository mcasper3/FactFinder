package com.buildertrend.factfinder.injection

import com.buildertrend.factfinder.list.FactListFragment
import dagger.Component

@Component(
    dependencies = [
        ApplicationComponent::class
    ]
)
interface FragmentComponent {

    fun inject(factListFragment: FactListFragment)
}
