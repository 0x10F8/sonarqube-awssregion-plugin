package com.wordpress._0x10f8.sonar.stringregex.sensors;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.InputFile.Type;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;

import com.wordpress._0x10f8.sonar.stringregex.rules.AWSRegionRule;

public class AWSRegionSensor implements Sensor {

    private static final String REGION_PATTERN = "[\"\'`].*(us|eu|ap|sa|ca|af|me|us-gov)-(north|south|east|west|central|northeast|southeast|southwest|northwest)-\\d.*[\"\'`]";
    private static final List<String> ALLOWED_LANGUAGES = List.of("java", "js", "py", "ts");

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("AWS Region Sensor");
    }

    @Override
    public void execute(final SensorContext context) {
        // Only scan main files not test files
        context.fileSystem().inputFiles(context.fileSystem().predicates().hasType(Type.MAIN))
                .forEach(file -> analyzeFile(file, context));
    }

    private void analyzeFile(final InputFile file, final SensorContext context) {
        try {

            // Don't scan files that are not in the allowed languages
            if (file == null || file.language() == null || !ALLOWED_LANGUAGES.contains(file.language())) {
                return;
            }

            try (InputStream is = file.inputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                List<String> lines = bufferedReader.lines().collect(Collectors.toList());
                Pattern pattern = Pattern.compile(REGION_PATTERN);

                for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
                    String line = lines.get(lineNumber);
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        reportIssue(context, file, lineNumber + 1, matcher.group());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reportIssue(SensorContext context, InputFile file, int lineNumber, String matchedString) {
        NewIssue newIssue = context.newIssue();
        NewIssueLocation location = newIssue.newLocation()
                .on(file)
                .at(file.selectLine(lineNumber))
                .message("Found hardcoded AWS region string: " + matchedString);

        if (file.language() == "java") {
            newIssue.at(location).forRule(AWSRegionRule.RULE_JAVA_AWS_REGION).save();
        }
        if (file.language() == "js") {
            newIssue.at(location).forRule(AWSRegionRule.RULE_JS_AWS_REGION).save();
        }
        if (file.language() == "ts") {
            newIssue.at(location).forRule(AWSRegionRule.RULE_TS_AWS_REGION).save();
        }
        if (file.language() == "py") {
            newIssue.at(location).forRule(AWSRegionRule.RULE_PY_AWS_REGION).save();
        }

    }
}