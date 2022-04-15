package org.geunyoung

import org.geunyoung.Regex.{Concatenated, Literal, NotWhiteSpace, OneOrMore}

object Example extends RegexSyntax {
  val emailRegex: Regex =
    Concatenated(
      Concatenated(OneOrMore(NotWhiteSpace), Literal("@")),
      Concatenated(
        Concatenated(OneOrMore(NotWhiteSpace), Literal(".")),
        OneOrMore(NotWhiteSpace)
      )
    )

  val emailRegex2: Regex =
    NotWhiteSpace.oneOrMore
      .concatWith("@")
      .concatWith(
        NotWhiteSpace.oneOrMore
          .concatWith(".")
          .concatWith(NotWhiteSpace.oneOrMore)
      )

  val emailRegex3: Regex =
    NotWhiteSpace.oneOrMore ++ "@" ++ NotWhiteSpace.oneOrMore ++ "." ++ NotWhiteSpace.oneOrMore
}
