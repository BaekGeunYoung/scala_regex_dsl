package org.geunyoung

import scala.language.implicitConversions

import org.geunyoung.Regex._
import org.geunyoung.Regex.CharSpecifier.{InRangeOf, Literal}
import org.geunyoung.Regex.Quantified.{OneOrMore, Optional, ZeroOrMore}

trait RegexSyntax {
  implicit def literal(char: Char): Regex  = Literal(char)
  implicit def literal(str: String): Regex = str.foldLeft(Empty: Regex) { (acc, r) => acc ++ Literal(r) }

  implicit class RegexOps(val regex: Regex) {
    def concatWith(regex2: Regex): Regex = Concatenated(regex, regex2)

    def oneOrMore: Regex  = OneOrMore(regex)
    def optional: Regex   = Optional(regex)
    def zeroOrMore: Regex = ZeroOrMore(regex)

    def quantified(min: Int, max: Int): Regex = Quantified.QuantifiedRange(regex, min, max)
    def quantifiedMin(min: Int): Regex        = Quantified.QuantifiedMin(regex, min)
    def quantifiedExact(quantity: Int): Regex = Quantified.QuantifiedExact(regex, quantity)

    def or(regex2: Regex): Regex = Or(regex, regex2)

    def ++(regex2: Regex): Regex = Concatenated(regex, regex2)
  }

  implicit class CharRegexOps(val char: Char) {
    def ~(char2: Char): Regex = InRangeOf(char, char2)
    def r: Regex              = Literal(char)
  }
}
