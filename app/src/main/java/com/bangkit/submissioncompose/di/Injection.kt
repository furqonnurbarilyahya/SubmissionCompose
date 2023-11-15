package com.bangkit.submissioncompose.di

import com.bangkit.submissioncompose.data.FootballPlayerRepository

object Injection {
    fun providedRepository(): FootballPlayerRepository {
        return FootballPlayerRepository.getInstance()
    }
}