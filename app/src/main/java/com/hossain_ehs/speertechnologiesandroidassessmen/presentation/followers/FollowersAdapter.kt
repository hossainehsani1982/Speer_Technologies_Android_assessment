package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.followers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hossain_ehs.speertechnologiesandroidassessmen.databinding.UserItemBinding
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo

class FollowersAdapter(private val listener: OnUserItemClickedListener) :
    ListAdapter<RemoteGithubUserInfo, FollowersAdapter.FollowersViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowersViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.bind(currentUser)
    }


    inner class FollowersViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(user: RemoteGithubUserInfo) {
            itemView.apply {
                binding.apply {
                    tvUsername.text = user.login
                    Glide.with(ivUserProfileImage.context)
                        .load(user.avatar_url)
                        .into(ivUserProfileImage)
                    ibShowProfile.setOnClickListener {
                        listener.onUserClicked(user)
                    }
                }

            }
        }
    }


    class DiffCallBack : DiffUtil.ItemCallback<RemoteGithubUserInfo>() {
        override fun areItemsTheSame(
            oldItem: RemoteGithubUserInfo,
            newItem: RemoteGithubUserInfo
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RemoteGithubUserInfo,
            newItem: RemoteGithubUserInfo
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnUserItemClickedListener {
        fun onUserClicked(user: RemoteGithubUserInfo)
    }
}