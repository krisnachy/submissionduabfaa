package com.krisnachy.submissiontwo.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.krisnachy.submissiontwo.R
import com.krisnachy.submissiontwo.model.UserModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_users.view.*

var followersList = ArrayList<UserModel>()

class FollowersAdapter(listData: ArrayList<UserModel>) : RecyclerView.Adapter<FollowersAdapter.ListDataHolder>() {

    init {
        followersList = listData
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgAvatar: CircleImageView = itemView.ivAvatar
        var name: TextView = itemView.tvName
        var username: TextView = itemView.tvUsername
        var followers: TextView = itemView.tvFollowers
        var following: TextView = itemView.tvFollowing
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(DataFollowers: UserModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_users, parent, false)
        val sch = ListDataHolder(view)
        mContext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        val data = followersList[position]
        Glide.with(holder.itemView.context)
                .load(data.avatar)
                .apply(RequestOptions().override(250, 250))
                .into(holder.imgAvatar)
        holder.name.text = data.name
        holder.username.text = data.username
        holder.followers.text = data.followers.toString().trim()
        holder.following.text = data.following.toString().trim()
        holder.itemView.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return followersList.size
    }
}