package com.kaito.sogni.data.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

interface ISettingRepo {
    fun isShowOnboard(): Boolean

    fun setShowed()
}
class SettingRepoImpl(
    private val settings: Settings
): ISettingRepo {

    override fun isShowOnboard(): Boolean {
        return settings.getBoolean(KEY_SHOWED, false)
    }

    override fun setShowed() {
        settings[KEY_SHOWED] = true
    }

    companion object {
        private const val KEY_SHOWED = "is_showed"
    }
}