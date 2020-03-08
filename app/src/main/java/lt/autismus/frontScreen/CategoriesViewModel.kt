package lt.autismus.frontScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lt.autismus.repository.CardsRepo
import lt.autismus.singleUnits.SingleCard
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    repository : CardsRepo
): ViewModel() {

    val cardsLive : LiveData<List<SingleCard>> = repository.getCardsLive()

}