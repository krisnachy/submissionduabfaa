@file:Suppress("DEPRECATION")

package com.krisnachy.submissiontwo.viewModel

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.krisnachy.submissiontwo.R
import com.krisnachy.submissiontwo.view.FollowersFragment
import com.krisnachy.submissiontwo.view.FollowingFragment

class ViewPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = listOf(FollowersFragment(), FollowingFragment())

    @StringRes
    private val tabTitle = intArrayOf(
            R.string.followers,
            R.string.following
    )
    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabTitle[position])
    }
}