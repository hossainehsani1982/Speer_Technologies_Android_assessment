package com.hossain_ehs.speertechnologiesandroidassessmen.data.mapper

import com.hossain_ehs.speertechnologiesandroidassessmen.data.local.entity.GitHubUserEntity
import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.dto.Item
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser

/*
*  this extension function is used to map the data from the database entity to
* data layer model we use this in cases where we want to have complex data types
* that cannot be stored in the database
*/

fun GitHubUserEntity.toRemoteUserDto() : RemoteGitHubUser {
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

/*
*  this extension function is used to map the data from data layer model  to
*  database entity
*/

fun Item.toUserEntity() : GitHubUserEntity {
    return GitHubUserEntity(
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