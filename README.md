## CircularQueue
### Description
CircularQueue is a Kotlin-based library providing an efficient and robust implementation of a circular queue (also known as a circular buffer or ring buffer). This data structure allows fixed-size storage with automatic wrap-around behavior when the end of the buffer is reached, making it ideal for scenarios that require a constant memory footprint, such as buffering data streams or implementing caches.

### Features
 - Fixed Capacity: Define a maximum size for the queue to ensure controlled memory usage.
 - Automatic Wrap-Around: When the queue reaches its capacity, new elements overwrite the oldest ones, maintaining the fixed size.
 - MutableCollection Interface: Implements the MutableCollection interface, providing familiar methods such as add, remove, contains, addAll, removeAll, retainAll, and clear.
 - Iterator Support: Iterate over the elements in the order they were added.
 - Equality and Hashing: Supports equality checks and hash code generation, making it suitable for use in collections requiring these capabilities.

### Getting Started
#### Prerequisites
 - Kotlin 1.5 or higher
 - Gradle or Maven for dependency management

#### Installation
To include CircularQueue in your project, add the following dependency to your build.gradle.kts file:

```kotlin
dependencies {
    implementation("com.example:circularqueue:1.0.0")
}
```

For Maven, add the following to your pom.xml:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>circularqueue</artifactId>
    <version>1.0.0</version>
</dependency>
```


#### Usage
Here's a simple example of how to use the CircularQueue class:

```kotlin
import com.example.circularqueue.CircularQueue

fun main() {
    val queue = CircularQueue<Int>(3)
    queue.add(1)
    queue.add(2)
    queue.add(3)
    println(queue) // Output: [1, 2, 3]

    queue.add(4)
    println(queue) // Output: [2, 3, 4]

    queue.remove(3)
    println(queue) // Output: [2, 4]
}
```

## API Documentation
 - add(element: T): Boolean: Adds an element to the queue. If the queue is full, the oldest element is overwritten.
 - remove(element: T): Boolean: Removes the specified element from the queue, shifting subsequent elements as necessary.
 - contains(element: T): Boolean: Checks if the queue contains the specified element.
 - addAll(elements: Collection<T>): Boolean: Adds all elements from the specified collection to the queue.
 - removeAll(elements: Collection<T>): Boolean: Removes all elements found in the specified collection from the queue.
 - retainAll(elements: Collection<T>): Boolean: Retains only the elements found in the specified collection.
 - clear(): Clears all elements from the queue.
 - iterator(): MutableIterator<T>: Returns an iterator over the elements in the queue.
 - size: Returns the current number of elements in the queue.
 - isEmpty(): Checks if the queue is empty.

## Contributing
We welcome contributions to improve the CircularQueue library. To contribute:

 - Fork the repository.
 - Create a new branch (git checkout -b feature-branch).
 - Make your changes and commit them (git commit -am 'Add new feature').
 - Push the branch (git push origin feature-branch).
 - Open a Pull Request.
 - Please ensure your code follows our coding guidelines and includes relevant tests.

## License
This project is licensed under the MIT License.
