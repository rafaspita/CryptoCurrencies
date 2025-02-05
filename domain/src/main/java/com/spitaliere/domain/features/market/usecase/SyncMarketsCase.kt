package com.spitaliere.domain.features.market.usecase

import com.spitaliere.domain.features.market.repository.MarketRepository
import com.spitaliere.domain.platform.usecase.CompletableInputUseCase
import io.reactivex.Completable

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
class SyncMarketsCase(private val marketRepository: MarketRepository) : CompletableInputUseCase<String> {

    override fun run(input: String): Completable = marketRepository.updateCoinMarkets(input)
}