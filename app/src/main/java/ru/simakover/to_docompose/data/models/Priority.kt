package ru.simakover.to_docompose.data.models


import androidx.compose.ui.graphics.Color
import ru.simakover.to_docompose.ui.theme.HighPriorityColor
import ru.simakover.to_docompose.ui.theme.LowPriorityColor
import ru.simakover.to_docompose.ui.theme.MediumPriorityColor
import ru.simakover.to_docompose.ui.theme.NonePriorityColor


enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor),
}