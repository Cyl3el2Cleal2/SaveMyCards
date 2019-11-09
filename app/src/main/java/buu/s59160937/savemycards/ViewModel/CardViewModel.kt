package buu.s59160937.savemycards.ViewModel

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import buu.s59160937.savemycards.Database.Card
import buu.s59160937.savemycards.Database.CardDatabaseDao
import buu.s59160937.savemycards.ListCardFragmentDirections
import kotlinx.coroutines.*

data class DataCard(val name : String, val number: String,val expire: String,val CVV: String){


}
class CardViewModel(dataSource: CardDatabaseDao, application: Application) : ViewModel() {


    val database = dataSource


    private val viewModelJob = Job()


    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)



    val cards = database.getAllcards()
    init {

        Log.i("CardViewModel", "init data")

        initializeCards()
//        addCard()
        Log.i("CardViewModel", cards.toString())
    }

    private fun initializeCards() {
        uiScope.launch {
//            tonight.value = getCardFromDatabase()
        }
    }


    private suspend fun insert(card: Card) {
        withContext(Dispatchers.IO) {
            database.insert(card)
        }
    }

    private suspend fun remove(id: Long) {
        withContext(Dispatchers.IO) {
            database.remove(id)
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun addCard(card: Card){
        uiScope.launch {
            insert(card)

        }
    }

    fun deleteCard(id: Long){
        uiScope.launch {
            clear()
        }
    }

    fun clearCard(){
        uiScope.launch {
            clear()
        }
    }

    fun viewCard(fragment: Fragment, card: Card){
        val action = ListCardFragmentDirections.actionListCardFragmentToViewCardFragment(card.name, card.number, card.expire, card.CVV)
        NavHostFragment.findNavController(fragment).navigate(action)
    }

}