package com.ayeee.presentation.element

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.buildSpannedString
import com.ayeee.model.Task
import com.ayeee.presentation.R
import com.ayeee.presentation.navigation.navigateToTaskDetail
import com.ayeee.presentation.navigation.parentNavHostController
import com.ayeee.utils.time.formattedDate

@Composable
fun TaskItem(task: Task, modifier: Modifier = Modifier) {
    val parentNavController = parentNavHostController.current
    Card(
        modifier = modifier
            .height(200.dp)
            .clickable(onClick = {
                parentNavController.navigateToTaskDetail(task)
            }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                task.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                task.description,
                modifier = Modifier
                    .weight(1F)
                    .padding(top = 4.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.small,
                    )
                    .clip(MaterialTheme.shapes.small)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        shape = MaterialTheme.shapes.small,
                    )
            ) {
                Text(
                    task.type,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            if (task.dueDate > 0L) {
                Text(
                    buildSpannedString {
                        append(stringResource(R.string.due))
                        append(" ")
                        append(task.dueDate formattedDate "dd MMM")
                    }.toString(),
                    Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

        }
    }
}