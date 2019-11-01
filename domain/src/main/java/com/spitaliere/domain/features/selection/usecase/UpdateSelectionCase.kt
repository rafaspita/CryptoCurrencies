package com.spitaliere.domain.features.selection.usecase

import com.spitaliere.domain.features.selection.entity.Selection
import com.spitaliere.domain.features.selection.repository.SelectionRepository
import com.spitaliere.domain.platform.usecase.CompletableInputUseCase
import io.reactivex.Completable

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
class UpdateSelectionCase(private val selectionRepository: SelectionRepository) : CompletableInputUseCase<List<Selection>> {

    override fun run(input: List<Selection>): Completable = selectionRepository.update(input)
}