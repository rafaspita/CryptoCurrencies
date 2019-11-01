package com.spitaliere.cryptocurrencies.ui.currency.fragments.history

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.view.View
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.domain.features.history.entity.HistoryData
import java.util.*

/**
 * Created by Rafael Spitaliere on 20/10/2019.
 **/
object ChartAdapter {

    fun buildLineChart(lineChart: LineChart, listChart: List<HistoryData>, context: Context){
        lineChart.invalidate()
        lineChart.clear()

        var index = 0f
        val entries = listChart.map { Entry(index++, it.priceUsd) }.toList()

        val set1 = LineDataSet(entries, "DataSet 1")

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1) // add the data sets

        with(set1){
            setDrawIcons(false)

            // draw dashed line
            enableDashedLine(100f, 5f, 0f)

            // black lines and points
            color = Color.BLACK
            setCircleColor(Color.BLACK)
            setDrawCircleHole(false)

            // line thickness and point size
            lineWidth = 1f
            circleRadius = 1f

            // draw points as solid circles
            setDrawCircleHole(false)

            // customize legend entry
            formLineWidth = 1f
            formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            formSize = 15f

            // text size of values
            valueTextSize = 9f

            // draw selection line as dashed
            enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            setDrawFilled(true)

            fillDrawable = ContextCompat.getDrawable(context, R.drawable.fade_yellow)
        }

        // create a data object with the data sets
        val data = LineData(dataSets)

        with(lineChart.xAxis){
            valueFormatter = getIndexAxisValueFormatter(listChart)
            enableGridDashedLine(10f, 10f, 0f)
            setLabelCount(5, true)
        }

        with(lineChart.axisRight){
            isEnabled = false
        }

        with(lineChart){
            legend.isEnabled = false
            description.isEnabled = false
            setVisibleXRangeMaximum(3f)
            this.data = data
            invalidate()
            lineChart.visibility = View.VISIBLE
            animateX(2000)
        }
    }

    private fun getIndexAxisValueFormatter(listChart: List<HistoryData>): IndexAxisValueFormatter {
        return object : IndexAxisValueFormatter() {
            override fun getFormattedValue(value: Float): String = listChart[value.toInt()].time

        }
    }
}