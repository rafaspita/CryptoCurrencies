package com.spitaliere.domain.features.selection.di

import com.spitaliere.domain.features.selection.usecase.GetAllSelectionsCase
import com.spitaliere.domain.features.selection.usecase.SyncCurrenciesForSelectionCase
import com.spitaliere.domain.features.selection.usecase.UpdateSelectionCase
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
object SelectionDomainModule {

    fun getModule() = module {
        factory{ GetAllSelectionsCase(selectionRepository = get()) }
        factory{ SyncCurrenciesForSelectionCase(selectionRepository = get()) }
        factory{ UpdateSelectionCase(selectionRepository = get()) }
    }
}