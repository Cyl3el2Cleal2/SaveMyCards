package buu.s59160937.savemycards

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buu.s59160937.savemycards.Database.CardDatabase
import buu.s59160937.savemycards.ViewModel.CardViewModel
import buu.s59160937.savemycards.databinding.FragmentListCardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ListCardFragment : Fragment() {
    var recyclerView: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentListCardBinding>(
            inflater, R.layout.fragment_list_card, container, false
        )

        binding.listCard.layoutManager = LinearLayoutManager(activity)

        val cardAdapter = context?.let { CardAdapter(it) }



        val application = requireNotNull(this.activity).application
        val dataSource = CardDatabase.getInstance(application).cardDatabaseDao
//        val viewModel = CardViewModel(dataSource, application)
//
//
//        val CardViewModel =
//            ViewModelProviders.of(
//                this).get(CardViewModel::class.java)

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


}
