# Orbit User Guide

Orbit is a command-line chatbot for tracking todos, deadlines, and events.

## Commands

* `todo DESCRIPTION` — adds a task without a date or time.
* `deadline DESCRIPTION /by DATE_OR_TIME` — adds a task with a deadline.
* `event DESCRIPTION /from START /to END` — adds an event.
* `list` — displays all tasks.
* `mark NUMBER` — marks a task as done.
* `unmark NUMBER` — marks a task as not done.
* `delete NUMBER` — removes a task.
* `bye` — exits Orbit.

For example, `deadline submit report /by Friday 5pm` adds a deadline named
`submit report` whose date and time is displayed as `Friday 5pm`.
