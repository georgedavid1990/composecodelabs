package com.jadc.composecodelabs

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.jadc.composecodelabs.ui.screens.TestingCodeLab
import org.junit.Rule
import org.junit.Test

class TestingCodeLab {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun alert_is_displayed() {
        rule.setContent { TestingCodeLab() }

        rule.waitForIdle()

        rule.onNodeWithText("Testing CodeLab").assertIsDisplayed()
    }
}