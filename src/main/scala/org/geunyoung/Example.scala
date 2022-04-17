package org.geunyoung

import org.geunyoung.adt.Regex
import org.geunyoung.adt.Regex.{Concatenated, Or}
import org.geunyoung.adt.Regex.CharSpecifier.{Digit, Literal, NotWhiteSpace}
import org.geunyoung.adt.Regex.Quantified.QuantifiedExact

object Example extends RegexSyntax {
  val email: Regex =
    NotWhiteSpace.oneOrMore ++ '@' ++ NotWhiteSpace.oneOrMore ++ '.' ++ NotWhiteSpace.oneOrMore

  val koreanPhoneNumber1: Regex =
    Concatenated(
      Concatenated(
        Concatenated(
          Concatenated(
            Or(
              Concatenated(Concatenated(Literal('0'), Literal('1')), Literal('0')),
              Concatenated(Concatenated(Literal('0'), Literal('1')), Literal('1'))
            ),
            Literal('-')
          ),
          QuantifiedExact(Digit, 4)
        ),
        Literal('-')
      ),
      QuantifiedExact(Digit, 4)
    )

  val koreanPhoneNumber: Regex =
    ("010".l or "011") ++ '-' ++
      Digit.quantifiedExact(4) ++ '-' ++
      Digit.quantifiedExact(4)

  private val year: Regex  = Digit.quantifiedExact(4)
  private val month: Regex =
    ('0'.l ++ '1' ~ '9') or ('1'.l ++ '0' ~ '2')
  private val date: Regex  =
    ('0'.l ++ '1' ~ '9') or ('1' ~ '2' ++ Digit) or ('3'.l ++ '0' ~ '1')

  private val hour: Regex   = ('0' ~ '1' ++ Digit) or ('2'.l ++ '0' ~ '3')
  private val minute: Regex = '0' ~ '5' ++ Digit
  private val second: Regex = '0' ~ '5' ++ Digit

  val iso8601Format: Regex =
    year ++ '-' ++
      month ++ '-' ++
      date ++ 'T' ++
      hour ++ ':' ++
      minute ++ ':' ++
      second ++ 'Z'
}
