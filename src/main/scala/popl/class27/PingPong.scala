package popl.class27

import akka.actor.{Actor, ActorRef, Props}
import akka.actor.ActorSystem

abstract class Message
case class Ping(x: Int) extends Message
case class Pong(count: Int) extends Message
case object Stop extends Message

class PingActor(pong: ActorRef) extends Actor {
  pong ! Ping(0)
  
  def receive = {
    case Pong(c) if c > 4 =>
      sender ! Stop
      println("ping stopped")
      context.stop(self)
    case Pong(c) =>
      println("ping")
      sender ! Ping(c + 1)
  }
}
 
class PongActor extends Actor {
  def receive = {
    case Ping(c) =>
      println("pong")
      sender ! Pong(c + 1)
    case Stop =>
      println("pong stopped")
      context.stop(self)
  }
}
 
object PingPong extends App {
  val system = ActorSystem("PingPong")
  val pong = system.actorOf(Props[PongActor])
  val ping = system.actorOf(Props(new PingActor(pong)))
}

