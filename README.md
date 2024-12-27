# Fluxus Template

Get started building Scala.js applications with Fluxus + Vite + Tailwind/DaisyUI. This template comes with:

- ⚡️ Vite + Scala.js integration for fast development
- 🎨 DaisyUI components with dark mode support
- 🖼️ Lucide icons ready to use
- 🔄 Live reload development

## Prerequisites

- [sbt](https://www.scala-sbt.org/download.html) (Scala Build Tool)
- [Node.js](https://nodejs.org/) (v16 or newer)
- Basic familiarity with Scala and web development

## Quick Start

1. Create a new repository from this template
2. Clone your new repository
3. Install Node.js dependencies:
```bash
npm install
```

## Development Workflow

You'll need to run three processes in separate terminals for development:

### 1. Scala.js Compilation
```bash
sbt ~fastLinkJS
```
This watches your Scala files and recompiles automatically on changes.

### 2. Tailwind CSS Compilation
```bash
npm run tailwind:watch
```
This watches your components and recompiles CSS when Tailwind classes are added/removed.

### 3. Development Server
```bash
npm run dev
```
Visit http://localhost:5173 to see your app.

## Available Scripts

- `npm run dev` - Start development server with HMR
- `npm run build` - Build for production
- `npm run preview` - Preview production build locally
- `npm run tailwind:build` - Build CSS with Tailwind/DaisyUI (minified)
- `npm run tailwind:watch` - Watch mode for Tailwind CSS development

## Using Components

### Basic Stateful Component
```scala
def Counter: () => FluxusNode = () => {
  val (count, setCount, _) = useState(0)
  
  div(
    cls := "flex flex-col items-center gap-4",
    p(s"Count: $count"),
    button(
      cls := "btn btn-primary",
      onClick := (() => setCount(count + 1)),
      "Increment"
    )
  )
}
```

### DaisyUI Components
```scala
div(
  cls := "card w-96 bg-base-100 shadow-xl",
  div(
    cls := "card-body",
    h2(cls := "card-title", "Hello Fluxus!"),
    p("Build beautiful UIs with DaisyUI components"),
    div(
      cls := "card-actions justify-end",
      button(cls := "btn btn-primary", "Get Started")
    )
  )
)
```

## Icons

Fluxus includes all icons from the [Lucide](https://lucide.dev) icon library (1500+ icons). The icons are tree-shakeable - only the icons you actually use will be included in your final bundle.

### Usage

Import any icon from the `icons` package:

```scala
import io.github.edadma.fluxus.icons.BookOpen  // or any other icon

// Inside your component:
BookOpen()                                     // default size (24px) and color (currentColor)
BookOpen(color = "#FF0000")                   // custom color
BookOpen(size = 48)                           // custom size
BookOpen(color = "#FF0000", size = 48)        // both custom color and size
```

### Available Icons

Browse the complete collection of available icons at [lucide.dev/icons](https://lucide.dev/icons). The icon names in Scala are the PascalCase versions of the kebab-case names shown on the Lucide website.

Examples:
- `book-open` → `BookOpen`
- `arrow-up-right` → `ArrowUpRight`
- `chevron-right` → `ChevronRight`

### Styling

Icons inherit their color from the current text color by default (`currentColor`). You can:
- Style them using the `color` parameter
- Style them using CSS (the icon inherits the color of its parent element)
- Adjust size using the `size` parameter (applies to both width and height)

### Example in a Component

Here's a complete example showing an icon in a navigation component:

```scala
def Navigation = () => {
  div(
    cls := "flex items-center gap-2",
    // Icon inherits color from parent text color
    span(
      cls := "text-blue-500 flex items-center",
      BookOpen(),  // Default size, inherits blue color
      "Documentation"
    ),
    // Custom colored icon
    ChevronRight(
      color = "#FF0000",
      size = 16
    )
  )
}
```

### Bundle Size Optimization

The icons are automatically tree-shaken by build tools like Vite. This means:
- Only icons you import and use will be included in your final bundle
- Unused icons are automatically removed during the build process
- Each icon is roughly 1KB before minification

## What's Next?

- Browse the [Fluxus Documentation](https://github.com/edadma/fluxus) for detailed guides
- Explore [DaisyUI components](https://daisyui.com/components/)
- Check out available [Lucide icons](https://lucide.dev/icons/)

## Project Structure
```
├── src/
│   └── main/
│       ├── scala/
│       │   └── Main.scala         # Entry point
│       └── resources/
│           └── styles/
│               └── tailwind.css   # Tailwind entry point
├── public/                        # Static assets
├── index.html                     # HTML template
├── output.css                     # Generated CSS
├── build.sbt                      # Scala.js build config
├── package.json                   # Node dependencies
├── vite.config.js                 # Vite configuration
└── tailwind.config.js             # Tailwind/DaisyUI config
```
