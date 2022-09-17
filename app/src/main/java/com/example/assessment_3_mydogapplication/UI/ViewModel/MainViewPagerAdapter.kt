package com.example.assessment_3_mydogapplication.UI.ViewModel

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assessment_3_mydogapplication.Keys.BundleKeys
import com.example.assessment_3_mydogapplication.R
import com.example.assessment_3_mydogapplication.UI.View.BreedListFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = MainTab.values().size
    override fun createFragment(position: Int): Fragment {
        return BreedListFragment().apply {
            arguments = bundleOf(BundleKeys.MAIN_TAB to MainTab.values()[position])
        }
    }
}
enum class MainTab(val titleRest: Int){
    All(R.string.main_tab_all),
    Faved(R.string.main_tab_faved)
}