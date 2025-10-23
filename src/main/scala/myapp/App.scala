package myapp

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.remix.*
import org.scalajs.dom.document

@main def run(): Unit = render(App, "app")

// Data model for a todo item
case class Todo(id: Int, text: String, completed: Boolean)

// Filter types
enum Filter:
  case All, Active, Completed

def App: FluxusNode = {
  val (todos, setTodos, _) = useState(List.empty[Todo])
  val (inputText, setInputText, _) = useState("")
  val (filter, setFilter, _) = useState(Filter.All)
  val (theme, setTheme, _) = useState("dark")
  val (nextId, setNextId, _) = useState(1)

  def toggleTheme(): Unit = {
    val newTheme = if (theme == "dark") "light" else "dark"
    setTheme(newTheme)
    document.documentElement.setAttribute("data-theme", newTheme)
  }

  def addTodo(): Unit = {
    if (inputText.trim.nonEmpty) {
      val newTodo = Todo(nextId, inputText.trim, false)
      setTodos(todos :+ newTodo)
      setInputText("")
      setNextId(nextId + 1)
    }
  }

  def toggleTodo(id: Int): Unit = {
    setTodos(todos.map(t => if (t.id == id) t.copy(completed = !t.completed) else t))
  }

  def deleteTodo(id: Int): Unit = {
    setTodos(todos.filter(_.id != id))
  }

  def clearCompleted(): Unit = {
    setTodos(todos.filter(!_.completed))
  }

  // Filter todos based on current filter
  val filteredTodos = filter match {
    case Filter.All       => todos
    case Filter.Active    => todos.filter(!_.completed)
    case Filter.Completed => todos.filter(_.completed)
  }

  // Statistics
  val totalTodos = todos.length
  val completedTodos = todos.count(_.completed)
  val activeTodos = totalTodos - completedTodos
  val progressPercent = if (totalTodos > 0) (completedTodos * 100) / totalTodos else 0

  div(
    cls := "min-h-screen flex flex-col items-center py-8 px-4 bg-base-100",
    // Theme switcher
    div(
      cls := "fixed top-4 right-4",
      label(
        cls := "swap swap-rotate relative h-10 w-10",
        input(
          typ      := "checkbox",
          cls      := "theme-controller",
          checked  := theme == "dark",
          onChange := (() => toggleTheme()),
        ),
        svg(
          cls     := "swap-off h-10 w-10 fill-current",
          xmlns   := "http://www.w3.org/2000/svg",
          viewBox := "0 0 24 24",
          path(
            "d" := "M21.64,13a1,1,0,0,0-1.05-.14,8.05,8.05,0,0,1-3.37.73A8.15,8.15,0,0,1,9.08,5.49a8.59,8.59,0,0,1,.25-2A1,1,0,0,0,8,2.36,10.14,10.14,0,1,0,22,14.05,1,1,0,0,0,21.64,13Zm-9.5,6.69A8.14,8.14,0,0,1,7.08,5.22v.27A10.15,10.15,0,0,0,17.22,15.63a9.79,9.79,0,0,0,2.1-.22A8.11,8.11,0,0,1,12.14,19.73Z",
          ),
        ),
        svg(
          cls     := "swap-on h-10 w-10 fill-current",
          xmlns   := "http://www.w3.org/2000/svg",
          viewBox := "0 0 24 24",
          path(
            "d" := "M5.64,17l-.71.71a1,1,0,0,0,0,1.41,1,1,0,0,0,1.41,0l.71-.71A1,1,0,0,0,5.64,17ZM5,12a1,1,0,0,0-1-1H3a1,1,0,0,0,0,2H4A1,1,0,0,0,5,12Zm7-7a1,1,0,0,0,1-1V3a1,1,0,0,0-2,0V4A1,1,0,0,0,12,5ZM5.64,7.05a1,1,0,0,0,.7.29,1,1,0,0,0,.71-.29,1,1,0,0,0,0-1.41l-.71-.71A1,1,0,0,0,4.93,6.34Zm12,.29a1,1,0,0,0,.7-.29l.71-.71a1,1,0,1,0-1.41-1.41L17,5.64a1,1,0,0,0,0,1.41A1,1,0,0,0,17.66,7.34ZM21,11H20a1,1,0,0,0,0,2h1a1,1,0,0,0,0-2Zm-9,8a1,1,0,0,0-1,1v1a1,1,0,0,0,2,0V20A1,1,0,0,0,12,19ZM18.36,17A1,1,0,0,0,17,18.36l.71.71a1,1,0,0,0,1.41,0,1,1,0,0,0,0-1.41ZM12,6.5A5.5,5.5,0,1,0,17.5,12,5.51,5.51,0,0,0,12,6.5Zm0,9A3.5,3.5,0,1,1,15.5,12,3.5,3.5,0,0,1,12,15.5Z",
          ),
        ),
      ),
    ),
    // Header
    div(
      cls := "w-full max-w-2xl text-center mb-8",
      div(
        cls := "flex items-center justify-center gap-3 mb-4",
        CheckSquareLine <> CheckSquareLineProps(size = Some(48), cls = Some("text-primary")),
        h1(
          cls := "text-5xl font-poppins font-bold text-base-content",
          "Fluxus Todo",
        ),
      ),
      p(
        cls := "text-base-content/60",
        "A reactive todo list built with Fluxus + DaisyUI",
      ),
    ),
    // Main content card
    div(
      cls := "card bg-base-200/50 w-full max-w-2xl shadow-xl",
      div(
        cls := "card-body",
        // Statistics and Progress
        if (totalTodos > 0) {
          div(
            cls := "mb-6",
            div(
              cls := "flex justify-between items-center mb-2",
              div(
                cls := "flex gap-4",
                div(
                  cls := "badge badge-primary badge-lg",
                  s"$totalTodos Total",
                ),
                div(
                  cls := "badge badge-success badge-lg",
                  s"$completedTodos Done",
                ),
                div(
                  cls := "badge badge-warning badge-lg",
                  s"$activeTodos Active",
                ),
              ),
              div(
                cls := "text-base-content/60 font-semibold",
                s"$progressPercent%",
              ),
            ),
            div(
              cls := "w-full bg-base-300 rounded-full h-2.5",
              div(
                cls   := "bg-primary h-2.5 rounded-full transition-all duration-300",
                style := s"width: $progressPercent%",
              ),
            ),
          )
        } else {
          div()
        },
        // Input form
        div(
          cls := "form-control mb-6",
          div(
            cls := "flex gap-2",
            input(
              typ         := "text",
              placeholder := "What needs to be done?",
              cls         := "input input-bordered flex-1",
              value       := inputText,
              onInput     := ((e: dom.Event) => setInputText(e.target.value)),
              onKeyPress  := ((e: dom.KeyboardEvent) => if (e.key == "Enter") addTodo()),
            ),
            button(
              cls     := "btn btn-primary",
              onClick := (() => addTodo()),
              PlusLine <> PlusLineProps(),
              "Add",
            ),
          ),
        ),
        // Filter buttons
        div(
          cls := "flex gap-2 mb-4",
          button(
            cls     := s"btn btn-sm ${if (filter == Filter.All) "btn-active" else ""}",
            onClick := (() => setFilter(Filter.All)),
            "All",
          ),
          button(
            cls     := s"btn btn-sm ${if (filter == Filter.Active) "btn-active" else ""}",
            onClick := (() => setFilter(Filter.Active)),
            "Active",
          ),
          button(
            cls     := s"btn btn-sm ${if (filter == Filter.Completed) "btn-active" else ""}",
            onClick := (() => setFilter(Filter.Completed)),
            "Completed",
          ),
          if (completedTodos > 0) {
            button(
              cls     := "btn btn-sm btn-ghost ml-auto",
              onClick := (() => clearCompleted()),
              Trash2Line <> Trash2LineProps(size = Some(16)),
              "Clear Completed",
            )
          } else {
            div()
          },
        ),
        // Todo list
        if (filteredTodos.isEmpty) {
          div(
            cls := "text-center py-12 text-base-content/40",
            div(
              cls := "flex flex-col items-center gap-3",
              if (todos.isEmpty) {
                CheckSquareLine <> CheckSquareLineProps(size = Some(64), cls = Some("opacity-30"))
              } else {
                InboxLine <> InboxLineProps(size = Some(64), cls = Some("opacity-30"))
              },
              p(
                cls := "text-lg",
                if (todos.isEmpty) {
                  "No todos yet. Add one above!"
                } else {
                  filter match {
                    case Filter.Active    => "No active todos"
                    case Filter.Completed => "No completed todos"
                    case _                => "No todos"
                  }
                },
              ),
            ),
          )
        } else {
          div(
            cls := "space-y-2",
            filteredTodos.map { todo =>
              div(
                key := s"todo-${todo.id}",
                cls := s"flex items-center gap-3 p-3 rounded-lg bg-base-100 hover:bg-base-300/50 transition-colors ${if (todo.completed) "opacity-60" else ""}",
                div(
                  cls := "form-control",
                  input(
                    typ     := "checkbox",
                    cls     := "checkbox checkbox-primary",
                    checked := todo.completed,
                    onChange := (() => toggleTodo(todo.id)),
                  ),
                ),
                div(
                  cls := s"flex-1 ${if (todo.completed) "line-through text-base-content/50" else "text-base-content"}",
                  todo.text,
                ),
                button(
                  cls     := "btn btn-ghost btn-sm btn-square",
                  onClick := (() => deleteTodo(todo.id)),
                  Trash2Line <> Trash2LineProps(size = Some(18)),
                ),
              )
            }*,
          )
        },
      ),
    ),
    // Footer
    div(
      cls := "mt-8 text-center text-base-content/40 flex flex-col items-center gap-2",
      p(
        cls := "flex items-center gap-2",
        BookOpenLine <> BookOpenLineProps(size = Some(16)),
        "Built with Vite, Fluxus, and DaisyUI",
      ),
      p(
        cls := "text-sm",
        "Edit ",
        code(cls := "bg-base-300 rounded px-2 py-1", "src/main/scala/myapp/App.scala"),
        " to test HMR",
      ),
    ),
  )
}
