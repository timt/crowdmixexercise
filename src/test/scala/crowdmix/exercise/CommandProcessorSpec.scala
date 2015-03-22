package crowdmix.exercise

import org.scalatest.{Matchers, FunSuite}
import TestClock.UnitOfTimePimp

class CommandProcessorSpec extends FunSuite with Matchers {

  test("Posting: Alice can publish messages to a personal timeline") {
    val (commandProcessor, _) = doSomePosts

    commandProcessor("Alice") should be(List("I love the weather today (5 minutes ago)"))
  }

  test("Reading: Bob can view Alice’s timeline") {
    val (commandProcessor, _) = doSomePosts

    commandProcessor("Alice") should be(List("I love the weather today (5 minutes ago)"))
    commandProcessor("Bob") should be(List(
      "Good game though. (1 minute ago)",
      "Damn! We lost! (2 minutes ago)"
    ))
  }

  test("Following: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions") {
    val (commandProcessor, clock) = doSomePosts

    commandProcessor("Charlie follows Alice")
    commandProcessor("Charlie wall") should be(List(
      "Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)",
      "Alice - I love the weather today (5 minutes ago)"
    ))

    clock.fastForward(13.seconds)
    commandProcessor("Charlie follows Bob")
    commandProcessor("Charlie wall") should be(List(
      "Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)",
      "Bob - Good game though. (1 minute ago)",
      "Bob - Damn! We lost! (2 minutes ago)",
      "Alice - I love the weather today (5 minutes ago)"
    ))

  }

  private def doSomePosts: (CommandProcessor, TestClock) = {
    val clock = TestClock()
    val commandProcessor = new CommandProcessor(clock)
    commandProcessor("Alice -> I love the weather today")
    clock.fastForward(3.minutes)
    commandProcessor("Bob -> Damn! We lost!")
    clock.fastForward(1.minutes)
    commandProcessor("Bob -> Good game though.")
    clock.fastForward(1.minutes)
    commandProcessor("Charlie -> I'm in New York today! Anyone want to have a coffee?")
    clock.fastForward(2.seconds)
    (commandProcessor, clock)
  }

}

object TestClock {
  def apply() = new TestClock

  implicit class UnitOfTimePimp(time: Int) {
    def seconds = time * 1000

    def minutes = seconds * 60
  }

}

class TestClock extends Clock {
  private var fixedNow: Long = super.now

  override def now: Long = fixedNow

  def fastForward(seconds: Int) = fixedNow += seconds

}
