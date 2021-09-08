package com.example.myapplication.presentation

interface BaseView<PresenterT : BasePresenter> {
    val presenter: PresenterT
}