package com.spitaliere.domain.features.currency.usecase

import com.spitaliere.domain.features.currency.entity.Currency
import com.spitaliere.domain.features.currency.repository.CurrencyRepository
import com.spitaliere.domain.platform.usecase.FlowableUseCase
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
class GetAllCurrenciesCase (private val currencyRepository: CurrencyRepository) : FlowableUseCase<List<Currency>> {

    override fun run(): Flowable<List<Currency>> = currencyRepository.getAllCurrencies()
}