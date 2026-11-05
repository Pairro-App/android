package pairro.ui.components.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StayCurrentLandscape
import androidx.compose.material.icons.outlined.StayCurrentPortrait
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import pairro.ui.theme.PairroColors

@Composable
fun SettingsOrientationRow(
  title: String,
  subtitle: String,
  landscape: Boolean,
  onLandscapeChange: (Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 12.dp)
  ) {
    Column(modifier = Modifier.weight(1f)) {
      Text(text = title, style = MaterialTheme.typography.bodyLarge)
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = PairroColors.TextMuted
      )
    }

    Spacer(modifier = Modifier.width(12.dp))

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      OrientationOption(
        icon = Icons.Outlined.StayCurrentPortrait,
        contentDescription = "Portrait",
        selected = !landscape,
        onClick = { onLandscapeChange(false) }
      )
      OrientationOption(
        icon = Icons.Outlined.StayCurrentLandscape,
        contentDescription = "Landscape",
        selected = landscape,
        onClick = { onLandscapeChange(true) }
      )
    }
  }
}

@Composable
private fun OrientationOption(
  icon: ImageVector,
  contentDescription: String,
  selected: Boolean,
  onClick: () -> Unit
) {
  val shape = RoundedCornerShape(12.dp)
  val primary = MaterialTheme.colorScheme.primary

  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
      .size(44.dp)
      .clip(shape)
      .background(if (selected) primary.copy(alpha = 0.14f) else Color.Transparent)
      .border(1.dp, if (selected) primary else PairroColors.BorderSubtle, shape)
      .selectable(selected = selected, role = Role.RadioButton, onClick = onClick)
  ) {
    Icon(
      imageVector = icon,
      contentDescription = contentDescription,
      tint = if (selected) primary else PairroColors.TextMuted,
      modifier = Modifier.size(22.dp)
    )
  }
}