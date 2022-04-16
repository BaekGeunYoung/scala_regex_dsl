package org.geunyoung

class RegexMatcherImpl(
    val regex: Regex,
    val src: String
) extends RegexMatcher {
  private val r = RegexInterpreter.interpret(regex)

  override def matches: Boolean = r.matches(src)

  override def findAll: Seq[String] = r.findAllIn(src).map(identity).toSeq
}
