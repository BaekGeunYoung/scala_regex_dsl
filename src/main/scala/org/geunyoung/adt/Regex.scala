package org.geunyoung.adt

sealed trait Regex

object Regex {
  case object Empty                                     extends Regex
  case object AnyChar                                   extends Regex // except newline
  sealed trait CharSpecifier                            extends Regex
  sealed trait Quantified                               extends Regex
  sealed trait Anchored                                 extends Regex
  sealed trait Set                                      extends Regex
  case class Grouped(regex: Regex)                      extends Regex
  case class Concatenated(regex1: Regex, regex2: Regex) extends Regex
  case class Or(regex1: Regex, regex2: Regex)           extends Regex

  object CharSpecifier {
    case class Literal(char: Char)             extends CharSpecifier
    case object Word                           extends CharSpecifier // alphabet & underscore
    case object Digit                          extends CharSpecifier
    case object WhiteSpace                     extends CharSpecifier
    case object NotWord                        extends CharSpecifier
    case object NotDigit                       extends CharSpecifier
    case object NotWhiteSpace                  extends CharSpecifier
    case object Tab                            extends CharSpecifier
    case object NewLine                        extends CharSpecifier
    case class InRangeOf(from: Char, to: Char) extends CharSpecifier
  }

  object Quantified {
    case class QuantifiedRange(regex: Regex, min: Int, max: Int) extends Quantified
    case class QuantifiedMin(regex: Regex, min: Int)             extends Quantified
    case class QuantifiedExact(regex: Regex, quantity: Int)      extends Quantified
    case class OneOrMore(regex: Regex)                           extends Quantified
    case class ZeroOrMore(regex: Regex)                          extends Quantified
    case class Optional(regex: Regex)                            extends Quantified
  }

  object Anchored {
    case object WordBoundary    extends Anchored
    case object NotWordBoundary extends Anchored
    case object Beginning       extends Anchored
    case object Ending          extends Anchored
  }

  object Set {
    case class AnyOf(candidates: Seq[CharSpecifier])    extends Set
    case class NotAnyOf(candidates: Seq[CharSpecifier]) extends Set
  }
}
