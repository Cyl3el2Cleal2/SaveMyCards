package buu.s59160937.savemycards

import android.os.Bundle
import android.provider.Settings
import android.text.Layout
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    lateinit var ViewModel: CardViewModel
    lateinit var cardAdapter: CardAdapter
    lateinit var binding: FragmentListCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.setMenuVisibility(true)
        (activity as AppCompatActivity)?.supportActionBar?.title = "My Cards"
        (activity as AppCompatActivity)?.supportActionBar?.show()
        setHasOptionsMenu(true)

        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.setMenuVisibility(true)
        (activity as AppCompatActivity)?.supportActionBar?.title = "My Cards"
        (activity as AppCompatActivity)?.supportActionBar?.show()
        setHasOptionsMenu(true)
        super.onResume()
        Log.i("ListCardFragment", "Resume and Set Title")
    }

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
        binding.listCard.adapter = cardAdapter

        setRecyclerViewItemTouchListener()



        return binding.root
    }

    //inflate the menu
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu, menu)


        super.onCreateOptionsMenu(menu, inflater)
    }

    //handle item clicks of menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //get item id to handle item clicks
        val id = item!!.itemId
        //handle item clicks
        if (id == R.id.action_new_card){
            view?.findNavController()?.navigate(R.id.action_listCardFragment_to_addCardFragment)
            Toast.makeText(activity, "Enter Your Card Information", Toast.LENGTH_SHORT).show()
        }else if (id == R.id.action_about){
            view?.findNavController()?.navigate(R.id.action_listCardFragment_to_about)
        }

        return super.onOptionsItemSelected(item)
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
