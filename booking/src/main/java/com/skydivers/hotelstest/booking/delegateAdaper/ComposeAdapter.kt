package com.skydivers.hotelstest.booking.delegateAdaper

fun  MultyAdapter(vararg delegateAdapters: DelegateAdapter):CompositeDelegateAdapter {
    return CompositeDelegateAdapter(adapters = delegateAdapters)
}