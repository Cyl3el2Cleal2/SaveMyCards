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
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
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
    lateinit var cardAdapter: CardAdapter
    lateinit var binding: FragmentListCardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentListCardBinding>(
            inflater, R.layout.fragment_list_card, container, false
        )

        binding.listCard.layoutManager = LinearLayoutManager(activity)





        val application = requireNotNull(this.activity).application
        val dataSource = CardDatabase.getInstance(application).cardDatabaseDao
        val viewModelFactory = CardViewModelFactory(dataSource, application)

        ViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(CardViewModel::class.java)




        cardAdapter = CardAdapter(ViewModel, this)


        ViewModel.cards.observe(this, Observer { cards ->
            cardAdapter?.data = cards as ArrayList<Card>
        })

        binding.setLifecycleOwner(this)

//        GlobalScope.launch {
//            var testData = dataSource.getAllcards()
//            for (i in arrayOf(testData)){
//                Log.i("ListCardFragment", i.toString())
//            }
//
//        }


        binding.listCard.adapter = cardAdapter
        binding.aboutButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_listCardFragment_to_about)

        }

        setRecyclerViewItemTouchListener()

        return binding.root
    }

    private fun setRecyclerViewItemTouchListener() {

        //1
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                //2
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                //3
                if (cardAdapter.data.size > 0){
                    val position = viewHolder.adapterPosition
                    Toast.makeText(context, cardAdapter.data[position].name + " removed", Toast.LENGTH_SHORT).show()
                    ViewModel.deleteCard(cardAdapter.data[position].cardId)
                }


            }
        }

        //4
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.listCard)
    }



}
