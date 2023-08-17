package id.haaweejee.rawgandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.rawgandroid.domain.entities.DetailGameEntities
import id.haaweejee.rawgandroid.domain.entities.FavoriteEntities
import id.haaweejee.rawgandroid.domain.entities.GameEntities
import id.haaweejee.rawgandroid.domain.usecase.FavoriteGameUseCase
import id.haaweejee.rawgandroid.domain.usecase.GetDetailGameUseCase
import id.haaweejee.rawgandroid.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailGameUseCase: GetDetailGameUseCase,
    private val favoriteGameUseCase: FavoriteGameUseCase
) : ViewModel() {

    private val _detailState: MutableStateFlow<UiState<DetailGameEntities>> = MutableStateFlow(
        UiState.Loading,
    )

    val detailState: StateFlow<UiState<DetailGameEntities>> get() = _detailState

    private val _favoriteState: MutableStateFlow<UiState<FavoriteEntities>> = MutableStateFlow(UiState.Loading)
    val favoriteState: StateFlow<UiState<FavoriteEntities>>
        get() =
            _favoriteState

    fun getDetailGame(gamesId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getDetailGameUseCase(gamesId)
                .catch {
                    _detailState.value = UiState.Error(it.message.toString())
                }.collect {
                    _detailState.value = UiState.Success(it)
                }
        }
    }

    fun insertGameToFavorite(game: GameEntities) =
        viewModelScope.launch(Dispatchers.IO) {
            favoriteGameUseCase(game).catch {
                _favoriteState.value = UiState.Error(it.message.toString())
            }.collect {
                _favoriteState.value = UiState.Success(it)
            }
        }
}
