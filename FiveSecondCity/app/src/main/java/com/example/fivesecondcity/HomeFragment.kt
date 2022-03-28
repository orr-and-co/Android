package com.example.fivesecondcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


const val ID_VAL = "com.example.fivesecondcity.ArticleID"

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = FragmentAdapter(this)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val mediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = position.toString()
        }.attach()
    }

    class FragmentAdapter : FragmentStateAdapter
    {
        constructor(fragmentActivity: FragmentActivity?) : super(fragmentActivity!!) {}
        constructor(fragment: Fragment?) : super(fragment!!) {}
        constructor(fragmentManager: FragmentManager?, lifecycle: Lifecycle?) : super(
            fragmentManager!!,
            lifecycle!!
        ) {}

        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            if(position == 0)
                return VideoFragment()
            return FullFeedFragment()
        }

    }
}