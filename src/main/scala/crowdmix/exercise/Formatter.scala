package crowdmix.exercise

object Formatter {
  def formatElapsedTime(elapsedTimeInMillis: Long): String = {
    val (amount, unit) = elapsedTimeInMillis / 1000 match {
      case seconds if seconds < 60 => (seconds, "second")
      case seconds => (seconds / 60, "minute")
    }
    s"$amount $unit${if (amount > 1) "s" else ""}"
  }
}
