package com.ryanphillips.auth.presentation.intro

/**
 * Things the user could trigger on the Auth Intro Screen
 * * Sign In
 * * Sign Up
 */
sealed interface IntroAction {
    data object OnSignInClick: IntroAction
    data object OnSignUpClick: IntroAction
}