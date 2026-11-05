package pairro.ui.components.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun ImmersiveControlEffect(portrait: Boolean) {
  val activity = LocalContext.current.findActivity()

  DisposableEffect(activity) {
    val window = activity?.window

    if (window != null) {
      val controller = WindowCompat.getInsetsController(window, window.decorView)
      controller.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
      controller.hide(WindowInsetsCompat.Type.systemBars())
    }

    onDispose {
      if (activity?.isChangingConfigurations != true) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        if (window != null) {
          WindowCompat
            .getInsetsController(window, window.decorView)
            .show(WindowInsetsCompat.Type.systemBars())
        }
      }
    }
  }

  LaunchedEffect(activity, portrait) {
    activity?.requestedOrientation =
      if (portrait) ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
      else ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
  }
}

private tailrec fun Context.findActivity(): Activity? = when (this) {
  is Activity -> this
  is ContextWrapper -> baseContext.findActivity()
  else -> null
}