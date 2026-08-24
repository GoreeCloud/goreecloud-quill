package com.goreecloud.quill.toolbox

object QuillToolbox {
    fun wordCount(text: String): Int = text.trim()
        .takeIf { it.isNotEmpty() }
        ?.split(Regex("\\s+"))
        ?.size ?: 0

    fun characterCount(text: String, includeWhitespace: Boolean = true): Int =
        if (includeWhitespace) text.length else text.count { !it.isWhitespace() }

    fun lineCount(text: String): Int = if (text.isEmpty()) 0 else text.lineSequence().count()

    fun reverse(text: String): String = text.reversed()
}