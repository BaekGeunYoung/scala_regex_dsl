package org.geunyoung

import zio.{EnvironmentTag, Scope, ZEnv, ZIO, ZIOApp, ZIOAppArgs, ZLayer}
import zio.Console.printLine

object Main extends ZIOApp {
  override def run: ZIO[Main.Environment with ZIOAppArgs with Scope, Any, Any] = {
    val src     = "qweqweasd@asfsa.qweqwe zxcvxcvz@qweqwe.qweqwe zxczc@qwoiej.xcvoij"
    val matcher = new RegexMatcherImpl(Example.email, src)

    val src2     = "2016-07-08T12:30:00Z"
    val matcher2 = new RegexMatcherImpl(Example.iso8601Format, src2)

    printLine(matcher2.findAll)
  }

  override implicit def tag: zio.EnvironmentTag[ZEnv] = EnvironmentTag[ZEnv]

  override type Environment = ZEnv

  override def layer: ZLayer[ZIOAppArgs with Scope, Any, ZEnv] = ZEnv.live
}
