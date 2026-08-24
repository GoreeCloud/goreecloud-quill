package com.goreecloud.quill.edit

/** Local, deterministic selection-editing helpers with explicit range validation. */
object QuillEditEngine {
    data class Selection(val start: Int, val endExclusive: Int) {
        init {
            require(start >= 0) { "start must be non-negative" }
            require(endExclusive >= start) { "endExclusive must be >= start" }
        }
    }

    fun replace(text: String, selection: Selection, replacement: String): String {
        require(selection.endExclusive <= text.length) { "selection exceeds text length" }
        return buildString(text.length - (selection.endExclusive - selection.start) + replacement.length) {
            append(text, 0, selection.start)
            append(replacement)
            append(text, selection.endExclusive, text.length)
        }
    }

    fun delete(text: String, selection: Selection): String = replace(text, selection, "")

    fun duplicate(text: String, selection: Selection): String {
        require(selection.endExclusive <= text.length) { "selection exceeds text length" }
        val selected = text.substring(selection.start, selection.endExclusive)
        return replace(text, Selection(selection.endExclusive, selection.endExclusive), selected)
    }

    fun swapAdjacentWords(text: String, left: Selection, right: Selection): String {
        require(left.endExclusive <= right.start) { "selections must be ordered and non-overlapping" }
        require(right.endExclusive <= text.length) { "selection exceeds text length" }

        val leftText = text.substring(left.start, left.endExclusive)
        val between = text.substring(left.endExclusive, right.start)
        val rightText = text.substring(right.start, right.endExclusive)

        return buildString(text.length) {
            append(text, 0, left.start)
            append(rightText)
            append(between)
            append(leftText)
            append(text, right.endExclusive, text.length)
        }
    }
}
