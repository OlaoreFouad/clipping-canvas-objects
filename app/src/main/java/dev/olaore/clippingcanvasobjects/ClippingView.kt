package dev.olaore.clippingcanvasobjects

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
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
        drawDifferenceClippingExample(canvas)
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
        canvas.save()
        // Move the origin to the right for the next rectangle.
        canvas.translate(columnTwo,rowOne)
        // Use the subtraction of two clipping rectangles to create a frame.
        canvas.clipRect(
            2 * rectInset,2 * rectInset,
            clipRectRight - 2 * rectInset,
            clipRectBottom - 2 * rectInset
        )
        // The method clipRect(float, float, float, float, Region.Op
        // .DIFFERENCE) was deprecated in API level 26. The recommended
        // alternative method is clipOutRect(float, float, float, float),
        // which is currently available in API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            canvas.clipRect(
                4 * rectInset,4 * rectInset,
                clipRectRight - 4 * rectInset,
                clipRectBottom - 4 * rectInset,
                Region.Op.DIFFERENCE
            )
        } else {
            canvas.clipOutRect(
                4 * rectInset,4 * rectInset,
                clipRectRight - 4 * rectInset,
                clipRectBottom - 4 * rectInset
            )
        }
        drawClippedRectangle(canvas)
        canvas.restore()
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