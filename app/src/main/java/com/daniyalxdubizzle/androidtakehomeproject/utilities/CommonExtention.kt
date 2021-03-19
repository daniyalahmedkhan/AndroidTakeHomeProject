package com.daniyalxdubizzle.androidtakehomeproject.utilities

val String.capitalizeWords get() = this.toLowerCase().split(" ").joinToString(" ") { it.capitalize() }

