package com.example.aliaksei_kisel.coroutinestart.util

import android.support.annotation.UiThread

/**
 * Value class for passing through LiveData. Values will only be consumed once, uless the consumer
 * explicitly calls [release].
 *
 * For background see this blog post:
 * https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
class ConsumableValue<T>(private val data: T) {
    var consumed = false
        private set

    /**
     * Process this value, block will only be called once.
     */
    @UiThread
    fun consume(block: ConsumableValue<T>.(T) -> Unit) {
        val wasConsumed = consumed
        consumed = true
        if (!wasConsumed) {
            this.block(data)
        }
    }

    /**
     * Inside a handle lambda, you may call this if you discover that you cannot handle
     * the event right now. It will mark the event ad available to be handled by another handler.
     */
    @UiThread
    fun ConsumableValue<T>.release() {
        consumed = false
    }
}