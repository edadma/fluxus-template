package myapp

import io.github.edadma.fluxus.*

@main def run(): Unit = render(App, "app")

def App: FluxusNode = {
  val (count, setCount, _) = useState(0)

  div(
    cls := "min-h-screen flex flex-col items-center justify-center gap-16 bg-base-100",
    div(
      cls := "flex gap-8 items-center",
      a(
        href   := "https://vitejs.dev",
        target := "_blank",
        img(src := "/vite.svg", cls := "h-24 hover:drop-shadow-[0_0_2em_#646cffaa]"),
      ),
      a(
        href   := "https://github.com/edadma/fluxus",
        target := "_blank",
        img(src := "/fluxus.png", cls := "h-24 hover:drop-shadow-[0_0_2em_#61dafbaa]"),
      ),
      a(
        href   := "https://daisyui.com",
        target := "_blank",
        img(src := "/daisyui.svg", cls := "h-24 scale-110 hover:drop-shadow-[0_0_2em_#61dafbaa]"),
      ),
    ),
    h1(
      cls := "text-5xl font-bold text-base-content",
      "Vite + React + DaisyUI",
    ),
    div(
      cls := "card bg-base-200/50 p-4",
      div( // Added wrapper div for button to control width
        cls := "flex justify-center", // Center the button
        button(
          cls     := "btn btn-primary w-fit",
          onClick := (() => setCount(count + 1)),
          s"count is $count",
        ),
      ),
      p(
        cls := "mt-4 text-base-content",
        "Edit ",
        code(cls := "bg-base-300 rounded px-2 py-1", "src/main/scala/myapp/Main.scala"),
        " and save to test HMR",
      ),
    ),
    p(
      cls := "text-base-content/60 flex items-center gap-2",
      i("data-lucide" := "book-open"),
      "Click on the Vite and Fluxus logos to learn more",
    ),
  )
}
