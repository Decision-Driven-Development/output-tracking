# Java Output Tracking for Testing without Mocks

Java implementation of James Shore's output tracking method for Nullables and
[Testing without mocks](https://www.jamesshore.com/v2/projects/nullables/testing-without-mocks)
approach.

## Description

`output-tracking` is a lightweight library designed to facilitate testing without relying on mocking
frameworks. It provides tools to track and verify outputs produced by testable components, enabling
clean and maintainable tests.

## Installation

To include `output-tracking` in your project, add the following dependency to your `pom.xml` if
you're using Maven:

```xml

<dependency>
    <groupId>com.example</groupId>
    <artifactId>output-tracking</artifactId>
    <version>1.0.0</version>
</dependency>
```

For Gradle, add this to your `build.gradle`:

```gradle
implementation 'com.example:output-tracking:1.0.0'
```

## Usage

### Tracking Outputs

The library allows you to track outputs produced by your components. You can read the tests to get
an idea of how to use the library, but here's a simple example:

```java
import ewc.utilities.testableio.tracking.OutputListener;
import ewc.utilities.testableio.tracking.OutputTracker;

public class Example {
    public static void main(String[] args) {
        OutputListener<String> listener = new OutputListener<>();
        OutputTracker<String> tracker = listener.trackOutput();

        // Simulate producing outputs
        listener.track("Hello");
        listener.track("World");

        // Retrieve tracked outputs
        System.out.println(tracker.data()); // Output: [Hello, World]

        // Clear tracked outputs
        tracker.clear();
        System.out.println(tracker.data()); // Output: []
    }
}
```

### Stopping Tracking

You can stop tracking outputs and remove the tracker from the listener:

```java
OutputListener<String> listener = new OutputListener<>();
OutputTracker<String> tracker = listener.trackOutput();

public class Example {
    public static void main(String[] args) {
        listener.track("Test Output");
        System.out.println(tracker.data()); // Output: [Test Output]

        // Stop tracking
        tracker.stopTracking();
        System.out.println(listener.trackers().isEmpty()); // Output: true

        listener.track("Another Output"); // This won't be tracked anywhere
    }
}
```

## Features

- Enable testable components to produce some output describing their state and behavior. Think
  of it as a logging mechanism that stores any kind of objects, so you can then test the sequence of
  logged events and the data they contain.
- Retrieve and clear tracked outputs.
- Manage multiple trackers with a single listener.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
