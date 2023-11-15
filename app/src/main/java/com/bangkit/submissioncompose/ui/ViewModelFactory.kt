package com.bangkit.submissioncompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.submissioncompose.FootballPlayerViewModel
import com.bangkit.submissioncompose.data.FootballPlayerRepository

class ViewModelFactory(private val footballPlayerRepository: FootballPlayerRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FootballPlayerViewModel::class.java)) {
            return FootballPlayerViewModel(footballPlayerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}