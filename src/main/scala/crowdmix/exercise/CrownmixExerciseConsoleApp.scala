package crowdmix.exercise

import scala.io.StdIn

object CrownmixExerciseConsoleApp extends App{
  val commandProcessor = CommandProcessor()
  while(true) {
    commandProcessor(StdIn.readLine("> ")).foreach(println)
  }
}


