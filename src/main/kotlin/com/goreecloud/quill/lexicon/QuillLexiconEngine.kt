package com.goreecloud.quill.lexicon

import com.goreecloud.quill.spi.QuillLexicon
import java.text.Normalizer
import java.util.Locale

class QuillLexiconEngine(
    words: Iterable<String>,
) : QuillLexicon {
    private val canonicalWords: Map<String, String> = words
        .mapNotNull { raw ->
            val display = raw.trim()
            if (display.isEmpty()) null else normalize(display) to display
        }
        .distinctBy { it.first }
        .sortedBy { it.first }
        .toMap()

    override fun contains(token: String): Boolean = normalize(token) in canonicalWords

    override fun suggestions(prefix: String, limit: Int): List<String> {
        if (limit <= 0) return emptyList()
        val normalizedPrefix = normalize(prefix)
        if (normalizedPrefix.isEmpty()) return emptyList()

        return canonicalWords
            .asSequence()
            .filter { (key, _) -> key.startsWith(normalizedPrefix) }
            .sortedWith(compareBy<Map.Entry<String, String>> { it.key.length }.thenBy { it.key })
            .map { it.value }
            .take(limit)
            .toList()
    }

    companion object {
        internal fun normalize(value: String): String = Normalizer
            .normalize(value.trim(), Normalizer.Form.NFKC)
            .lowercase(Locale.ROOT)
    }
}
