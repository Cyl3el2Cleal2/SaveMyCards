package buu.s59160937.savemycards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*
import kotlinx.android.synthetic.main.model.view.*
import androidx.navigation.findNavController


class CardAdapter(val items: Array<String>, val imageId: Array<Int>, val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0?.getNameTxt?.text = items.get(p1)
//        p0?.getThumbnail?.setImageResource(imageId.get(p1))
        p0?.getCardName?.text = items.get(p1)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card, p0, false))
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val getNameTxt = view.nameTxt
    val getThumbnail = view.thumbnail
    val getCardName = view.cardName

    init {
        view.setOnClickListener{
                view: View ->
            if(view.cardName?.text == ""){
             view.findNavController().navigate(R.id.action_listCardFragment_to_addCardFragment)
            }else{
                view.findNavController().navigate(R.id.action_listCardFragment_to_viewCardFragment)
            }

        }


    }
}