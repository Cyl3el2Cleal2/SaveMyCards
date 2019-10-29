package buu.s59160937.savemycards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buu.s59160937.savemycards.databinding.FragmentListCardBinding


class ListCardFragment : Fragment() {
    var recyclerView: RecyclerView? = null


    var foods = arrayOf(
        "Minced pork omelette",
        "Stir fried pork with basil",
        "Papaya salad",
        ""
    )
    var arrImg = arrayOf<Int>(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentListCardBinding>(
            inflater, R.layout.fragment_list_card, container, false
        )

        binding.listCard.layoutManager = LinearLayoutManager(activity)

        val cardAdapter = context?.let { CardAdapter(foods, arrImg, it) }
        binding.listCard.adapter = cardAdapter


        return binding.root
    }


}
