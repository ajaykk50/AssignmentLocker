package com.test.machine.utils

import android.content.res.AssetManager
import com.google.gson.Gson
import com.test.machine.pojo.locker

// Function to read and parse JSON from assets
fun readAndParseJSON(assetManager: AssetManager, fileName: String): locker? {
    val json: String
    try {
        // Read the JSON file as a string
        val inputStream = assetManager.open(fileName)
        json = inputStream.bufferedReader().use { it.readText() }
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

    // Parse the JSON string using Gson
    return Gson().fromJson(json, locker::class.java)
}