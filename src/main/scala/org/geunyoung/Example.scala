package org.geunyoung

import org.geunyoung.Regex.CharSpecifier.{Digit, NotWhiteSpace}

object Example extends RegexSyntax {
  val email: Regex =
    NotWhiteSpace.oneOrMore ++ '@' ++ NotWhiteSpace.oneOrMore ++ '.' ++ NotWhiteSpace.oneOrMore

  val koreanPhoneNumber: Regex =
    ("010".l or "011") ++ '-' ++
      Digit.quantifiedExact(4) ++ '-' ++
      Digit.quantifiedExact(4)

  val year: Regex  = Digit.quantifiedExact(4)
  val month: Regex =
    ('0'.l ++ '1' ~ '9') or ('1'.l ++ '0' ~ '2')
  val date: Regex  =
    ('0'.l ++ '1' ~ '9') or ('1' ~ '2' ++ Digit) or ('3'.l ++ '0' ~ '1')

  val hour: Regex   = ('0' ~ '1' ++ Digit) or ('2'.l ++ '0' ~ '3')
  val minute: Regex = '0' ~ '5' ++ Digit
  val second: Regex = '0' ~ '5' ++ Digit

  val iso8601Format: Regex =
    year ++ '-' ++
      month ++ '-' ++
      date ++ 'T' ++
      hour ++ ':' ++
      minute ++ ':' ++
      second ++ 'Z'
}
