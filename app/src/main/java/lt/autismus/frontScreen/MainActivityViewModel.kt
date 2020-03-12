package lt.autismus.frontScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.autismus.repository.CardsRepo
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val repository : CardsRepo
) : ViewModel() {
    fun updateCards(){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCards()
        }
    }
    fun updateCategories(){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCategories()
        }
    }
}