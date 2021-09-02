package class02

import scala.annotation.tailrec

object worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

	val x = 5                                 //> x  : Int = 5
	x + 2                                     //> res0: Int = 7
	
	def max(x: Int, y: Int) =
		if (x < y) y else x               //> max: (x: Int, y: Int)Int
	max(3, 6)                                 //> res1: Int = 6

	val pi = 3.14159                          //> pi  : Double = 3.14159
	def circle(r: Double): Double = {
		2 * pi * r
	}                                         //> circle: (r: Double)Double
	circle(1)                                 //> res2: Double = 6.28318
	
	def area(r: Double): Double = {
		def square(x: Double) = x * x
		square(r) * pi
  }                                               //> area: (r: Double)Double
  
  def sum(a: Int, b: Int): Int = {
		def loop(i: Int, acc: Int): Int = {
			if (i < b) loop(i + 1, i + acc) else acc
		}
		loop(a, 0)
  }                                               //> sum: (a: Int, b: Int)Int
  
  def sumImp(a: Int, b: Int): Int = {
  	var i = a
  	var acc = 0
  	
  	while (i < b) {
  		acc = i + acc
  		i = i + 1
  	}
  	
  	acc
  }                                               //> sumImp: (a: Int, b: Int)Int
  
	sum(1, 100000)                            //> res3: Int = 704982704
}