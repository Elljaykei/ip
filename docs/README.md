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

For example, `deadline submit report /by 2026-09-15` adds a deadline named
`submit report` whose date is displayed as `Sep 15 2026`.

## Duplicate tasks

Orbit rejects an addition if a task with the same type and description already
exists. Descriptions ignore case and surrounding whitespace; internal whitespace
is significant. Deadlines must also have the same date. Events must have matching
start and end text, ignoring case and surrounding whitespace. Event times remain
text: `2pm` and `14:00` are not treated as equivalent.

For example, after `todo Read book`, `todo read book` responds:

```text
OOPS!!! This task already exists. Use list to find it, or unmark it to reopen it.
```

Completed tasks also count as duplicates. Use `unmark NUMBER` to reopen one, or
delete it before adding it again. Tasks of different types, or with different
dates or event times, are allowed. Rejected additions do not change the task list
or saved file. This applies to both the GUI and CLI, including tasks loaded after
restarting. Existing saved duplicates are preserved, and the save format is unchanged.
