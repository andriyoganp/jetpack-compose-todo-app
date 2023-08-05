package com.ayeee.utils.converter

import com.google.gson.Gson

fun Any.convertToJson(): String = Gson().toJson(this)

fun <T> String?.fromJson(clas: Class<T>): T = Gson().fromJson(this, clas)