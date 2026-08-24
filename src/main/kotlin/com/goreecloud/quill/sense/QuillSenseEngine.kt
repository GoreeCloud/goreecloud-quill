package com.goreecloud.quill.sense

/**
 * Deterministic, local-only context classifier for lightweight typing assistance.
 *
 * Quill Sense intentionally operates on bounded caller-provided text and exposes no
 * persistence or network transport. Hosts decide whether contextual assistance is enabled
 * and remain responsible for Privacy Shield and Wardveil policy enforcement.
 */
object QuillSenseEngine {
    enum class Context {
        EMPTY,
        WORD,
        SENTENCE,
        QUESTION,
        EXCLAMATION,
    }

    data class Snapshot(
        val context: Context,
        val currentToken: String,
        val sentenceStart: Boolean,
    )

    fun inspect(textBeforeCursor: String, maxChars: Int = 256): Snapshot {
        require(maxChars > 0) { "maxChars must be positive" }

        val bounded = textBeforeCursor.takeLast(maxChars)
        val trimmedEnd = bounded.trimEnd()
        if (trimmedEnd.isEmpty()) {
            return Snapshot(Context.EMPTY, currentToken = "", sentenceStart = true)
        }

        val currentToken = bounded
            .takeLastWhile { it.isLetterOrDigit() || it == '\'' || it == '-' }

        val terminal = trimmedEnd.last()
        val context = when (terminal) {
            '?' -> Context.QUESTION
            '!' -> Context.EXCLAMATION
            '.', ':', ';' -> Context.SENTENCE
            else -> if (trimmedEnd.any(Char::isWhitespace)) Context.SENTENCE else Context.WORD
        }

        val previousNonSpace = bounded
            .dropLast(currentToken.length)
            .trimEnd()
            .lastOrNull()

        val sentenceStart = previousNonSpace == null || previousNonSpace in ".!?"
        return Snapshot(context, currentToken, sentenceStart)
    }
}
