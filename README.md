# Superr Notes 📓

A beautiful native Android notes app built with the **Superr design system** — a warm schoolyard aesthetic rendered in digital form.

![Superr Notes](https://img.shields.io/badge/Android-Native-green?style=flat-square&logo=android)
![Kotlin](https://img.shields.io/badge/Kotlin-2.0-purple?style=flat-square&logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Modern-blue?style=flat-square)

## ✨ Features

- **Create, edit, and delete notes** with a beautiful, tactile interface
- **Color-coded note covers** — choose from cream, orange, blue, pink, or green
- **Pin important notes** to keep them at the top
- **Search functionality** to quickly find your notes
- **Staggered grid layout** for a notebook-like browsing experience
- **Local storage** with Room database — your notes stay on your device
- **Handwritten captions** and decorative sticker illustrations
- **Name label stickers** that mimic real school notebook labels

## 🎨 Design System

Superr Notes implements the **Superr design system** with:

- **Cream paper canvas** (#FDFBF9) — warm white ground tone
- **Marker Orange accent** (#FF6F1E) — signature color for captions and highlights
- **Cocoa Ink headlines** (#2B1A07) — warm dark for typography
- **Pill-shaped buttons** with charcoal borders on cream
- **Rounded corners** (20px for buttons, 12px for cards)
- **Decorative sticker illustrations** — lightning bolts, hearts, sparkles
- **Hand-drawn aesthetic** — arrows, captions, and organic layouts

### Color Palette

| Name | Hex | Usage |
|------|-----|-------|
| Cream Paper | `#FDFBF9` | Canvas, card surfaces |
| Charcoal | `#171717` | Borders, text, structural edges |
| Cocoa Ink | `#2B1A07` | Headlines, decorative borders |
| Marker Orange | `#FF6F1E` | Captions, highlights, accents |
| Dew Drop | `#F7EFE9` | Secondary surface tint |
| Sky Sticker | `#3B82F6` | Decorative only |
| Bubblegum | `#FF66CF` | Decorative only |
| Sprout | `#22C55E` | Decorative only |

## 🏗️ Tech Stack

- **Kotlin 2.0** — Modern, concise Android development
- **Jetpack Compose** — Declarative UI toolkit
- **Material 3** — Design system foundation
- **Room Database** — Local persistence with SQLite
- **Navigation Compose** — Type-safe navigation
- **Coroutines & Flow** — Asynchronous programming
- **MVVM Architecture** — Clean separation of concerns

## 📱 Screenshots

_Coming soon — build the app to see it in action!_

## 🚀 Getting Started

### Prerequisites

- Android Studio Koala (2024.1.1) or later
- JDK 17
- Android SDK 35
- Gradle 8.7

### Build Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/Manmathdev/Note.git
   cd Note
   ```

2. Open in Android Studio or build from command line:
   ```bash
   ./gradlew assembleDebug
   ```

3. Install on a device or emulator:
   ```bash
   ./gradlew installDebug
   ```

### Download APK

Every push to `main` or `arena/**` branches triggers a CI build. Download the latest APK from:

**[GitHub Actions → Build APK → Artifacts](https://github.com/Manmathdev/Note/actions)**

## 📂 Project Structure

```
app/src/main/java/dev/manmath/note/
├── data/                    # Data layer
│   ├── Note.kt             # Room entity
│   ├── NoteDao.kt          # Data access object
│   ├── NoteDatabase.kt     # Room database
│   └── NoteRepository.kt   # Repository pattern
├── viewmodel/
│   └── NoteViewModel.kt    # Business logic
├── ui/
│   ├── theme/              # Superr design system
│   │   ├── Color.kt        # Color tokens
│   │   ├── Type.kt         # Typography scale
│   │   ├── Shape.kt        # Border radius tokens
│   │   └── Theme.kt        # Material theme
│   ├── components/         # Reusable UI components
│   │   ├── PillButton.kt   # Primary CTA
│   │   ├── NoteCard.kt     # Note preview card
│   │   └── StickerDecoration.kt  # Decorative elements
│   ├── screens/            # App screens
│   │   ├── NoteListScreen.kt     # Home screen
│   │   └── NoteEditScreen.kt     # Editor screen
│   └── navigation/
│       └── NoteNavHost.kt  # Navigation graph
├── MainActivity.kt         # Entry point
└── NoteApplication.kt      # Application class
```

## 🎯 Design Principles

Following the Superr design system:

- ✅ **Lowercase headlines** — never capitalize
- ✅ **Pill buttons with borders** — no filled CTAs
- ✅ **Cream paper canvas** — never pure white
- ✅ **Photographed objects** — notebooks, labels, stickers
- ✅ **Random sticker rotations** — 5-15° for hand-placed feel
- ✅ **Whisper-light shadows** — rgba(0,0,0,0.06) max
- ❌ **No gradients or glassmorphism**
- ❌ **No neon accents**
- ❌ **Sticker colors are decorative only** — never for functional UI

## 🔄 CI/CD

GitHub Actions automatically builds the APK on every push:

- **Workflow**: `.github/workflows/build.yml`
- **Trigger**: Push to `main` or `arena/**` branches
- **Artifacts**: Debug APK + build reports
- **Retention**: 30 days for APK, 7 days for reports

### Build Artifacts

After a successful build, download the APK:

1. Go to [Actions tab](https://github.com/Manmathdev/Note/actions)
2. Click on the latest workflow run
3. Scroll to "Artifacts" section
4. Download `SuperrNotes-Debug`

## 🛠️ Development

### Add a New Feature

1. Create a new screen in `ui/screens/`
2. Add navigation route in `NoteNavHost.kt`
3. Update `NoteViewModel.kt` with business logic
4. Follow Superr design tokens for styling

### Customize Colors

Edit `ui/theme/Color.kt`:

```kotlin
val MarkerOrange = Color(0xFFFF6F1E)
val CocoaInk = Color(0xFF2B1A07)
// ... add your custom colors
```

### Add New Components

Follow the component pattern in `ui/components/`:

```kotlin
@Composable
fun MyComponent(modifier: Modifier = Modifier) {
    // Use Superr tokens
    Surface(
        color = CreamPaper,
        shape = CardShape,
        border = BorderStroke(1.dp, Charcoal)
    ) {
        // Content
    }
}
```

## 📄 License

This project is open source and available under the MIT License.

## 🙏 Acknowledgments

- **Superr Design System** — Warm schoolyard aesthetic
- **Jetpack Compose** — Modern Android UI toolkit
- **Room** — Local database abstraction
- **Material Design 3** — Design foundation

## 📧 Contact

For questions, issues, or contributions:

- **GitHub Issues**: [Report a bug](https://github.com/Manmathdev/Note/issues)
- **Pull Requests**: Contributions welcome!

---

Made with ❤️ and Jetpack Compose
