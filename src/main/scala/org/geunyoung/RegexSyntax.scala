package org.geunyoung

import scala.language.implicitConversions

import org.geunyoung.Regex._

trait RegexSyntax {
  implicit def literal(str: String): Regex = Literal(str)

  implicit class RegexOps(val regex: Regex) {
    def concatWith(regex2: Regex): Regex = Concatenated(regex, regex2)

    def ++(regex2: Regex): Regex = Concatenated(regex, regex2)

    def oneOrMore: Regex = OneOrMore(regex)
  }
}
