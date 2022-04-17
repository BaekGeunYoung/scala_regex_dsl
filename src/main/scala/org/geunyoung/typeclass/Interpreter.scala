package org.geunyoung.typeclass

trait Interpreter[-From, +To] {
  def interpret(from: From): To
}
