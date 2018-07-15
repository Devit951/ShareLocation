package com.example.david951.sharelocation.base

open class BasePresenter<View : BaseView> {
    var view: View? = null

    fun detachView() { view = null }
}
