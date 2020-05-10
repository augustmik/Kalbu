package lt.kalbu.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lt.kalbu.singleUnits.SingleCard

class CardSelection {
    private val _nowCard : MutableLiveData<SingleCard> = MutableLiveData()
    private val _thenCard : MutableLiveData<SingleCard> = MutableLiveData()

    val nowCard : LiveData<SingleCard> = _nowCard
    val thenCard : LiveData<SingleCard> = _thenCard

    fun updateNowCard(card: SingleCard){
        _nowCard.postValue(card)
    }

    fun updateThenCard(card: SingleCard){
        _thenCard.postValue(card)
    }

//    fun resetCardSelector(){
//        _nowCard.postValue(null)
//        _thenCard.postValue(null)
//    }

    fun resetNowCardSelector(){
        _nowCard.postValue(null)
    }

    fun resetThenCardSelector(){
        _thenCard.postValue(null)
    }
}