import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";

export default defineConfig({
  plugins: [scalaJSPlugin()],
  build: {
    // Make sure Vite picks up your CSS file
    assetsInlineLimit: 0
  }
});
