package org.geunyoung

import zio.{EnvironmentTag, Scope, ZEnv, ZIO, ZIOApp, ZIOAppArgs, ZLayer}
import zio.Console.printLine

object Main extends ZIOApp with RegexMatcherSyntax {
  override def run: ZIO[Main.Environment with ZIOAppArgs with Scope, Any, Any] = {
    val src2 = "2016-07-08T12:30:00Z"

    printLine(Example.iso8601Format.matches(src2)) *>
      printLine(Example.iso8601Format.findAllIn(src2))
  }

  override implicit def tag: zio.EnvironmentTag[ZEnv] = EnvironmentTag[ZEnv]

  override type Environment = ZEnv

  override def layer: ZLayer[ZIOAppArgs with Scope, Any, ZEnv] = ZEnv.live
}
