package com.krisnachy.submissiontwo.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.krisnachy.submissiontwo.R
import com.krisnachy.submissiontwo.model.UserModel
import com.krisnachy.submissiontwo.viewModel.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        pbDetail.visibility = View.VISIBLE

        setData()
        viewPagerConfig()
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar!!.title = title
        }
    }

    private fun viewPagerConfig() {
        val viewPagerDetailAdapter = ViewPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = viewPagerDetailAdapter
        tabLayout.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        val dataUser = intent.getParcelableExtra<UserModel>(EXTRA_DATA) as UserModel
        setActionBarTitle(dataUser.name.toString())
        tvNameD.text = dataUser.name.toString()
        tvUsernameD.text = dataUser.username.toString()
        tvCompanyD.text = dataUser.company.toString()
        tvLocationD.text = dataUser.location.toString()
        tvRepoD.text = dataUser.repository.toString()
        tvFollowersD.text = dataUser.followers.toString()
        tvFollowingD.text = dataUser.following.toString()
        Glide.with(this)
                .load(dataUser.avatar.toString())
                .into(ivAvatarD)
    }
}