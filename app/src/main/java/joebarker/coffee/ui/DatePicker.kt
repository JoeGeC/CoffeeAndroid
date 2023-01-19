package joebarker.coffee.ui

import android.widget.CalendarView
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun DatePicker(listener: CalendarView.OnDateChangeListener) {
    AndroidView(
        { CalendarView(it) },
        modifier = Modifier.wrapContentWidth(),
        update = { views -> views.setOnDateChangeListener(listener) }
    )
}