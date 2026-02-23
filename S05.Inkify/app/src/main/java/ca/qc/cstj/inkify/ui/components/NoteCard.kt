package ca.qc.cstj.inkify.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ca.qc.cstj.inkify.models.Note

@Composable
fun NoteCard(
    note: Note,
    onSaveClick: (Note) -> Unit,
    onDeleteClick: (Note) -> Unit
) {

    //TODO: Gestion des dialogs

    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .clickable {
                //TODO: Dialog pour la modification
            },
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Title", style = MaterialTheme.typography.titleLarge, color = Color.Black
            )
            Text(
                text = "Content", color = Color.Black
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Date", color = Color.Black, style = MaterialTheme.typography.bodyMedium
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = Icons.Default.Delete.toString(),
                    tint = Color.Black,
                    modifier = Modifier.clickable {
                        //TODO: Dialog pour la suppression
                    })
            }
        }
    }
}