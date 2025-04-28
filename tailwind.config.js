// tailwind.config.js

module.exports = {
  // Specify the paths to all of your template files
  content: [
    './target/**/*.js', // Your Scala.js source files
    './index.html',         // Your HTML files
  ],
  // Extend the default Tailwind CSS configuration
  theme: {
    extend: {
      fontFamily: {
        'poppins': ['Poppins', 'sans-serif'],
      }
    },
  },
};
