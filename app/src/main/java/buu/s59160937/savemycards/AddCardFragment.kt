package buu.s59160937.savemycards


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import buu.s59160937.savemycards.Database.Card
import buu.s59160937.savemycards.Database.CardDatabase
import buu.s59160937.savemycards.ViewModel.CardViewModel
import buu.s59160937.savemycards.ViewModel.CardViewModelFactory
import buu.s59160937.savemycards.databinding.FragmentAboutBinding
import buu.s59160937.savemycards.databinding.FragmentAddCardBinding
import buu.s59160937.savemycards.databinding.FragmentListCardBinding

/**
 * A simple [Fragment] subclass.
 */
class AddCardFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {

        (activity as AppCompatActivity)?.supportActionBar?.title = "New Card"


        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentAddCardBinding>(
            inflater, R.layout.fragment_add_card, container, false
        )



        val application = requireNotNull(this.activity).application
        val dataSource = CardDatabase.getInstance(application).cardDatabaseDao
        val viewModelFactory = CardViewModelFactory(dataSource, application)

        val ViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(CardViewModel::class.java)


        binding.button.setOnClickListener { v ->

            val card = Card(0,binding.cardName.text.toString(), binding.cardNumber.text.toString(), binding.CVV.text.toString(), binding.expireDate.text.toString())
            if(card.name.isNotEmpty() && card.number.isNotEmpty() && card.CVV.isNotEmpty() && card.expire.isNotEmpty()){
                ViewModel.addCard(card)

                    //Hide keyboard
                    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)

                view?.findNavController()?.popBackStack()
            }else{
                Toast.makeText(context, "Please Enter all Information", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }


}
