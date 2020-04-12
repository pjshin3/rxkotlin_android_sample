package com.example.mygooglebook.di

import com.example.mygooglebook.List.ListViewModel
import com.example.mygooglebook.Seach.SeachViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewmodelModule = module {
    viewModel { SeachViewModel(get()) }
    viewModel { ListViewModel(get()) }
}