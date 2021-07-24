package br.com.fiap.easycoachapp.ui.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.easycoachapp.R
import br.com.fiap.easycoachapp.databinding.ActivityScheduleBinding
import br.com.fiap.easycoachapp.ui.schedule.adapter.ScheduledSessionsAdapter
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleViewModel
import kotlinx.android.synthetic.main.activity_schedule.*
import org.koin.core.parameter.parametersOf
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ScheduleActivity : AppCompatActivity() {
    private lateinit var adapter: ScheduledSessionsAdapter
    private lateinit var binding: ActivityScheduleBinding
    private val viewModel: ScheduleViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule)
        binding.viewModel = viewModel
        viewModel.onCreate()
        adapter = ScheduledSessionsAdapter(viewModel)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        rvSessions.adapter = adapter
        rvSessions.layoutManager = LinearLayoutManager(this)
        cvSchedule.setOnDateChangeListener { cv, p1, p2, p3 ->
            viewModel.onScheduleDateChanged(Date(cv.date))
        }
    }

    private fun setupViewModel() {
        viewModel.sessions.observe(this, Observer {
            adapter.list = it
            adapter.notifyDataSetChanged()
        })
    }
}