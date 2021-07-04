package com.app.simplegraph

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.simplegraph.data.DataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataSource: DataSource

    private val defaultFrom = LocalDate.now().minusDays(30).toDateFormat()
    private val defaultTo = LocalDate.now().toDateFormat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        date_from.setText(defaultFrom)
        date_to.setText(defaultTo)
        getAndSetData()
        btn_recalculate.setOnClickListener {
            val start =
                if (date_from.text.isNotEmpty()) date_from.text.toString() else defaultFrom
            val end = if (date_to.text.isNotEmpty()) date_to.text.toString() else defaultTo
            getAndSetData(start, end)
        }


    }

    private fun getAndSetData(
        start: String = defaultFrom,
        end: String = defaultTo,
    ) {
        lifecycleScope.launchWhenStarted {
            val data = dataSource.getBitcoinPrices(start, end).data?.data?.values?.flatten()
                ?.filter { it.contains(".") }?.mapNotNull { it.toFloatOrNull() } ?: emptyList()
            Timber.e(data.toString())
            histogram_view.setGraphArray(data)
        }
    }
}