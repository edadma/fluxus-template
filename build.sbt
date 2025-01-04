ThisBuild / licenses += "MIT"  -> url("https://opensource.org/licenses/MIT")
ThisBuild / versionScheme      := Some("semver-spec")
ThisBuild / evictionErrorLevel := Level.Warn

lazy val fluxus_template = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
//  .enablePlugins(ScalablyTypedConverterPlugin)
  .settings(
    name         := "fluxus-template",
    version      := "0.0.1",
    scalaVersion := "3.6.2",
    organization := "io.github.edadma",
    jsEnv        := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0",
      "io.github.edadma"  %%% "fluxus"          % "0.0.1",
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    publishMavenStyle      := true,
    Test / publishArtifact := false,
    licenses += "ISC"      -> url("https://opensource.org/licenses/ISC"),
  )
