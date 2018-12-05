package com.buildertrend.factfinder.list

sealed class Event

object SelectRandomFactEvent : Event()

object AddFactEvent : Event()

object UseFactEvent : Event()
