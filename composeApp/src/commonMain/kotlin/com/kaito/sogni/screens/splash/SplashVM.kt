package com.kaito.sogni.screens.splash

import androidx.lifecycle.ViewModel
import com.kaito.sogni.data.repository.ISettingRepo

class SplashVM(
    private val repo: ISettingRepo
): ViewModel() {

    fun isShowed(): Boolean {
        val isShowed = repo.isShowOnboard()
        if (!isShowed) {
            repo.setShowed()
        }
        return isShowed
    }
}