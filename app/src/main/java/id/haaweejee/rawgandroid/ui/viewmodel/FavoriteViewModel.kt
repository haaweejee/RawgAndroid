package id.haaweejee.rawgandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.domain.usecase.GetFavoriteGameListUseCase
import id.haaweejee.rawgandroid.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteGameUseCase: GetFavoriteGameListUseCase,
): ViewModel() {

    private val _favoriteGamesState: MutableStateFlow<UiState<List<GameEntities>>> = MutableStateFlow(
        UiState.Loading,
    )

    val favoriteGamesState: StateFlow<UiState<List<GameEntities>>> get() = _favoriteGamesState

    fun getFavoriteGamesList() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteGameUseCase()
                .catch {
                    _favoriteGamesState.value = UiState.Error(it.message.toString())
                }.collect {
                    _favoriteGamesState.value = UiState.Success(it)
                }
        }
    }
}