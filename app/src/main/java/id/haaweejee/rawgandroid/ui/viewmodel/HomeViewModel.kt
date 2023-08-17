package id.haaweejee.rawgandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.domain.usecase.GetGameListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGameListUseCase: GetGameListUseCase,
) : ViewModel() {

    private val _gamesState: MutableStateFlow<PagingData<GameEntities>> = MutableStateFlow(
        PagingData.empty(),
    )
    val gamesState: StateFlow<PagingData<GameEntities>> get() = _gamesState

    fun getGames(query: String? = "") {
        viewModelScope.launch(Dispatchers.IO) {
            getGameListUseCase(query)
                .catch {
                    _gamesState.value = PagingData.empty()
                }.cachedIn(viewModelScope).collect {
                    _gamesState.value = it
                }
        }
    }
}
