package com.hossain_ehs.speertechnologiesandroidassessmen.data.mapper

import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.dto.UserDto
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo

fun UserDto.toRemoteUserInfo(): RemoteGithubUserInfo {
    return RemoteGithubUserInfo(
        avatar_url = avatar_url,
        bio = bio ?: "",
        blog = blog ?: "",
        company = company?: "",
        created_at = created_at?: "",
        email = email?: "",
        events_url = events_url?: "",
        followers = followers?: 0,
        followers_url = followers_url?: "",
        following = following?: 0,
        following_url = following_url?: "",
        gists_url = gists_url?: "",
        gravatar_id = gravatar_id?: "",
        hireable = hireable?: "",
        html_url = html_url?: "",
        id = id?: 0,
        location = location?: "",
        login = login?: "",
        name = name?: "",
        node_id = node_id?: "",
        organizations_url = organizations_url?: "",
        public_gists = public_gists?: 0,
        public_repos = public_repos?: 0,
        received_events_url = received_events_url?: "",
        repos_url = repos_url?: "",
        site_admin = site_admin?: false,
        starred_url = starred_url?: "",
        subscriptions_url = subscriptions_url?: "",
        twitter_username = twitter_username?: "",
        type = type?: "",
        updated_at = updated_at?: "",
        url = url?: ""
    )
}
