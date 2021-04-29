package com.yours.masterapp.ui.list.di

import com.yours.masterapp.ui.calculation.CalculationViewModel
import com.yours.masterapp.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val listModule = module {
    viewModel {
        ListViewModel(get())
    }
}
val calculationModule = module {
    viewModel {
        CalculationViewModel(get())
    }
}