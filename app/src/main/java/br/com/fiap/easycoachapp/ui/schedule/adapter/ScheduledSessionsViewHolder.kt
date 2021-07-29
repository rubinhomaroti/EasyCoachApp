package br.com.fiap.easycoachapp.ui.schedule.adapter

import android.app.AlertDialog
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.easycoachapp.R
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.session_card.view.*
import java.text.SimpleDateFormat

class ScheduledSessionsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(viewModel: ScheduleViewModel, session: SessionEntity) {
        val formatter = SimpleDateFormat("HH:mm")
        Picasso.get().load(R.drawable.coachee).into(itemView.ivCoacheePhoto)
        itemView.tvScheduleTime.text = formatter.format(session.scheduledDateTime)
        Picasso.get().load(R.drawable.amor).into(itemView.ivSpecialty)
        itemView.tvCoacheeName.text = "${session.coachee?.name}\n${session.title}"
        itemView.btEdit.setOnClickListener{
            viewModel.onEditScheduledSessionPressed(session)
        }
        itemView.btDelete.setOnClickListener{
            AlertDialog.Builder(itemView.context)
                .setMessage("Deseja realmente apagar a sessão?")
                .setPositiveButton("Sim") { _, _ -> viewModel.onDeleteScheduledSessionPressed(session)}
                .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}