package com.hehebaba.practise.architecture.hilt.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ExampleViewModel @ViewModelInject constructor(
    private var exampleRepository: ExampleRepository,
    @Assisted private var savedStateHandle: SavedStateHandle
) : ViewModel() {
}


class ExampleRepository @Inject constructor() {}