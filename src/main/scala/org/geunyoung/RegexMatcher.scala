package org.geunyoung

trait RegexMatcher {
  def matches: Boolean
  def findAll: Seq[String]
}
