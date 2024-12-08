# MD4Encryption

**MD4Encryption** is a Java-based implementation of the **MD4 (Message Digest 4)** hashing algorithm. It is designed for lightweight, dependency-free use in applications that require generating MD4 hashes. The implementation adheres to the original MD4 specification by Ronald Rivest.

---

## Features

- **Pure Java Implementation**: No external libraries required.
- **Message Hashing**: Produces a fixed-size 128-bit hash from input data.
- **Compact and Fast**: Optimized for simplicity and performance.
- **Educational Use**: Useful for understanding hash algorithms.

---

## Use Cases

- **Digital Signatures**: Hashing data for signing processes.
- **Integrity Verification**: Ensuring data has not been tampered with.
- **Legacy Applications**: Supporting systems that require MD4.

---

## Installation

Copy the `MD4Encryption` class into your Java project. No additional setup or dependencies are required.

---

## Usage

### Basic Example
```java
public class Main {
    public static void main(String[] args) {
        String input = "Hello, World!";
        
        String hash = MD4Encryption.md4(input);
        System.out.println("MD4 Hash: " + hash);
    }
}
