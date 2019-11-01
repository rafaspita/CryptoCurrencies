package com.spitaliere.domain.features.currency.usecase

import com.spitaliere.domain.features.currency.repository.CurrencyRepository
import com.spitaliere.domain.platform.usecase.CompletableInputUseCase
import io.reactivex.Completable

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
class SyncCurrenciesCase(private val currencyRepository: CurrencyRepository) : CompletableInputUseCase<Boolean> {

    override fun run(input: Boolean): Completable = currencyRepository.updateCurrencies(input)
}