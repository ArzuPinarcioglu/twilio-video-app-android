package com.twilio.video.app.ui.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RoomViewModel(private val roomManager: RoomManager) : ViewModel() {

    // TODO Build ViewStates for UI to consume instead of just passing events
    val roomEvents: LiveData<RoomEvent?> = roomManager.viewEvents

    // TODO Add single point of entry function from UI, something like "processInput"

    fun connectToRoom(
        identity: String,
        roomName: String,
        isNetworkQualityEnabled: Boolean
    ) =
        viewModelScope.launch {
            roomManager.connectToRoom(
                    identity,
                    roomName,
                    isNetworkQualityEnabled)
        }

    fun disconnect() {
        roomManager.disconnect()
    }

    class RoomViewModelFactory(private val roomManager: RoomManager) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RoomViewModel(roomManager) as T
        }
    }
}
