An Android application that implements the Aircraft queue shown below.
This mobile app should provide and interface to allow the user to:
- Initialize the queue
- Add a new aircraft to the queue.
- Retrieve the next aircraft from the queue.

Aircraft Queue
A software subsystem of an air-traffic control system is defined to manage a queue of aircraft (AC) in an airport. The aircraft queue is managed by a process which responds to three types of requests:
- system boot used to start the system.
- enqueue aircraft used to insert a new AC into the system.
- dequeue aircraft used to remove an AC from the system.

AC�s have the following properties:
- AC type: Passenger or Cargo
- AC size: Small or Large

The process which manages the queue of AC�s satisfies the following:
- There is no limit on the number of AC�s it can manage (could be tens of thousands)
- Dequeue aircraft requests result in selection of one AC for removal such that:
- Passenger AC�s have removal precedence over Cargo AC�s
- Large AC�s of a given type have removal precedence over Small AC�s of the same type.
- Earlier enqueued AC�s of a given type and size have precedence over later enqueued AC�s of the same type and size.



NOTE: Removed gradle.bat file from the zip file. Please include batch file inorder to build it in Android studio.