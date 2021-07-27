package br.com.fiap.easycoachapp.ui.schedule.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.easycoachapp.R
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.session_card.view.*

class ScheduledSessionsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(viewModel: ScheduleViewModel, session: SessionEntity) {
        Picasso.get().load(R.drawable.coachee).into(itemView.ivCoacheePhoto)
        itemView.tvScheduleTime.text = session.scheduledDateTime.toString()
        Picasso.get().load(R.drawable.amor).into(itemView.ivSpecialty)
        itemView.tvCoacheeName.text = session.coachee.name
        itemView.btEdit.setOnClickListener{
            viewModel.onEditScheduledSessionPressed(session)
        }
        itemView.btDelete.setOnClickListener{
            viewModel.onDeleteScheduledSessionPressed(session)
        }
    }
}