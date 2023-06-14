package com.hossain_ehs.speertechnologiesandroidassessmen.data.util

import android.content.Context

/*
 this class is used to access string resources in view-model
*/

sealed class UiText{
    data class DynamicString(val text : String) : UiText()
    data class ResourceString(var resId : Int) : UiText()

    fun asString(context : Context) : String{
        return when(this){
            is DynamicString -> text
            is ResourceString -> context.getString(resId)
        }
    }
}
