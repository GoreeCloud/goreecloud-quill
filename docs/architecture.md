# Quill Architecture

GoreeCloud Quill is an application capability-family foundation for GoreeCloud Keyboard. It organizes advanced typing, writing assistance, editing, personalization, dictionaries, clipboard, and intelligent input capabilities without becoming a separate application authority.

## Capability families

The canonical capability taxonomy is Quill Flow, Sense, Learn, Lexicon, Clip, Snippets, Edit, Case, Glide, Compose, Recall, Private, Toolbox, and Themes.

## Native boundary

Quill core is implemented as original GoreeCloud-owned Kotlin source. The core avoids Android framework dependencies so policy, transformation, and capability contracts can be unit tested independently. GoreeCloud Keyboard will provide native Android adapters at the application boundary.

## Platform-system boundaries

- Glaze UI owns visual and interaction semantics. Quill does not define a competing UI system.
- Wardveil Security owns security and protection state. Quill policy adapters must consume evidence-backed Wardveil decisions rather than inventing security badges.
- Privacy Shield owns privacy controls and data-minimization contracts. Quill core is text-minimizing by design and keeps raw typed text out of diagnostics contracts.
- Everkeep owns resilience, preservation, portability, recovery, and continuity contracts. Persistent Quill data stores must expose explicit export/recovery behavior before Stable qualification.

## Privacy model

Quill Private evaluates text-free editor metadata. Unknown editors, password fields, editor requests that disable personalized learning, and explicit Quill Private sessions fail closed. In those states Quill suppresses personalized learning, context reads, automatic clipboard retention, swipe-learning retention, and typed-text diagnostics.

## Storage model

Quill core contains no implicit persistent store. Persistence is host-provided through bounded interfaces. Clipboard payloads, selections, typed text, passwords, and arbitrary exception text are prohibited from Quill diagnostic events.

## Licensing boundary

Original GoreeCloud Quill source is licensed under the Mozilla Public License 2.0 (`MPL-2.0`). Quill does not elect the MPL 2.0 Exhibit B "Incompatible With Secondary Licenses" designation. This preserves MPL's default secondary-license compatibility for combining Quill-covered files into a larger consuming application while keeping the MPL requirements applicable to the covered Quill files. Host application code and separately licensed dependencies retain their applicable licenses.

## Validation boundary

Source presence and passing unit tests establish only the core-library foundation. They do not establish Android integration, compiled APK acceptance, real-device acceptance, current Glaze UI conformance, complete Wardveil/Privacy Shield/Everkeep integration, production readiness, or Stable status.
