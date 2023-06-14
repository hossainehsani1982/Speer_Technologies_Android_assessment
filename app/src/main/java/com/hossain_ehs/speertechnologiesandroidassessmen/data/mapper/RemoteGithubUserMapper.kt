package com.hossain_ehs.speertechnologiesandroidassessmen.data.mapper

import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.dto.Item
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser

/*
* extension method to map GitHubUser in data layer to RemoteGitHubUser in domain layer
* so it can be used in presentation layer.
*/

/*
* for separation of concerns, we assume that the presentation layers does not have
* direct access to data layer
*/

fun Item.toRemoteGitHubUser() : RemoteGitHubUser {
    return RemoteGitHubUser(
        avatar_url= avatar_url,
        events_url= events_url,
        followers_url= followers_url,
        following_url= following_url,
        gists_url= gists_url,
        gravatar_id= gravatar_id,
        html_url= html_url,
        id= id,
        login= login,
        node_id= node_id,
        organizations_url= organizations_url,
        received_events_url= received_events_url,
        repos_url= repos_url,
        score= score,
        site_admin= site_admin,
        starred_url= starred_url,
        subscriptions_url= subscriptions_url,
        type= type,
        url= url
    )
}