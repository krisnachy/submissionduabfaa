package com.krisnachy.submissiontwo.viewModel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.krisnachy.submissiontwo.R
import com.krisnachy.submissiontwo.model.UserModel
import com.krisnachy.submissiontwo.view.DetailActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_users.view.*
import java.util.*
import kotlin.collections.ArrayList

var userList = ArrayList<UserModel>()
lateinit var mContext: Context

class UsersAdapter(private val listData: ArrayList<UserModel>) : RecyclerView.Adapter<UsersAdapter.ListDataHolder>(), Filterable {

    init {
        userList = listData
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgAvatar: CircleImageView = itemView.ivAvatar
        var name: TextView = itemView.tvName
        var username: TextView = itemView.tvUsername
        var followers: TextView = itemView.tvFollowers
        var following: TextView = itemView.tvFollowing
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_users, parent, false)
        val sch = ListDataHolder(view)
        mContext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        val data = userList[position]
        Glide.with(holder.itemView.context)
                .load(data.avatar)
                .apply(RequestOptions().override(250, 250))
                .into(holder.imgAvatar)
        holder.name.text = data.name
        holder.username.text = data.username
        holder.followers.text = data.followers.toString().trim()
        holder.following.text = data.following.toString().trim()
        holder.itemView.setOnClickListener {
            val dataUser = UserModel(
                    data.username,
                    data.name,
                    data.avatar,
                    data.company,
                    data.location,
                    data.repository,
                    data.followers,
                    data.following
            )
            val intentDetail = Intent(mContext, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_DATA, dataUser)
            mContext.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                userList = if (charSearch.isEmpty()) {
                    listData
                } else {
                    val resultList = ArrayList<UserModel>()
                    for (row in userList) {
                        if ((row.username.toString().toLowerCase(Locale.ROOT)
                                        .contains(charSearch.toLowerCase(Locale.ROOT)))
                        ) {
                            resultList.add(
                                    UserModel(
                                            row.username,
                                            row.name,
                                            row.avatar,
                                            row.company,
                                            row.location,
                                            row.repository,
                                            row.followers,
                                            row.following
                                    )
                            )
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                userList = p1?.values as ArrayList<UserModel>
                notifyDataSetChanged()
            }

        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(userModel: UserModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}