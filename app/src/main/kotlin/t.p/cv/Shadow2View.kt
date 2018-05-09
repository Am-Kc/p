package t.p.cv

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import t.p.R

/**
 *  背景渐变，红色阴影(有模糊效果的阴影)
 */
class Shadow2View @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val shadowPaint = Paint()
    private val rect = Rect()
    private var space: Int

    private val bgPaint = Paint()

    init {
        val dx = resources.getDimension(R.dimen.shadowDx)
        val dy = resources.getDimension(R.dimen.shadowDy)
        val radius = resources.getDimension(R.dimen.shadowRadius)
        space = resources.getDimensionPixelSize(R.dimen.shadowSpace)
        val shadowColor = ContextCompat.getColor(context, R.color.shadowColor)

        shadowPaint.isAntiAlias = true
        shadowPaint.style = Paint.Style.FILL
        shadowPaint.setShadowLayer(radius, dx, dy, shadowColor)
        setLayerType(LAYER_TYPE_SOFTWARE, shadowPaint)

        bgPaint.isAntiAlias = true
        bgPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.left = space
        rect.top = space
        rect.right = width - space
        rect.bottom = height - space

        canvas.drawRect(rect, shadowPaint)

        bgPaint.shader = LinearGradient(
            rect.left.toFloat(), rect.left.toFloat(),
            rect.left.toFloat(), rect.bottom.toFloat(),
            Color.BLUE, Color.GREEN,
            Shader.TileMode.REPEAT
        )
        canvas.drawRect(rect, bgPaint)
    }

}
