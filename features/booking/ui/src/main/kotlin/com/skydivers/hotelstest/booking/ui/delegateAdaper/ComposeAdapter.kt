package com.skydivers.hotelstest.booking.ui.delegateAdaper

fun  MultyAdapter(vararg delegateAdapters: DelegateAdapter): CompositeDelegateAdapter {
    return CompositeDelegateAdapter(adapters = delegateAdapters)
}