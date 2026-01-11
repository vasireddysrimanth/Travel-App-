package com.devsrimanth.travenor_cmp.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Represents a single highlighted word and its styling configuration.
 */
data class TextHighlight(
    val word: String,
    val color: Color,
    val showUnderline: Boolean = true,
    val fontWeight: FontWeight = FontWeight.Normal
)

@Composable
fun MultiHighlightedText(
    modifier: Modifier,
    fullText: String,
    highlights: List<TextHighlight>,
    textStyle: TextStyle = TextStyle.Default,
    fontSize: TextUnit = 33.sp,
    fontColor: Color = Color.Black,
    thickness: Dp = 6.dp,
    peakHeight: Dp = 10.dp,
) {

    /**
     * Internal data class used to store the character
     * range and styling details of a highlighted word.
     */
    data class HighlightPosition(
        val startIndex: Int,
        val endIndex: Int,
        val highlight: TextHighlight
    )

    /**
     * Calculates the start and end character positions
     * for each highlighted word.
     *
     * This computation is wrapped in remember so it
     * recalculates only when the text or highlights change.
     */
    val highlightedPositions = remember(fullText, highlights) {
        highlights.mapNotNull {
            val start = fullText.indexOf(it.word)
            if (start != -1) {
                HighlightPosition(
                    startIndex = start,
                    endIndex = start + it.word.length,
                    highlight = it
                )
            } else null
        }.sortedBy { it.startIndex }
    }

    /**
     * Builds an AnnotatedString to apply different styles
     * to different parts of the same text.
     */
    val annotatedString = buildAnnotatedString {
        var currentIndex = 0

        highlightedPositions.forEach { position ->

            // Append normal text before the highlighted word
            if (currentIndex < position.startIndex) {
                withStyle(
                    SpanStyle(
                        color = fontColor,
                        fontSize = fontSize,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(fullText.substring(currentIndex, position.startIndex))
                }
            }

            // Append the highlighted word with custom style
            withStyle(
                SpanStyle(
                    color = position.highlight.color,
                    fontSize = fontSize,
                    fontWeight = position.highlight.fontWeight
                )
            ) {
                append(fullText.substring(position.startIndex, position.endIndex))
            }

            currentIndex = position.endIndex
        }

        // Append remaining text after the last highlighted word
        if (currentIndex < fullText.length) {
            withStyle(
                SpanStyle(
                    color = fontColor,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Normal
                )
            ) {
                append(fullText.substring(currentIndex))
            }
        }
    }

    /**
     * TextMeasurer is used to obtain layout information
     * such as character bounding boxes, which are required
     * for drawing custom underlines.
     */
    val textMeasurer = rememberTextMeasurer()

    /**
     * Measures the annotated text layout only once and
     * provides access to character position data.
     */
    val textLayoutResults = remember {
        textMeasurer.measure(annotatedString, style = textStyle)
    }

    /**
     * Prepares underline drawing data for highlighted words
     * that have underline enabled.
     *
     * For each word, a bounding rectangle is calculated
     * based on the first and last character positions.
     */
    val underlineData = remember(textLayoutResults, highlightedPositions) {
        highlightedPositions
            .filter { it.highlight.showUnderline }
            .mapNotNull { position ->
                try {
                    val startBox = textLayoutResults.getBoundingBox(position.startIndex)
                    val endBox = textLayoutResults.getBoundingBox(position.endIndex - 1)

                    Pair(
                        Rect(
                            left = startBox.left,
                            top = startBox.bottom,
                            right = endBox.right,
                            bottom = startBox.bottom
                        ),
                        position.highlight.color
                    )
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    null
                }
            }
    }

    /**
     * Box is used to layer the Text and Canvas.
     * Canvas is drawn on top of the Text to render
     * custom curved underlines.
     */
    Box(modifier = modifier.fillMaxWidth()) {

        Text(
            text = annotatedString,
            style = textStyle
        )

        Canvas(modifier = Modifier.matchParentSize()) {

            underlineData.forEach { (boundingBox, color) ->

                if (boundingBox != Rect.Zero) {

                    val baseY = boundingBox.bottom + 2.dp.toPx()
                    val startX = boundingBox.left
                    val endX = boundingBox.right
                    val peak = peakHeight.toPx()
                    val strokeThickness = thickness.toPx()

                    /**
                     * A wave-like curved underline is drawn using
                     * quadratic Bézier curves.
                     */
                    val path = Path().apply {
                        moveTo(startX, baseY)

                        quadraticTo(
                            x1 = (startX + endX) / 2,
                            y1 = baseY - peak - strokeThickness / 2,
                            x2 = endX,
                            y2 = baseY
                        )

                        quadraticTo(
                            x1 = (startX + endX) / 2,
                            y1 = baseY - peak + strokeThickness / 2,
                            x2 = startX,
                            y2 = baseY
                        )
                    }

                    drawPath(
                        path = path,
                        color = color,
                        style = Fill
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiHighlightedTextPreview() {
    Scaffold {
        MultiHighlightedText(
            modifier = Modifier,
            fullText = "Explore the \nbeautiful world!",
            highlights = listOf(
                TextHighlight(
                    word = "world!",
                    color = Color.Magenta,
                    showUnderline = true,
                    fontWeight = FontWeight.Bold
                ),
                TextHighlight(
                    word = "beautiful",
                    color = Color.Black,
                    showUnderline = false,
                    fontWeight = FontWeight.Bold
                )
            )
        )
    }
}
