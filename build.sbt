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
    scalaVersion := "3.6.4",
    organization := "io.github.edadma",
    jsEnv        := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time"   % "2.6.0",
      "io.github.edadma"  %%% "fluxus"            % "0.0.29",
      "io.github.edadma"  %%% "fluxus-remix"      % "0.0.2",
      "io.github.edadma"  %%% "fluxus-daisyui"    % "0.0.4",
      "io.github.edadma"  %%% "fluxus-querystate" % "0.0.2",
      "io.github.edadma"  %%% "fluxus-i18n"       % "0.0.2",
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    publishMavenStyle      := true,
    Test / publishArtifact := false,
    licenses += "ISC"      -> url("https://opensource.org/licenses/ISC"),
  )
