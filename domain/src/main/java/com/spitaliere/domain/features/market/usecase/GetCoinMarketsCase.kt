package com.spitaliere.domain.features.market.usecase

import com.spitaliere.domain.features.market.entity.Market
import com.spitaliere.domain.features.market.repository.MarketRepository
import com.spitaliere.domain.platform.usecase.FlowableInputUseCase
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
class GetCoinMarketsCase(private val marketRepository: MarketRepository) : FlowableInputUseCase<String, Market> {

    override fun run(input: String): Flowable<Market> = marketRepository.getCoinMarkets(input)
}