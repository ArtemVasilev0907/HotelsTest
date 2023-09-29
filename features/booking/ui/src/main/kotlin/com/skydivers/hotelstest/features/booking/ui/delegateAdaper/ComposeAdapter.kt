package com.skydivers.hotelstest.features.booking.ui.delegateAdaper

fun  MultyAdapter(vararg delegateAdapters: DelegateAdapter): CompositeDelegateAdapter {
    return CompositeDelegateAdapter(adapters = delegateAdapters)
}