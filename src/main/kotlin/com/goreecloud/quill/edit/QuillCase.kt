package com.goreecloud.quill.edit

/**
 * Pure text transformation used by host applications only after their
 * Wardveil/Privacy policy layer has authorized access to an explicit selection.
 */
object QuillCase {
    fun next(text: String): String? {
        if (text.none(Char::isLetter)) return null

        val lower = text.lowercase()
        val upper = text.uppercase()

        return when (text) {
            lower -> toTitleCase(text)
            toTitleCase(text) -> upper
            upper -> lower
            else -> lower
        }
    }

    private fun toTitleCase(text: String): String {
        var capitalizeNext = true
        return buildString(text.length) {
            text.forEach { char ->
                if (char.isLetter()) {
                    append(if (capitalizeNext) char.titlecaseChar() else char.lowercaseChar())
                    capitalizeNext = false
                } else {
                    append(char)
                    capitalizeNext = char.isWhitespace() || char == '-' || char == '/' || char == '.'
                }
            }
        }
    }
}
