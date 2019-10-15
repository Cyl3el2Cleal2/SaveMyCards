package buu.s59160937.savemycards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import buu.s59160937.savemycards.databinding.FragmentListCardBinding


class ListCardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentListCardBinding>(
            inflater, R.layout.fragment_list_card, container, false
        )
        return binding.root
    }

}
