package popl.class20

// The state monad: a container for stateful computations S => (S,R)
abstract class State[S,R] {
  def apply(s: S): (S, R)

  def map[P](f: R => P): State[S, P] = {
    new State { def apply(s: S) = {
      val (sp, r) = this.apply(s)
    } }
  }
}
