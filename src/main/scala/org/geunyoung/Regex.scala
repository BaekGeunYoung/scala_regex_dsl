package org.geunyoung

sealed trait Regex

object Regex {
  case class Literal(literal: String)                          extends Regex
  case class Grouped(regex: Regex)                             extends Regex
  case class Concatenated(regex1: Regex, regex2: Regex)        extends Regex
  case object AnyChar                                          extends Regex // except newline
  case object Word                                             extends Regex // alphabet & underscore
  case object Digit                                            extends Regex
  case object WhiteSpace                                       extends Regex
  case object NotWord                                          extends Regex
  case object NotDigit                                         extends Regex
  case object NotWhiteSpace                                    extends Regex
  case class AnyOf(candidates: Seq[Regex])                     extends Regex
  case class NotAnyOf(candidates: Seq[Regex])                  extends Regex
  case class InRangeOf(from: Char, to: Char)                   extends Regex
  case class BeginWith(regex: Regex)                           extends Regex
  case class EndWith(regex: Regex)                             extends Regex
  case object WordBoundary                                     extends Regex
  case object NotWordBoundary                                  extends Regex
  case object Tab                                              extends Regex
  case object NewLine                                          extends Regex
  case class QuantifiedRange(regex: Regex, min: Int, max: Int) extends Regex
  case class QuantifiedMin(regex: Regex, min: Int)             extends Regex
  case class QuantifiedExact(regex: Regex, quantity: Int)      extends Regex
  case class OneOrMore(regex: Regex)                           extends Regex
  case class ZeroOrMore(regex: Regex)                          extends Regex
  case class Optional(regex: Regex)                            extends Regex
}
