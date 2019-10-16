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
        "Boiled egg, Bael leaves",
        "Pad Thai",
        "Korat Noodle",
        "Shrimp Salad",
        "Boiled pork noodles",
        "Steamed sea",
        "Steamed rices",
        "Pork Belly",
        "Liang Vegetable Fried Eggs"
    )
    var arrImg = arrayOf<Int>(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
        R.drawable.image9,
        R.drawable.image10,
        R.drawable.image11,
        R.drawable.image12
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
