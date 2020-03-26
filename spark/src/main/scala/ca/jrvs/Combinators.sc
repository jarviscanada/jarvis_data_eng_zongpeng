import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
 * Count number of elements
 * Get the first element
 * Get the last element
 * Get the first 5 elements
 * Get the last 5 elements
 *
 * hint: use the following methods
 * head
 * last
 * size
 * take
 * tail
 */
val ls = List.range(0,10)
ls.head
ls.last
ls.size
ls.take(5)
ls.tail(5)

/**
 * Double each number from the numList and return a flatten list
 * e.g.res4: List[Int] = List(2, 3, 4)
 *
 * Compare flatMap VS ls.map().flatten
 */
val numList = List(List(1,2), List(3));
//write you solution here
def double(x:Int) : Int = x*2
numList.flatMap(x => x.map(y => double(y)))  // a single list
numList.map(x => x.map(y => double(y))) // list of list

/**
 * Sum List.range(1,11) in three ways
 * hint: sum, reduce, foldLeft
 *
 * Compare reduce and foldLeft
 * https://stackoverflow.com/questions/7764197/difference-between-foldleft-and-reduceleft-in-scala
 */
val ls2 = List.range(1,11)
ls2.sum
// reduce will return a final value of type Int or supertype of Int
ls2.reduce((x:Int, y:Int) => x+y)
// fold will return a final value which is not limited to its type
ls2.foldLeft(0)((x:Int, y:Int) => x+y)

/**
 * Practice Map and Optional
 *
 * Map question1:
 *
 * Compare get vs getOrElse (Scala Optional)
 * countryMap.get("Amy");
 * countryMap.getOrElse("Frank", "n/a");
 */
val countryMap = Map("Amy" -> "Canada", "Sam" -> "US", "Bob" -> "Canada")
// get will return an option contains the value if the key is found (some(value))
// or an option of none if it is not found
countryMap.get("Amy")
countryMap.get("edward")
// getOrElse will return the value of 2nd parameter if the key is not found in the map
countryMap.getOrElse("edward", "n/a")


/**
 * Map question2:
 *
 * create a list of (name, country) tuples using `countryMap` and `names`
 * e.g. res2: List[(String, String)] = List((Amy,Canada), (Sam,US), (Eric,n/a), (Amy,Canada))
 */
val names = List("Amy", "Sam", "Eric", "Amy")
//write you solution here
def fn1 (str:String, map:Map[String,String]) : (String, String) = Tuple2(str, map.getOrElse(str, "n/a"))
val resTuple = names.map((str:String)=>fn1(str, countryMap))

/**
 * Map question3:
 *
 * count number of people by country. Use `n/a` if the name is not in the countryMap  using `countryMap` and `names`
 * e.g. res0: scala.collection.immutable.Map[String,Int] = Map(Canada -> 2, n/a -> 1, US -> 1)
 * hint: map(get_value_from_map) ; groupBy country; map to (country,count)
 */
//write you solution here
def fn2 (map:Map[String, Int], string: String) : Map[String, Int] = {
  val country = countryMap.getOrElse(string, "n/a");
  map + (country -> (map.getOrElse(country, 0) + 1))
}
println( names.foldLeft(Map.empty[String,Int])(fn2))

println( resTuple.groupBy(_._2).mapValues(_.size) )

/**
 * number each name in the list from 1 to n
 * e.g. res3: List[(Int, String)] = List((1,Amy), (2,Bob), (3,Chris))
 */
val names2 = List("Amy", "Bob", "Chris", "Dann")
//write you solution here
println( (1 to names2.length).toList.zip(names2) )


/**
 * SQL questions1:
 *
 * read file lines into a list
 * lines: List[String] = List(id,name,city, 1,amy,toronto, 2,bob,calgary, 3,chris,toronto, 4,dann,montreal)
 */
//write you solution here
val source = Source.fromFile("/home/centos/dev/jarvis_data_eng_Zongpeng_Yang/spark/src/main/resources/employees.csv")
val lines = source.getLines().toList
println(lines)
source.close()
/**
 * SQL questions2:
 *
 * Convert lines to a list of employees
 * e.g. employees: List[Employee] = List(Employee(1,amy,toronto), Employee(2,bob,calgary), Employee(3,chris,toronto), Employee(4,dann,montreal))
 */
//write you solution here
case class Employee (val id:Int, val name:String, val home:String, val age:Int)
var bufferList = new ListBuffer[Employee]()
for (i <- 1 until (lines.size - 1) ){
  val fields = lines(i).split(",")
  bufferList += new Employee(fields(0).toInt, fields(1), fields(2), fields(3).toInt)
}
var empList = bufferList.toList
println(empList)

/**
 * SQL questions3:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 *
 * result:
 * upperCity: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(2,bob,CALGARY,19), Employee(3,chris,TORONTO,20), Employee(4,dann,MONTREAL,21), Employee(5,eric,TORONTO,22))
 */
//write you solution here
// query the whole row
val upperCaseCity = empList.map(emp => new Employee(emp.id,emp.name,emp.home.toUpperCase,emp.age))
// City only
val upperCaseCityOnly = empList.map(emp => emp.home.toUpperCase)

/**
 * SQL questions4:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 * WHERE city = 'toronto'
 *
 * result:
 * res5: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(3,chris,TORONTO,20), Employee(5,eric,TORONTO,22))
 */
//write you solution here
// query the whole row
empList.filter(emp => emp.home=="toronto").map(emp => new Employee(emp.id,emp.name,emp.home.toUpperCase,emp.age))

/**
 * SQL questions5:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city
 *
 * result:
 * cityNum: scala.collection.immutable.Map[String,Int] = Map(CALGARY -> 1, TORONTO -> 3, MONTREAL -> 1)
 */
//write you solution here
def fn3 (map:Map[String, Int], city: String) : Map[String, Int] = {
  map + (city -> (map.getOrElse(city, 0) + 1))
}
empList.map(emp => emp.home.toUpperCase).foldLeft(Map.empty[String,Int])(fn3)

println(empList.map(emp => emp.home.toUpperCase).groupBy(identity).mapValues(_.size))

/**
 * SQL questions6:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city,age
 *
 * result:
 * res6: scala.collection.immutable.Map[(String, Int),Int] = Map((MONTREAL,21) -> 1, (CALGARY,19) -> 1, (TORONTO,20) -> 2, (TORONTO,22) -> 1)
 */
//write you solution here
def fn4 (map:Map[(String,Int), Int], emp:Employee) : Map[(String,Int), Int]={
  val cityAndAge = (emp.home.toUpperCase, emp.age)
  map + (cityAndAge -> (map.getOrElse(cityAndAge, 0) + 1 ))
}
val result = empList.foldLeft(Map.empty[(String, Int), Int])(fn4)

empList.groupBy(x => (x.home.toUpperCase,x.age)).mapValues(_.size)