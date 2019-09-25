package com.spitaliere.domain.features.history.di

import com.spitaliere.domain.features.history.usecase.GetCoinHistoryCase
import com.spitaliere.domain.features.history.usecase.SyncHistoryCase
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
object HistoryDomainModule {

    fun getModule() = module {
        factory{ GetCoinHistoryCase(historyRepository = get()) }
        factory{ SyncHistoryCase(historyRepository = get()) }
    }
}