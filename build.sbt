import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / licenses += "ISC" -> url("https://opensource.org/licenses/ISC")
ThisBuild / versionScheme     := Some("semver-spec")

publish / skip := true

lazy val fluxus_template = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
//  .enablePlugins(ScalablyTypedConverterPlugin)
  .settings(
    name                                        := "fluxus-template",
    version                                     := "0.0.1",
    scalaVersion                                := "3.6.2",
    organization                                := "io.github.edadma",
    githubOwner                                 := "edadma",
    githubRepository                            := name.value,
    libraryDependencies += "io.github.cquiroz" %%% "scala-java-time" % "2.6.0",
    libraryDependencies += "io.github.edadma"  %%% "fluxus"          % "0.0.1",
    //    libraryDependencies += "com.lihaoyi" %%% "pprint" % "0.9.0" % "test",
    jsEnv                           := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    scalaJSLinkerConfig ~= { _.withModuleSplitStyle(ModuleSplitStyle.SmallestModules) },
    scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    publishMavenStyle      := true,
    Test / publishArtifact := false,
    licenses += "ISC"      -> url("https://opensource.org/licenses/ISC"),
  )
