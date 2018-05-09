package t.p.cv

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import t.p.R

class Shadow0View @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val rect = Rect()
    private var space: Int

    init {
        val dx = resources.getDimension(R.dimen.shadowDx)
        val dy = resources.getDimension(R.dimen.shadowDy)
        val radius = resources.getDimension(R.dimen.shadowRadius)
        space = resources.getDimensionPixelSize(R.dimen.shadowSpace)
        val shadowColor = ContextCompat.getColor(context, R.color.shadowColor)

        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = Color.BLUE
        paint.setShadowLayer(radius, dx, dy, shadowColor)
        setLayerType(LAYER_TYPE_SOFTWARE, paint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.left = space
        rect.top = space
        rect.right = width - space
        rect.bottom = height - space
        canvas.drawRect(rect, paint)
    }

}
