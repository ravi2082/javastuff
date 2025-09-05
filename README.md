# Java Utilities Collection

A collection of Java utility classes developed for automating debugging tasks, data processing, and testing in various projects.

## Overview

This repository contains various Java utility classes that demonstrate common programming patterns and provide solutions for:
- File processing and comparison
- Data deduplication and analysis 
- Database connectivity
- XML parsing and processing
- Thread pool management
- Security and encryption utilities
- String manipulation and testing

**Note**: Not all proprietary code using organization-specific libraries has been included in this repository.

## Getting Started

### Prerequisites
- Java 8 or higher
- Some utilities may require additional dependencies (noted in individual class documentation)

### Compilation
Most classes can be compiled independently:
```bash
cd src/
javac ClassName.java
```

### Running Examples
```bash
java ClassName [arguments]
```

## Utility Classes

### File Operations
- **FileCompare.java** - Compares two files and identifies differences
- **DelDup.java** - Removes duplicates from text files
- **FileBreak.java** - File splitting utilities
- **FileParse.java** - Text file parsing utilities

### Data Processing
- **CalculateActualEffort.java** - Calculates work effort from time tracking data
- **Divide100.java** - Finds number combinations that sum to specific values
- **CalculateCycleTimes.java** - Performance timing utilities

### Database & Connectivity
- **DBConnection.java** - Database connection utilities (uses deprecated JDBC-ODBC bridge)
- **MyServlet.java** - Simple servlet example

### Security & Encryption
- **MyPrivateKey.java** - Private key handling utilities
- **TestDecrypt.java** - Decryption testing utilities

### Threading & Concurrency
- **ThreadPoolImplementor.java** - Custom thread pool implementation with monitoring

### Testing & Examples
- **TestDate.java** - Dynamic class loading example
- **TestInterface.java** - Interface for testing implementations
- **TestAbstract.java** - Abstract class for inheritance examples

## Code Quality Improvements

Recent improvements include:
- ✅ Replaced raw generic types with parameterized types
- ✅ Added try-with-resources for proper resource management
- ✅ Improved exception handling and logging
- ✅ Added comprehensive JavaDoc documentation
- ✅ Replaced hardcoded values with constants where appropriate
- ✅ Enhanced security by removing hardcoded credentials
- ✅ Updated deprecated API usage recommendations

## Security Notes

⚠️ **Important**: Some classes contain examples with hardcoded file paths and connection strings. These are for demonstration purposes only and should not be used in production environments. Always:
- Externalize configuration to properties files
- Use environment variables for sensitive data
- Implement proper input validation
- Use modern security practices

## Legacy Dependencies

Some classes depend on legacy or proprietary libraries:
- JDBC-ODBC bridge (deprecated since Java 8)
- Matrix One framework (proprietary)
- iText library for PDF processing
- Custom XML binding libraries

Consider updating these dependencies for production use.

## Contributing

This is a personal utility collection, but suggestions for improvements are welcome. Please ensure any contributions:
- Follow existing code style
- Include proper documentation
- Handle errors gracefully
- Use modern Java practices

## Practice Setup

This repository is also used for setting up GitHub Copilot review workflows and testing automated code analysis tools.
