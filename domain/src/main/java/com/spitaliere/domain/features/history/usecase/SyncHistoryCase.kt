package com.spitaliere.domain.features.history.usecase

import com.spitaliere.domain.features.history.repository.HistoryRepository
import com.spitaliere.domain.platform.usecase.CompletableUseCase
import io.reactivex.Completable

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
class SyncHistoryCase(private val historyRepository: HistoryRepository) : CompletableUseCase<String> {

    override fun run(input: String): Completable = historyRepository.updateCoinHistory(input, "m1")
}