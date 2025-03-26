# SonarQube Regex Plugin

## Overview
The SonarQube Regex Plugin is designed to analyze Java code and identify strings that match a specific regex pattern. This plugin enhances code quality by allowing developers to enforce string formatting rules and catch potential issues early in the development process.

## Features
- Scans Java files for strings matching predefined regex patterns.
- Provides customizable regex patterns for different use cases.
- Integrates seamlessly with SonarQube to display results in the UI.

## Installation
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd sonarqube-regex-plugin
   ```
3. Build the plugin using Maven:
   ```
   mvn clean install
   ```

## Usage
1. Place the generated JAR file in the `extensions/plugins` directory of your SonarQube installation.
2. Restart SonarQube.
3. Configure the regex patterns in the SonarQube UI under the plugin settings.

## Configuration
You can customize the regex patterns by modifying the `RegexRule.java` file. Ensure that your patterns are well-tested to avoid false positives or negatives during analysis.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.