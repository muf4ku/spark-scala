package demo

object TestFunction {
  def main(args: Array[String]): Unit = {
  val aa=addInt("mufaku",23)
    println(aa.get("nama").get)
    println(aa.get("umur").get)
  }

  def addInt( a:String, b:Int ) : Map[String,String] = {
    var map:Map[String,String] = Map()
    map+=("nama"->a)
    map+=("umur"->b.toString)
    return map
  }
}
