package com.yours.masterapp.domain

import org.koin.dsl.module

val UseCaseModules = module {
    single<CurrencyUseCase> {
        CurrencyUseCaseImp(get())
    }
}