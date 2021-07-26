package br.com.fiap.easycoachapp.ui.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.easycoachapp.R
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleViewModel

class ScheduledSessionsAdapter(
    val viewModel: ScheduleViewModel
) : RecyclerView.Adapter<ScheduledSessionsViewHolder>() {
    var list: ArrayList<SessionEntity> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduledSessionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.session_card, parent, false)
        return ScheduledSessionsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ScheduledSessionsViewHolder, position: Int) {
        holder.bind(viewModel, list[position])
    }
}