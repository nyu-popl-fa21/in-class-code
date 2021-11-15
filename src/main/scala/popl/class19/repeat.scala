package popl.class19

class repeat(body: => Unit) {
  def until(cond: => Boolean): Unit = {
    body
    if (!cond) until(cond)
  }
}

object repeat {
  def apply(body: => Unit) = new repeat(body)
}