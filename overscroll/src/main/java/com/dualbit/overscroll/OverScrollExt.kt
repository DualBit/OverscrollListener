package com.dualbit.overscroll

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val OverscrollTag = "Overscroll"

inline fun RecyclerView.addOverScrollEdge(
    lifecycleScope: LifecycleCoroutineScope,
    crossinline onTop: () -> Unit = {},
    crossinline onBottom: () -> Unit = {}
) {
    var isAtTop = false
    var isAtBottom = false
    var currentDy = 0
    var job: Job? = null
    var lastVisibleItemPosition = 0
    var firstVisibleItemPosition = 0

    this.layoutManager = LinearLayoutManager(
        context,
        LinearLayoutManager.VERTICAL,
        false
    )

    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == SCROLL_STATE_DRAGGING && (isAtTop || firstVisibleItemPosition == 0)) {
                job?.cancel()
                job = lifecycleScope.launch {
                    try {
                        delay(100)
                        if (currentDy <= 0) {
                            onTop()
                        }
                    } catch (e: Exception) {
                        Log.e(OverscrollTag, e.message ?: "", e)
                    }
                }
            }
            if (newState == SCROLL_STATE_DRAGGING &&
                (isAtBottom || lastVisibleItemPosition == ((this@addOverScrollEdge.adapter?.itemCount
                    ?: 0) - 1))
            ) {
                job?.cancel()
                job = lifecycleScope.launch {
                    try {
                        delay(100)
                        if (currentDy >= 0) {
                            onBottom()
                        }
                    } catch (e: Exception) {
                        Log.e(OverscrollTag, e.message ?: "", e)
                    }
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val totalItemCount = layoutManager.itemCount
            lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            isAtTop = firstVisibleItemPosition == 0 && dy < 0
            isAtBottom = lastVisibleItemPosition == totalItemCount - 1 && dy > 0
            currentDy = dy
        }
    })
}