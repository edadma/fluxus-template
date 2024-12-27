// tailwind.config.js

module.exports = {
  // Specify the paths to all of your template files
  content: [
    './target/scala-3.6.2/fluxus-template-fastopt/**/*.js', // Your Scala.js source files
    './index.html',         // Your HTML files
  ],
  daisyui: {
      themes: ["light", "dark", "night", "cupcake"],
  },
  // Extend the default Tailwind CSS configuration
  theme: {
    extend: {
      fontFamily: {
        'poppins': ['Poppins', 'sans-serif'],
      }
    },
  },
  // Add DaisyUI as a plugin
  plugins: [require("@tailwindcss/typography"), require('daisyui')],
};
