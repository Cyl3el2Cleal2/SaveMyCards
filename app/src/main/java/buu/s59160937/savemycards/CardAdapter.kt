package buu.s59160937.savemycards

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*
import kotlinx.android.synthetic.main.model.view.*
import androidx.navigation.findNavController
import buu.s59160937.savemycards.Database.Card
import buu.s59160937.savemycards.R.drawable.cardbackground
import buu.s59160937.savemycards.ViewModel.CardViewModel
import buu.s59160937.savemycards.ViewModel.DataCard


class CardAdapter(val context: Context) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    var data = ArrayList<Card>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Log.i("CardAdapter",data.toString())
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var Number = view.cardNumber
        var Thumbnail = view.cardBackground
        var CardName = view.cardName

        init {
            view.setOnClickListener { view: View ->
                if (view.cardName?.text == "") {
                    view.findNavController()
                        .navigate(R.id.action_listCardFragment_to_addCardFragment)
                } else {
                    view.findNavController()
                        .navigate(R.id.action_listCardFragment_to_viewCardFragment)
                }

            }


        }

        fun bind(item: Card) {
            Thumbnail.setImageResource(cardbackground)
            CardName.text = item.name
            Number.text = item.number


        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.card, parent, false)

                return ViewHolder(view)
            }
        }
    }
}

