package com.ryanphillips.auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryanphillips.auth.domain.UserDataValidator
import com.ryanphillips.auth.presentation.R
import com.ryanphillips.core.presentation.designsystem.CheckIcon
import com.ryanphillips.core.presentation.designsystem.CrossIcon
import com.ryanphillips.core.presentation.designsystem.EmailIcon
import com.ryanphillips.core.presentation.designsystem.Poppins
import com.ryanphillips.core.presentation.designsystem.RuniqueDarkRed
import com.ryanphillips.core.presentation.designsystem.RuniqueGreen
import com.ryanphillips.core.presentation.designsystem.RuniqueTheme
import com.ryanphillips.core.presentation.designsystem.component.GradientBackground
import com.ryanphillips.core.presentation.designsystem.component.RuniqueActionButton
import com.ryanphillips.core.presentation.designsystem.component.RuniquePasswordTextField
import com.ryanphillips.core.presentation.designsystem.component.RuniqueTextField
import com.ryanphillips.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoot(
    onSignInClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveAsEvents(
        viewModel.events
    ) { event ->
        when (event) {
            is RegisterEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                )
                    .show()
            }

            is RegisterEvent.RegistrationSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.registration_successful,
                    Toast.LENGTH_LONG
                )
                    .show()
                onSuccessfulRegistration()
            }
        }
    }

    RegisterScreen(
        state = viewModel.state,
        onAction = {
            when (it) {
                is RegisterAction.OnLoginClick -> onSignInClick()
                else -> Unit
            }
            viewModel.onAction(it)
        }
    )
}

@Composable
private fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.headlineMedium
            )
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Poppins,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    append(stringResource(id = R.string.already_have_an_account) + " ")
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "clickable_text",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontFamily = Poppins
                                )
                            ),
                            linkInteractionListener = {
                                onAction(RegisterAction.OnLoginClick)
                            })
                    ) {
                        append(stringResource(id = R.string.login))
                    }
                }
            }
            Text(
                text = annotatedString,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(48.dp))
            RuniqueTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = if (state.isEmailValid) {
                    CheckIcon
                } else {
                    null
                },
                hint = stringResource(R.string.example_email),
                title = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth(),
                additionalInfo = stringResource(R.string.must_be_a_valid_email),
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(16.dp))
            RuniquePasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = {
                    onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                },
                hint = stringResource(R.string.password),
                title = stringResource(R.string.password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            PasswordRequirement(
                text = stringResource(
                    id = R.string.at_least_x_characters,
                    UserDataValidator.MIN_PASSWORD_LENGTH
                ),
                isValid = state.passwordValidationState.hasMinLength
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirement(
                text = stringResource(
                    id = R.string.at_least_one_number,
                ),
                isValid = state.passwordValidationState.hasNumber
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirement(
                text = stringResource(
                    id = R.string.contains_lowercase_char,
                ),
                isValid = state.passwordValidationState.hasLowerCaseCharacter
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirement(
                text = stringResource(
                    id = R.string.contains_uppercase_char,
                ),
                isValid = state.passwordValidationState.hasUpperCaseCharacter
            )
            Spacer(modifier = Modifier.height(32.dp))
            RuniqueActionButton(
                text = stringResource(id = R.string.register),
                isLoading = state.isRegistering,
                enabled = state.canRegister,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(RegisterAction.OnRegisterClick)
                })
        }
    }
}

@Composable
fun PasswordRequirement(
    text: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isValid) {
                CheckIcon
            } else {
                CrossIcon
            },
            contentDescription = null,
            tint = if (isValid) RuniqueGreen else RuniqueDarkRed
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RuniqueTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {})
    }
}