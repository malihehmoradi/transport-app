package com.example.barflowapp.domain.usecase

import javax.inject.Inject

class FakeDataSource
    @Inject
    constructor() {
        fun getFakeData(): String = "Fake Data"
    }