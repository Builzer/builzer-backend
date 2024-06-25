package com.builzer.backend.global.util

import java.nio.file.Files
import java.nio.file.Paths

object FileUtils {
    fun readFileToString(filePath: String): String {
        return String(Files.readAllBytes(Paths.get(filePath)))
    }
}