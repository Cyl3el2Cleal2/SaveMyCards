package buu.s59160937.savemycards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.s59160937.savemycards.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login, container, false
        )

        binding.playButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_listCardFragment)
        }

        return binding.root
    }


}
