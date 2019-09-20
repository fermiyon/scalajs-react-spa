import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._

/**
  * Application settings. Configure the build for your application here.
  * You normally don't have to touch the actual build definition after this.
  */
object Settings {

  /** Declare global dependency versions here to avoid mismatches in multi part dependencies */
  object v {
    /** The version of your application */
    val app = "1.1.5"

    val scala = "2.12.9"
    val scalajsDom = "0.9.7"
    val scalajsReact = "1.5.0-RC2"
    val scalaCSS = "0.6.0-RC1"
    val log4js = "1.4.13-1"
    val autowire = "0.2.6"
    val booPickle = "1.3.1"
    val diode = "1.1.5"
    val diodeReact = "1.1.5.142"
    val uTest = "0.7.1"

    val react = "16.8.6"
    val jQuery = "3.4.1"
    val bootstrap = "4.3.1"
    val chartjs = "2.6.0"
    val fontawesome = "4.7.0"

    val scalajsScripts = "1.1.4"
    val silencer = "1.4.1"
  }

  /** The name of your application */
  val name = "scalajs-spa"

  /** Options for the scala compiler */
  val scalacOptions = Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature",
    // silence all warnings on autogenerated files
    "-P:silencer:pathFilters=target/.*"
  )

  /**
    * These dependencies are shared between JS and JVM projects
    * the special %%% function selects the correct version for each project
    */
  val sharedDependencies = Def.setting(Seq(
    "com.lihaoyi" %%% "autowire" % v.autowire,
    "io.suzaku" %%% "boopickle" % v.booPickle
  ))

  /** Dependencies only used by the JVM project */
  val jvmDependencies = Def.setting(Seq(
    "com.vmunier" %% "scalajs-scripts" % v.scalajsScripts,
    "org.webjars.bower" % "fontawesome" % v.fontawesome % Provided,
    "org.webjars.npm" % "bootstrap" % v.bootstrap % Provided,
    "com.lihaoyi" %% "utest" % v.uTest % Test,
    compilerPlugin("com.github.ghik" %% "silencer-plugin" % v.silencer),
    "com.github.ghik" %% "silencer-lib" % v.silencer % Provided
  ))

  /** Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val scalajsDependencies = Def.setting(Seq(
    "com.github.japgolly.scalajs-react" %%% "core" % v.scalajsReact,
    "com.github.japgolly.scalajs-react" %%% "extra" % v.scalajsReact,
    "com.github.japgolly.scalacss" %%% "ext-react" % v.scalaCSS,
    "io.suzaku" %%% "diode" % v.diode,
    "io.suzaku" %%% "diode-react" % v.diodeReact,
    "org.scala-js" %%% "scalajs-dom" % v.scalajsDom,
    "com.lihaoyi" %%% "utest" % v.uTest % Test,
    compilerPlugin("com.github.ghik" %% "silencer-plugin" % v.silencer),
    "com.github.ghik" %% "silencer-lib" % v.silencer % Provided
  ))

  /** Dependencies for external JS libs that are bundled into a single .js file according to dependency order */
  val jsDependencies = Def.setting(Seq(
    "org.webjars.npm" % "react" % v.react / "umd/react.development.js" minified "umd/react.production.min.js" commonJSName "React",
    "org.webjars.npm" % "react-dom" % v.react / "umd/react-dom.development.js" minified "umd/react-dom.production.min.js" dependsOn "umd/react.development.js" commonJSName "ReactDOM",
    "org.webjars.npm" % "react-dom" % v.react / "umd/react-dom-server.browser.development.js" minified "umd/react-dom-server.browser.production.min.js" dependsOn "umd/react-dom.development.js" commonJSName "ReactDOMServer",
    "org.webjars.npm" % "jquery" % v.jQuery / "dist/jquery.js" minified "jquery.min.js",
    "org.webjars.npm" % "bootstrap" % v.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn "dist/jquery.js",
    "org.webjars.bower" % "chartjs" % v.chartjs / "Chart.js" minified "Chart.min.js",
    "org.webjars" % "log4javascript" % v.log4js / "js/log4javascript_uncompressed.js" minified "js/log4javascript.js"
  ))

  /** Fixes unresolved deps issue: https://github.com/webjars/webjars/issues/1789 **/
  val dependencyOverrides = Def.setting(Seq(
    "org.webjars.npm" % "js-tokens" % "4.0.0",
    "org.webjars.npm" % "scheduler" % "0.14.0"
  ))
}
