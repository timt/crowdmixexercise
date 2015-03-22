package crowdmix.exercise

import crowdmix.exercise.Formatter.formatElapsedTime
import org.scalatest.{Matchers, FunSuite}
import TestClock.UnitOfTimePimp

class FormatterSpec extends FunSuite with Matchers{

  test("formatElapsedTime as minutes if more than 59 seconds") {
    formatElapsedTime(5.minutes) should be("5 minutes")
    formatElapsedTime(60.seconds) should be("1 minute")
  }

  test("formatElapsedTime as seconds if less than one minute") {
    formatElapsedTime(59.seconds) should be("59 seconds")
  }

  test("formatElapsedTime, uses singular for value of 1") {
    formatElapsedTime(1.seconds) should be("1 second")
    formatElapsedTime(1.minutes) should be("1 minute")
  }

}
