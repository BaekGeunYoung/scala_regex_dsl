package org

import scala.util.matching.{Regex => ScalaRegex}

import org.geunyoung.adt.Regex
import org.geunyoung.adt.Regex._
import org.geunyoung.typeclass.Interpreter

package object geunyoung {
  val regexInterpreter: Interpreter[Regex, ScalaRegex] = new Interpreter[Regex, ScalaRegex] {
    override def interpret(from: Regex): ScalaRegex =
      toString(from).r

    private def toString(from: Regex): String = from match {
      case Regex.Empty                        => ""
      case Regex.AnyChar                      => "."
      case specifier: Regex.CharSpecifier     =>
        specifier match {
          case CharSpecifier.Literal(char)       =>
            if ("+*?^$\\.[]{}()|/".contains(char)) s"\\$char"
            else char.toString
          case CharSpecifier.Word                => "\\w"
          case CharSpecifier.Digit               => "\\d"
          case CharSpecifier.WhiteSpace          => "\\s"
          case CharSpecifier.NotWord             => "\\W"
          case CharSpecifier.NotDigit            => "\\D"
          case CharSpecifier.NotWhiteSpace       => "\\S"
          case CharSpecifier.Tab                 => "\\t"
          case CharSpecifier.NewLine             => "\\n"
          case CharSpecifier.InRangeOf(from, to) => s"[$from-$to]"
        }
      case quantified: Regex.Quantified       =>
        quantified match {
          case Quantified.QuantifiedRange(regex, min, max) => s"${toString(regex)}{$min,$max}"
          case Quantified.QuantifiedMin(regex, min)        => s"${toString(regex)}{$min,}"
          case Quantified.QuantifiedExact(regex, quantity) => s"${toString(regex)}{$quantity}"
          case Quantified.OneOrMore(regex)                 => s"${toString(regex)}+"
          case Quantified.ZeroOrMore(regex)                => s"${toString(regex)}*"
          case Quantified.Optional(regex)                  => s"${toString(regex)}?"
        }
      case anchored: Regex.Anchored           =>
        anchored match {
          case Anchored.WordBoundary    => "\\b"
          case Anchored.NotWordBoundary => "\\B"
          case Anchored.Beginning       => "$"
          case Anchored.Ending          => "^"
        }
      case set: Regex.Set                     =>
        def concat(candidates: Seq[CharSpecifier]): String =
          candidates.foldLeft("") { (acc, r) =>
            val str = r match {
              case CharSpecifier.InRangeOf(from, to) => s"$from-$to"
              case charSpecifier                     => toString(charSpecifier)
            }
            s"$acc$str"
          }

        set match {
          case Set.AnyOf(candidates)    => s"[${concat(candidates)}]"
          case Set.NotAnyOf(candidates) => s"[^${concat(candidates)}]"
        }
      case Regex.Grouped(regex)               => s"(${toString(regex)})"
      case Regex.Concatenated(regex1, regex2) => s"${toString(regex1)}${toString(regex2)}"
      case Regex.Or(regex1, regex2)           => s"(?:${toString(regex1)}|${toString(regex2)})"
    }
  }
}
