package com.goreecloud.quill.compose

/** Local, deterministic composition helpers. No network or model dependency. */
object QuillComposeEngine {
    fun normalizeSpacing(text: String): String = text
        .replace(Regex("[ \\t]+"), " ")
        .replace(Regex(" +([,.;:!?])"), "$1")
        .trim()

    fun ensureTerminalPunctuation(text: String): String {
        val normalized = normalizeSpacing(text)
        if (normalized.isEmpty()) return normalized
        return if (normalized.last() in ".!?") normalized else "$normalized."
    }

    fun capitalizeSentence(text: String): String {
        val normalized = normalizeSpacing(text)
        if (normalized.isEmpty()) return normalized
        return normalized.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}