package org.geunyoung

import zio.{EnvironmentTag, Scope, ZEnv, ZIO, ZIOApp, ZIOAppArgs, ZLayer}
import zio.Console.printLine

object Main extends ZIOApp {
  override def run: ZIO[Main.Environment with ZIOAppArgs with Scope, Any, Any] =
    printLine(Example.emailRegex) *> printLine(Example.emailRegex2) *> printLine(Example.emailRegex3)

  override implicit def tag: zio.EnvironmentTag[ZEnv] = EnvironmentTag[ZEnv]

  override type Environment = ZEnv

  override def layer: ZLayer[ZIOAppArgs with Scope, Any, ZEnv] = ZEnv.live
}
