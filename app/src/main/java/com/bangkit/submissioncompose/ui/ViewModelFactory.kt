package com.bangkit.submissioncompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.submissioncompose.data.FootballPlayerRepository
import com.bangkit.submissioncompose.ui.screen.detail.DetailPlayerViewModel
import com.bangkit.submissioncompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val footballPlayerRepository: FootballPlayerRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(footballPlayerRepository) as T
        } else if (modelClass.isAssignableFrom(DetailPlayerViewModel::class.java)) {
            return DetailPlayerViewModel(footballPlayerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}