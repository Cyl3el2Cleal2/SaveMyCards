package buu.s59160937.savemycards.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buu.s59160937.savemycards.Database.Card
import buu.s59160937.savemycards.Database.CardDatabaseDao
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

//        cards.add(DataCard("Test Card","9999-9999-9999","99/99","555"))
//        cards.add(DataCard("","","",""))
        initializeCards()
        addCard()
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

    fun addCard(){
        uiScope.launch {
            val card = Card()
            insert(card)

        }
    }

    fun deleteCard(){

    }

}