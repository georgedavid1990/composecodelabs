package com

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.jadc.composecodelabs.ui.screens.Greeting
import org.junit.Rule
import org.junit.Test

class GreetingsTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun greeting_name_is_displayed() {
        rule.setContent { Greeting(name = "Test") }
        rule.onNodeWithText("Test").assertExists()
    }

    @Test
    fun greeting_show_more_and_less() {
        rule.setContent { Greeting(name = "Test") }

        rule.onNodeWithTag("expand_button").performClick()

        rule.onNodeWithTag("expanded_text").assertExists()

        rule.onNodeWithTag("expand_button").performClick()

        rule.onNodeWithTag("expanded_text").assertDoesNotExist()
    }
}