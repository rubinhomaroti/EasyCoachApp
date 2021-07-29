package br.com.fiap.easycoachapp.ui.session.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.fiap.easycoachapp.R
import br.com.fiap.easycoachapp.databinding.ActivityNewSessionBinding
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.SpecialtyEntity
import br.com.fiap.easycoachapp.ui.extensions.TextViewExtensions.Companion.transformIntoDatePicker
import br.com.fiap.easycoachapp.ui.schedule.ScheduleActivity
import br.com.fiap.easycoachapp.viewModel.session.create.NewSessionContract
import br.com.fiap.easycoachapp.viewModel.session.create.NewSessionViewModel
import kotlinx.android.synthetic.main.activity_new_session.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*


class NewSessionActivity : AppCompatActivity(), NewSessionContract {
    private lateinit var binding: ActivityNewSessionBinding
    private lateinit var coacheesAdapter: ArrayAdapter<CoacheeEntity>
    private lateinit var specialtiesAdapter: ArrayAdapter<SpecialtyEntity>
    private val viewModel: NewSessionViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_session)
        binding.viewModel = viewModel
        viewModel.onCreate()
        setupView()
    }

    private fun setupView() {
        setupScheduleEditText()
        setupCoacheeSpinner()
        setupSpecialtiesSpinner()
        setupSessionNumberSpinner()

        btSendInvite.setOnClickListener { viewModel.onCreateSessionPressed() }
    }

    private fun setupScheduleEditText() {
        val pattern = "dd/MM/yyyy HH:mm"
        val formatter = SimpleDateFormat(pattern)

        etSessionScheduledDate.transformIntoDatePicker(this)
        etSessionScheduledDate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

                viewModel.scheduledDateTime =
                    formatter.parse(etSessionScheduledDate.text.toString())
            }
        })
        etSessionScheduledDate.setText(formatter.format(Date()))
    }

    private fun setupCoacheeSpinner() {
        coacheesAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, viewModel.coachees)
        coacheesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCoachee.adapter = coacheesAdapter
        spCoachee.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectedCoachee = viewModel.coachees[position]
            }
        }
    }

    private fun setupSpecialtiesSpinner() {
        specialtiesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, viewModel.specialties)
        specialtiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSpecialities.adapter = specialtiesAdapter
        spSpecialities.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.selectedSpecialty = viewModel.specialties[position]
            }
        }
    }

    private fun setupSessionNumberSpinner() {
        val sessionsNumberAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        )
        sessionsNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSessionNumber.adapter = sessionsNumberAdapter
    }

    override fun goToHomeActivity() {
        startActivity(Intent(this, ScheduleActivity::class.java))
    }

    override fun onDataLoaded() {
        coacheesAdapter.notifyDataSetChanged()
        specialtiesAdapter.notifyDataSetChanged()
    }

    override fun showErrorMessage() {
        Toast.makeText(this, "Erro ao criar a sessão", Toast.LENGTH_SHORT).show()
    }
}