package org.geunyoung

import org.geunyoung.adt.Regex

trait RegexMatcherSyntax {
  implicit class RegexMatcherOps(regex: Regex) {
    def matches(src: String): Boolean       = regexInterpreter.interpret(regex).matches(src)
    def findAllIn(src: String): Seq[String] = regexInterpreter.interpret(regex).findAllIn(src).map(identity).toSeq
  }
}
