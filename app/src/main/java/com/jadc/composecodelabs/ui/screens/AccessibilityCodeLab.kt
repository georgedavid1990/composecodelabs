package com.jadc.composecodelabs.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp

/**
 * Any on-screen element that someone can click, touch, or otherwise interact with
 * should be large enough for reliable interaction.
 * You should make sure these elements have a width and height of at least 48dp.
 */

@Composable
fun AccessibilityElement() {

    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close",
        modifier = Modifier
            .clickable { }
            .size(24.dp)
    )

    // To increase the touch target size of this Icon, we can add padding:
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close",
        modifier = Modifier
            .clickable { }
            .padding(12.dp)
            .size(24.dp)
    )

    // there is an easier way to make sure the touch target is at least 48dp.
    // We can make use of the Material component IconButton that will handle this for us:
    IconButton(onClick = { }) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close"
        )
    }

    // Clickable elements in your app by default don't provide any information on what clicking
    // that element will do. Therefore, accessibility services like TalkBack will use a very
    // generic default description.

    // To provide the best experience for users with accessibility needs, we can provide a specific
    // description that explains what will happen when the user clicks this element.

    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close",
        modifier = Modifier
            .clickable(onClickLabel = "close screen") {
                // TalkBack now correctly announces "Double tap to close screen".
            }
            .padding(12.dp)
            .size(24.dp)
    )

    // This composable uses the onClick parameter, which does not allow you to directly set
    // the click label. Instead, you can use the semantics modifier to set the click label:
    IconButton(
        modifier = Modifier.semantics { onClick(label = "close screen", action = null) },
        onClick = { }
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close"
        )
    }


    // Many apps show some sort of list, where each item in the list contains one or more actions.
    // When using a screen reader, navigating such a list can become tedious, as the same action
    // would be focused over and over again.

    // ...
    val showFewerLabel = "Show fewer of this articles"
    Row(
        Modifier
            .clickable(
                onClickLabel = "read article"
            ) {
                // navigateToArticle(post.id)
            }
            .semantics {
                // However, by removing the semantics of the IconButton, there is now no way to
                // execute the action anymore. We can add the action to the list item instead by
                // adding a custom action in the semantics modifier:
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = showFewerLabel,
                        // action returns boolean to indicate success
                        action = {
                            // openDialog = true;
                            true
                        }
                    )
                )
            }
    ) {
        // ...
        // We rather want the action related to the IconButton to be included as a custom action on
        // the list item. We can tell Accessibility Services not to interact with this Icon by using
        // the clearAndSetSemantics modifier:
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
            IconButton(
                modifier = Modifier.clearAndSetSemantics { },
                onClick = {
                    // openDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = showFewerLabel
                )
            }
        }
    }

    // Visual composables like Image and Icon include a parameter contentDescription.
    // Here you pass a localized description of that visual element, or null if the
    // element is purely decorative.
    IconButton(
        onClick = { }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Navigate up"
        )
    }

    // When a screen contains a lot of text, like our article screen, it is quite hard for users
    // with visual difficulties to quickly find the section they're looking for.
    // To help with that, we can indicate which parts of the text are headings. Users can then
    // navigate quickly through these different headings by swiping up or down.
    Text(
        modifier = Modifier.padding(4.dp)
            .semantics { heading() },
        text = "Article title",
        style = MaterialTheme.typography.titleLarge
    )

    // Having too many focusable elements on screen can lead to confusion as the user navigates
    // them one by one. Instead, composables can be merged together using the semantics modifier
    // with its mergeDescendants property.
    Row(Modifier.semantics(mergeDescendants = true) {}) {
        Text(text = "Article title")
        Text(text = "Article subtitle")
    }

    // Toggleable elements like Switch and Checkbox read out loud their checked state as they are
    // selected by TalkBack. Without context it can be hard to understand what these toggleable
    // elements refer to though. We can include context for a toggleable element by lifting the
    // toggleable state up, so a user can toggle the Switch or Checkbox by either pressing the
    // composable itself, or the label that describes it.
    // ...
    val selected by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .semantics {
                // By default, our Checkbox status is read as either "Ticked" or "Not ticked".
                // We can replace this description with our own custom description:
                stateDescription = if (selected) {
                    "Subscribed"
                } else {
                    "Not subscribed"
                }
            }
            .toggleable(
                value = selected,
                onValueChange = { _ ->  },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // ...
        Checkbox(
            checked = selected,
            onCheckedChange = null,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}