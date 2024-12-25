# Fluxus Template

Get started building Scala.js applications with Fluxus + Vite. This template comes with:

- ⚡️ Vite + Scala.js integration for fast development
- 🎨 DaisyUI components with dark mode support
- 🖼️ Lucide icons ready to use
- 🔄 HMR (Hot Module Replacement)

## Prerequisites

- [sbt](https://www.scala-sbt.org/download.html) (Scala Build Tool)
- [Node.js](https://nodejs.org/) (v16 or newer)

## Quick Start

1. Create a new repository from this template
2. Clone your new repository
3. Install dependencies:
```bash
npm install
```
4. Start the development server:
```bash
npm run dev
```

## Available Scripts

- `npm run dev` - Start development server with HMR
- `npm run build` - Build for production
- `npm run preview` - Preview production build locally
- `npm run tailwind:build` - Build CSS with Tailwind/DaisyUI (minified)
- `npm run tailwind:watch` - Watch mode for Tailwind CSS development

## Using Components

### Basic Component
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

### Using Icons
```scala
div(
  cls := "flex items-center gap-2",
  i("data-lucide" := "heart"),  // Renders a heart icon
  "Like"
)
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

## What's Next?

- Browse the [Fluxus Documentation](https://github.com/edadma/fluxus) for detailed guides
- Explore [DaisyUI components](https://daisyui.com/components/)
- Check out available [Lucide icons](https://lucide.dev/icons/)

## Template Structure
```
├── src/
│   └── main/
│       ├── scala/
│       │   └── Main.scala   # Entry point
│       └── resources/
│           └── styles/
│               └── tailwind.css   # Tailwind entry point
├── public/                 # Static assets
├── index.html              # HTML template
├── build.sbt               # Scala.js build config
├── package.json            # Node dependencies
├── vite.config.js          # Vite configuration
└── tailwind.config.js      # Tailwind/DaisyUI config
```

