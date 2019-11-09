package buu.s59160937.savemycards

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*
import kotlinx.android.synthetic.main.model.view.*
import androidx.navigation.findNavController
import buu.s59160937.savemycards.Database.Card
import buu.s59160937.savemycards.R.drawable.cardbackground
import buu.s59160937.savemycards.ViewModel.CardViewModel
import buu.s59160937.savemycards.ViewModel.DataCard


class CardAdapter(val viewModel: CardViewModel,MyFragment: Fragment) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    var data = ArrayList<Card>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var myFragment = MyFragment


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Log.i("CardAdapter",data.toString())
        if(data.size > 0){
            val card = Card(holder.itemId, holder.CardName.text.toString(), holder.Number.text.toString(), holder.Expire.text.toString(), holder.CVV.text.toString())


            holder.itemView.setOnClickListener {
                viewModel.viewCard(myFragment, card)
            }
        }

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
        var CVV = view.cardCVV
        var Expire = view.cardExpire

        fun bind(item: Card) {
            Thumbnail.setImageResource(cardbackground)
            CardName.text = item.name
            Number.text = item.number
            CVV.text = item.CVV
            Expire.text = item.expire


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

