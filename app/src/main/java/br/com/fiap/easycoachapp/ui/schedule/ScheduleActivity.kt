package br.com.fiap.easycoachapp.ui.schedule

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.easycoachapp.R
import br.com.fiap.easycoachapp.databinding.ActivityScheduleBinding
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.ui.schedule.adapter.ScheduledSessionsAdapter
import br.com.fiap.easycoachapp.ui.session.register.NewSessionActivity
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleContract
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleViewModel
import kotlinx.android.synthetic.main.activity_schedule.*
import org.koin.core.parameter.parametersOf
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ScheduleActivity : AppCompatActivity(), ScheduleContract {
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
        cvSchedule.setOnClickListener {
            viewModel.onSchedulePressed()
        }
    }

    private fun setupViewModel() {
        viewModel.sessions.observe(this, {
            adapter.list = it
            adapter.notifyDataSetChanged()
        })
    }

    override fun scheduleNewSession() {
        startActivity(Intent(this, NewSessionActivity::class.java))
    }

    override fun goToScheduledSessionDetails(session: SessionEntity) {
        Toast.makeText(this, "Em breve...", Toast.LENGTH_SHORT).show()
    }

    override fun requestConfirmation(message: String): Boolean {
        var confirmation = false

        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Sim") { _, _ -> confirmation = true}
                setNegativeButton("Não") { _, _ -> confirmation = false}
            }
            builder.setMessage(message)

            // Create the AlertDialog
            builder.create()
        }

        alertDialog?.show()
        return confirmation
    }

    override fun showErrorMessage() {
        Toast.makeText(this, "Erro ao carregar o calendário de sessões", Toast.LENGTH_SHORT).show()
    }
}