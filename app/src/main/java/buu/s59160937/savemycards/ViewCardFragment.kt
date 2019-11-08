package buu.s59160937.savemycards


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import buu.s59160937.savemycards.databinding.FragmentViewCardBinding


class ViewCardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  = DataBindingUtil.inflate<FragmentViewCardBinding>(
            inflater,
            R.layout.fragment_view_card,
            container,
            false
        )

        binding.Name.text = arguments?.get("name").toString()
        binding.cardNumber.setText(arguments?.get("number").toString())
        binding.CVV.setText(arguments?.get("cvv").toString())
        binding.expireDate.setText(arguments?.get("expire").toString())


//        Log.i("ViewCardFragment", name.toString())



        return binding.root
    }


}
