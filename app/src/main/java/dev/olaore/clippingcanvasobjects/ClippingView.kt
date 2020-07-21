package dev.olaore.clippingcanvasobjects

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

class ClippingView
    constructor(
        private val ctx: Context,
        private val attributeSet: AttributeSet? = null,
        private val defStyleAttr: Int = 0
    ): View(ctx, attributeSet, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
    }

    private val path = Path()
    private val clipRectRight = resources.getDimension(R.dimen.clipRectRight)
    private val clipRectBottom = resources.getDimension(R.dimen.clipRectBottom)
    private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
    private val clipRectLeft = resources.getDimension(R.dimen.clipRectLeft)

    private val rectInset = resources.getDimension(R.dimen.rectInset)
    private val smallRectOffset = resources.getDimension(R.dimen.smallRectOffset)

    private val circleRadius = resources.getDimension(R.dimen.circleRadius)
    private val textSize = resources.getDimension(R.dimen.textSize)
    private val textOffset = resources.getDimension(R.dimen.textOffset)

    private val columnOne = rectInset
    private val columnTwo = rectInset + columnOne + clipRectRight

    private val rowOne = rectInset
    private val rowTwo = rectInset + rowOne + clipRectBottom
    private val rowThree = rowTwo + rectInset + clipRectBottom
    private val rowFour = rowThree + rectInset + clipRectBottom
    private val textRow = rowFour + (1.5 * clipRectBottom)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBackAndUnclippedRectangle(canvas!!)
    }

    private fun drawBackAndUnclippedRectangle(canvas: Canvas){
        canvas.drawColor(Color.GRAY) // draw the gray background

        canvas.save()
        canvas.translate(columnOne, rowOne)
        drawClippedRectangle(canvas)
        canvas.restore()
    }

    private fun drawClippedRectangle(canvas: Canvas) {
        canvas.clipRect(
            clipRectLeft, clipRectTop, clipRectRight, clipRectBottom
        )

        // draw white rectangle - not a rect actually
        canvas.drawColor(Color.WHITE)

        // draw line
        paint.color = Color.RED
        canvas.drawLine(
            clipRectLeft, clipRectTop, clipRectRight, clipRectBottom, paint
        )

        // draw circle
        paint.color = Color.GREEN
        canvas.drawCircle(
            circleRadius, clipRectBottom - circleRadius, circleRadius, paint
        )

        // draw text
        paint.color = Color.BLUE
        paint.textSize = textSize
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText(
            resources.getString(R.string.clipping), clipRectRight, textOffset, paint
        )

    }

    private fun drawDifferenceClippingExample(canvas: Canvas){
    }

    private fun drawCircularClippingExample(canvas: Canvas){
    }

    private fun drawIntersectionClippingExample(canvas: Canvas){
    }

    private fun drawCombinedClippingExample(canvas: Canvas){
    }

    private fun drawRoundedRectangleClippingExample(canvas: Canvas){
    }

    private fun drawOutsideClippingExample(canvas: Canvas){
    }

    private fun drawTranslatedTextExample(canvas: Canvas){
    }

    private fun drawSkewedTextExample(canvas: Canvas){
    }

    private fun drawQuickRejectExample(canvas: Canvas){
    }

}