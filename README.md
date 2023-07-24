# ipApi

A simple Scala command-line tool that retrieves your public IP address using the https://api.ipify.org service.

## Prerequisites

- Java 8 or higher
- Scala 3.x
- SBT (Scala Build Tool)

## Getting Started

1. Clone this repository to your local machine:

```bash
git clone https://github.com/SetMyDream/ipApi
```
Navigate to the project root folder:
```bash
cd ipApi
```
Compile the project:
```bash
sbt compile
```
Run the command-line tool:
```bash
sbt run
```

## Description
The ipApi tool uses the https://api.ipify.org/?format=json API to fetch your public IP address. It demonstrates the usage of ZIO, a functional effect system for Scala, to handle asynchronous and error-prone operations in a purely functional way.

## Functionality
The tool performs the following steps:

1. Sends a request to the https://api.ipify.org/?format=json API to get the IP address.
2. Parses the JSON response to extract the IP address using the Jackson library.
3. Displays the retrieved IP address on the console.

## Usage
Simply run the command-line tool using sbt run. The tool will make an HTTP request to the https://api.ipify.org API to fetch your IP address and display it on the console.