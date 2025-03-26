package com.jadc.composecodelabs

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
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
    fun greeting_show_more_and_less_text() {
        rule.setContent { Greeting(name = "Test") }

        rule.onNodeWithTag("expand_button").performClick()

        rule.onNodeWithTag("expanded_text").assertExists()

        rule.onNodeWithTag("expand_button").performClick()

        rule.onNodeWithTag("expanded_text").assertDoesNotExist()
    }

    @Test
    fun greeting_icon_show_more_show_less() {
        rule.setContent { Greeting(name = "Test") }

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val showMore = context.resources.getString(R.string.show_more)
        val showLess = context.resources.getString(R.string.show_less)
        rule.onNodeWithTag("expand_button", useUnmergedTree = true)
            .onChild()
            .assertContentDescriptionEquals(showMore)

        rule.onNodeWithTag("expand_button").performClick()
        rule.onNodeWithTag("expand_button", useUnmergedTree = true)
            .onChild()
            .assertContentDescriptionEquals(showLess)
    }
}