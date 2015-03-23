CrowdMix Coding Exercise
=================

This is and implementation of the CrowdMix exercise, see [CodingExercise.pdf](CodingExercise.pdf)

How to run the application
--------------------------

Linux/Macos

    ./sbt run

Windows

    sbt.bat run

(wait for the world of scala to download)

Submit commands at the > prompt

* posting: [user name] -> [message]
* reading: [user name]
* following: [user name] follows [another user]
* wall: [user name] wall

Example
-------

    > Alice -> I love the weather today
    > Alice
    I love the weather today (5 minutes ago)

    > Charlie -> I'm in New York today! Anyone want to have a coffee?
    > Charlie follows Alice
    > Charlie wall
    Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
    Alice - I love the weather today (5 minutes ago)
