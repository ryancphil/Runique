package com.ryanphillips.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryanphillips.auth.domain.AuthRepository
import com.ryanphillips.auth.domain.UserDataValidator
import com.ryanphillips.auth.presentation.R
import com.ryanphillips.auth.presentation.textAsFlow
import com.ryanphillips.core.domain.util.DataError
import com.ryanphillips.core.domain.util.Result
import com.ryanphillips.core.presentation.ui.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
): ViewModel() {

    // Compose state for screen
    var state by mutableStateOf(RegisterState())
        private set

    // One-Time events channel we handle in Compose with ObserveAsEvent utility.
    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.email.textAsFlow()
            .onEach { email ->
                val isValidEmail = userDataValidator.isValidEmail(email.toString())
                state = state.copy(
                    isEmailValid = isValidEmail,
                    canRegister = isValidEmail && state.passwordValidationState.isValidPassword && !state.isRegistering
                )
            }
            .launchIn(viewModelScope)

        state.password.textAsFlow()
            .onEach { password ->
                val passwordValidationState = userDataValidator.validatePassword(password.toString())
                state = state.copy(
                    passwordValidationState = passwordValidationState,
                    canRegister = state.isEmailValid && passwordValidationState.isValidPassword && !state.isRegistering
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnRegisterClick -> register()
            is RegisterAction.OnTogglePasswordVisibilityClick -> {
                state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
            else -> Unit
        }
    }

    private fun register() {
        Timber.d("Register called!")
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = repository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isRegistering = false)
            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(RegisterEvent.Error(
                            error = UiText.StringResource(R.string.error_email_exists)
                        ))
                    }
                }
                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}