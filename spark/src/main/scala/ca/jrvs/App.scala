package ca.jrvs

import scala.collection.mutable.ListBuffer
import scala.io.Source


object App {

  def foo(x: Array[String]) = x.foldLeft("")((a, b) => a + " " + b)

  def main(args: Array[String]) {
    println("Hello World!")
    println("concat arguments = " + foo(args))
    val source = Source.fromFile("/home/centos/dev/jarvis_data_eng_Zongpeng_Yang/spark/src/main/resources/employees.csv")
    val lines = source.getLines().toList
    println(lines)

    case class Employee (val id:Int, val name:String, val home:String, val age:Int)
    var bufferList = new ListBuffer[Employee]()
    for (i <- 1 until (lines.size - 1) ){
      val fields = lines(i).split(",")
      bufferList += new Employee(fields(0).toInt, fields(1), fields(2), fields(3).toInt)
    }
    var empList = bufferList.toList
    println(empList)
    val upperCaseCity = empList.map(emp => new Employee(emp.id,emp.name, emp.home.toUpperCase, emp.age))
    println(upperCaseCity)


    def fn3 (map:Map[String, Int], city: String) : Map[String, Int] = {
      map + (city -> (map.getOrElse(city, 0) + 1))
    }
    println(empList.map(emp => emp.home.toUpperCase).groupBy(identity).mapValues(_.size))

    def fn4 (map:Map[(String,Int), Int], emp:Employee) : Map[(String,Int), Int]={
      val cityAndAge = (emp.home.toUpperCase, emp.age)
      map + (cityAndAge -> (map.getOrElse(cityAndAge, 0) + 1 ))
    }
    val result = empList.foldLeft(Map.empty[(String, Int), Int])(fn4)
    println(result)

    println(
      empList.groupBy(x => (x.home.toUpperCase,x.age)).mapValues(_.size))
  }
}
