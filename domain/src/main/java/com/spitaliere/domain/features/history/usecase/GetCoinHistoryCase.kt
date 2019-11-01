package com.spitaliere.domain.features.history.usecase

import com.spitaliere.domain.features.history.entity.History
import com.spitaliere.domain.features.history.repository.HistoryRepository
import com.spitaliere.domain.platform.usecase.FlowableInputUseCase
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
class GetCoinHistoryCase(private val historyRepository: HistoryRepository) : FlowableInputUseCase<String, History> {

    override fun run(input: String): Flowable<History> = historyRepository.getCoinHistory(input)
}