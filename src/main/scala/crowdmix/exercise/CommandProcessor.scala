package crowdmix.exercise

import crowdmix.exercise.Formatter.formatElapsedTime

import scala.collection.mutable

case class CommandProcessor(clock: Clock = new Clock()) {
  val POSTING = "(.*) -> (.*)".r
  val FOLLOWING = "(.*) follows (.*)".r
  val WALL = "(.*) wall".r
  var messages: List[Message] = List()
  val subscriptions = new mutable.HashMap[String, mutable.Set[String]] with mutable.MultiMap[String, String]

  def apply(command: String): List[String] = command match {
    case POSTING(user, message) => posting(Message(user.trim, message.trim, clock.now))
    case FOLLOWING(follower, followee) => following(follower.trim, followee.trim)
    case WALL(user) => wall(user)
    case user => reading(user)
  }

  private def posting(message: Message): List[String] = {
    messages = message :: messages
    Nil
  }

  private def reading(user: String): List[String] = {
    messagesFor(Set(user)).map(message => formatMessage(message))
  }

  private def following(follower: String, followee: String): List[String] = {
    subscriptions.addBinding(follower, followee)
    Nil
  }

  private def wall(user: String):List[String] = {
    val users = (subscriptions.getOrElse(user, mutable.Set()) + user).toSet
    messagesFor(users).map(message => formatMessageForWall(message))
  }

  private def messagesFor(users: Set[String]): List[Message] = {
    messages
      .filter(message => users.contains(message.user))
  }

  private def formatMessage(message: Message): String = {
    s"${message.text} (${formatElapsedTime(clock.now - message.timeInMillis)} ago)"
  }

  private def formatMessageForWall(message: Message): String = {
    s"${message.user} - ${formatMessage(message)}"
  }


}