package crowdmix.exercise

import scala.io.StdIn

object CrownmixExerciseConsoleApp extends App {
  val commandProcessor = CommandProcessor()
  println("USAGE:\n" +
    "posting: <user name> -> <message>\n" +
    "reading: <user name>\n" +
    "following: <user name> follows <another user>\n" +
    "wall: <user name> wall")
  while (true) {
    commandProcessor(StdIn.readLine("> ")).foreach(println)
  }
}


