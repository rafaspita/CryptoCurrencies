package com.spitaliere.data.features.selection.repository

import com.spitaliere.data.api.request.CurrencyRequest
import com.spitaliere.data.features.currency.datasource.remote.CurrencyRemoteDataSourceImpl
import com.spitaliere.data.features.currency.entity.CurrencyCache
import com.spitaliere.data.features.selection.datasource.local.SelectionLocalDataSourceImpl
import com.spitaliere.data.features.selection.entity.mapToData
import com.spitaliere.data.features.selection.entity.mapToSaveData
import com.spitaliere.domain.features.selection.entity.Selection
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test

/**
 * Created by Rafael Spitaliere on 27/09/2019.
 */
class SelectionRepositoryImplTest {

    private val mockRepository = mockkClass(SelectionRepositoryImpl::class)
    private val mockLocalDataSource = mockkClass(SelectionLocalDataSourceImpl::class)
    private val mockCurrencyRemoteDataSourceImpl = mockkClass(CurrencyRemoteDataSourceImpl::class)
    private val listSelection = listOf(Selection(), Selection())
    private val listCurrencies = listOf(CurrencyCache(), CurrencyCache())


    @Test
    fun `test local dataSource and repository update selection`() {
        every { mockLocalDataSource.update(listSelection.mapToData()) }.returns(Completable.complete())
        every { mockRepository.update(listSelection) }.returns(Completable.complete())

        val testDataSource = mockLocalDataSource.update(listSelection.mapToData()).test()
        val testRepository = mockRepository.update(listSelection).test()

        verify { mockLocalDataSource.update(listSelection.mapToData()) }
        verify { mockRepository.update(listSelection) }

        testDataSource.assertComplete()
        testRepository.assertComplete()
    }

    @Test
    fun `test local dataSource and repository sync selection`() {
        every { mockCurrencyRemoteDataSourceImpl.fetchCurrencies(CurrencyRequest()) }.returns(Single.just(listCurrencies))
        every { mockLocalDataSource.save(listCurrencies.mapToSaveData()) }.returns(Completable.complete())
        every { mockRepository.syncCurrenciesForSelection() }.returns(Single.just(true))

        val testFetch = mockCurrencyRemoteDataSourceImpl.fetchCurrencies(CurrencyRequest()).test()
        val testSave = mockLocalDataSource.save(listCurrencies.mapToSaveData()).test()
        val testSync = mockRepository.syncCurrenciesForSelection().test()

        verify { mockCurrencyRemoteDataSourceImpl.fetchCurrencies(CurrencyRequest()) }
        verify { mockLocalDataSource.save(listCurrencies.mapToSaveData()) }
        verify { mockRepository.syncCurrenciesForSelection() }

        testFetch.assertValue(listCurrencies)
        testSave.assertComplete()
        testSync.assertValue(true)
    }

    @Test
    fun `test local dataSource and repository get all selection`() {
        every { mockLocalDataSource.getAll() }.returns(Single.just(listSelection))
        every { mockRepository.getAll() }.returns(Single.just(listSelection))

        val testDataSource  = mockLocalDataSource.getAll().test()
        val testRepository  = mockRepository.getAll().test()

        verify { mockLocalDataSource.getAll() }
        verify { mockRepository.getAll() }

        testDataSource.assertValue(listSelection)
        testRepository.assertValue(listSelection)
    }
}