package buu.s59160937.savemycards

import android.os.Bundle
import android.provider.Settings
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buu.s59160937.savemycards.Database.Card
import buu.s59160937.savemycards.Database.CardDatabase
import buu.s59160937.savemycards.ViewModel.CardViewModel
import buu.s59160937.savemycards.ViewModel.CardViewModelFactory
import buu.s59160937.savemycards.databinding.FragmentListCardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ListCardFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    lateinit var ViewModel: CardViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentListCardBinding>(
            inflater, R.layout.fragment_list_card, container, false
        )

        binding.listCard.layoutManager = LinearLayoutManager(activity)





        val application = requireNotNull(this.activity).application
        val dataSource = CardDatabase.getInstance(application).cardDatabaseDao
        val viewModelFactory = CardViewModelFactory(dataSource, application)

        ViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(CardViewModel::class.java)


        // Get a reference to the ViewModel associated with this fragment.

        val cardAdapter = CardAdapter(ViewModel, this)
        //        val cardAdapter = context?.let { CardAdapter(it) }

        ViewModel.cards.observe(this, Observer { cards ->
            cardAdapter?.data = cards as ArrayList<Card>
        })

        binding.setLifecycleOwner(this)

        GlobalScope.launch {
            var testData = dataSource.getAllcards()
            for (i in arrayOf(testData)){
                Log.i("ListCardFragment", i.toString())
            }

        }

        //Fragment create dataSource
        //ViewModel(dataSource)
        //Adapter call ViewModel LiveData

//        binding.CardViewModel = CardViewModel
        binding.listCard.adapter = cardAdapter


        return binding.root
    }

    private fun goViewCard(card: Card) {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        ViewModel.viewCard(this, card)
//        viewModel.onGameFinishComplete()
    }


}
