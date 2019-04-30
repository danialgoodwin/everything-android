package dev.goodwin.android.common

import android.content.Context
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView

class TouchSlopRecyclerViewOnItemTouchListener(context: Context) : RecyclerView.OnItemTouchListener {

    private var isScrolling = false
    private var initialTouchY: Int? = null
    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onInterceptTouchEvent(rv: RecyclerView, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                isScrolling = false
                initialTouchY = null
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isScrolling) {
                    initialTouchY?.let {
                        isScrolling = Math.abs(event.y - it) > touchSlop
                    }
                }
                if (isScrolling) rv.parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_DOWN -> initialTouchY = (event.y + 0.5f).toInt()
            else -> { /* Ignored */ }
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

}
