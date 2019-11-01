package com.spitaliere.domain.features.selection.usecase

import com.spitaliere.domain.features.selection.repository.SelectionRepository
import com.spitaliere.domain.platform.usecase.SingleUseCase
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
class SyncCurrenciesForSelectionCase(private val selectionRepository: SelectionRepository) : SingleUseCase<Boolean> {

    override fun run(): Single<Boolean> = selectionRepository.syncCurrenciesForSelection()
}