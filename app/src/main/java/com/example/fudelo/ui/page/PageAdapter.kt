package com.example.fudelo.ui.page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fudelo.ui.Recipe
import com.example.fudelo.ui.page.Fragment.FirstFragment
import com.example.fudelo.ui.page.Fragment.LastFragment
import com.example.fudelo.ui.page.Fragment.StepFragment

class PageAdapter(activity: FragmentActivity, private val recipe: Recipe) :
    FragmentStateAdapter(activity) {

    private val pages = buildList {
        add(FirstFragment.newInstance(recipe))
        recipe.steps.sortedBy { it.index }.forEach {
            add(StepFragment.newInstance(it.step))
        }
        add(LastFragment.newInstance(recipe))
    }

    override fun getItemCount() = pages.size
    override fun createFragment(position: Int): Fragment = pages[position]
}

