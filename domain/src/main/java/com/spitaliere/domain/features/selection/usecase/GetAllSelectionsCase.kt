package com.spitaliere.domain.features.selection.usecase

import com.spitaliere.domain.features.selection.entity.Selection
import com.spitaliere.domain.features.selection.repository.SelectionRepository
import com.spitaliere.domain.platform.usecase.SingleUseCase
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
class GetAllSelectionsCase(private val selectionRepository: SelectionRepository) : SingleUseCase<List<Selection>> {

    override fun run(): Single<List<Selection>> = selectionRepository.getAll()
}