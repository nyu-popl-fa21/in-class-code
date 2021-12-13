package popl.class27

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.concurrent._
import ExecutionContext.Implicits.global

class MergeSort(l: List[Int], p: Promise[List[Int]]) extends Actor {
  val root: ActorRef = context.actorOf(Props(new Sorter))

  l.foreach { root ! Some(_) }
  root ! None

  def collectResult(result: List[Int]): Receive = {
    case Some(x:Int) => 
      context.become(collectResult(x :: result))
    case None => 
      p.success(result.reverse)
  }

  def receive: Receive = collectResult(Nil)
}

object Sorting extends App {
   
  val input = args.foldRight(Nil: List[Int]) { _.toInt :: _ } //List(1, 5, 3, 2)
  println(s"Input: $input")

  val system = ActorSystem("MergeSort")
  val p = Promise[List[Int]]
  val m = system.actorOf(Props(new MergeSort(input, p)))

  p.future foreach { 
    output => 
      system.terminate()
      println(s"Output: $output") 
  }
}
