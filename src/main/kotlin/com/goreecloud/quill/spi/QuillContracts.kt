package com.goreecloud.quill.spi

/**
 * Host-provided integration seams. Quill core remains independent from Android,
 * storage, network, and UI frameworks so GoreeCloud Keyboard can bind native
 * platform implementations without leaking platform concerns into capability logic.
 */
interface QuillClock {
    fun nowEpochMillis(): Long
}

interface QuillLexicon {
    fun contains(token: String): Boolean
    fun suggestions(prefix: String, limit: Int): List<String>
}

interface QuillSnippetStore {
    fun listIds(): List<String>
    fun read(id: String): String?
}

interface QuillEventSink {
    /**
     * Events must be typed and text-free. Implementations must not include typed
     * text, selections, clipboard payloads, passwords, or arbitrary exception text.
     */
    fun record(event: QuillEvent)
}

sealed interface QuillEvent {
    data class CapabilityUsed(val capability: String) : QuillEvent
    data class PolicyDenied(val capability: String, val reason: DenialReason) : QuillEvent
}

enum class DenialReason {
    UNKNOWN_EDITOR,
    PASSWORD_FIELD,
    PRIVATE_SESSION,
    NO_PERSONALIZED_LEARNING,
    SENSITIVE_CLIPBOARD,
}
