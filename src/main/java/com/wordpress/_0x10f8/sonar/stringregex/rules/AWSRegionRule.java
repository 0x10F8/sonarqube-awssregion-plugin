package com.wordpress._0x10f8.sonar.stringregex.rules;

import org.sonar.api.rule.RuleKey;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rule.Severity;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Rule;

@Rule(key = "AWSRegionRule")
public class AWSRegionRule implements RulesDefinition {

        public static final String REPOSITORY_JAVA = "aws-region-java";
        public static final String REPOSITORY_JS = "aws-region-js";
        public static final String REPOSITORY_PY = "aws-region-python";
        public static final String REPOSITORY_TS = "aws-region-typescript";

        public static final String JAVA_LANGUAGE = "java";
        public static final String JS_LANGUAGE = "js";
        public static final String PY_LANGUAGE = "py";
        public static final String TS_LANGUAGE = "ts";

        public static final String RULE_NAME = "hardcoded-aws-region";

        public static final RuleKey RULE_JAVA_AWS_REGION = RuleKey.of(REPOSITORY_JAVA, RULE_NAME);
        public static final RuleKey RULE_JS_AWS_REGION = RuleKey.of(REPOSITORY_JS, RULE_NAME);
        public static final RuleKey RULE_PY_AWS_REGION = RuleKey.of(REPOSITORY_PY, RULE_NAME);
        public static final RuleKey RULE_TS_AWS_REGION = RuleKey.of(REPOSITORY_TS, RULE_NAME);

        @Override
        public void define(final Context context) {
                // Java
                final NewRepository javaRepository = context.createRepository(REPOSITORY_JAVA, JAVA_LANGUAGE)
                                .setName("Java - Find Hardcoded AWS Regions");

                createRule(javaRepository, RULE_JAVA_AWS_REGION,
                                "Hardcoded Region (Java)", JAVA_LANGUAGE);
                javaRepository.done();

                // Javascript
                final NewRepository javaScriptRepository = context
                                .createRepository(REPOSITORY_JS, JS_LANGUAGE)
                                .setName("Javascript - Find Hardcoded AWS Regions");

                createRule(javaScriptRepository, RULE_JS_AWS_REGION,
                                "Hardcoded Region (Javascript)", JS_LANGUAGE);
                javaScriptRepository.done();

                // Python
                final NewRepository pythonRepository = context
                                .createRepository(REPOSITORY_PY, PY_LANGUAGE)
                                .setName("Python - Find Hardcoded AWS Regions");

                createRule(pythonRepository, RULE_JS_AWS_REGION,
                                "Hardcoded Region (Python)", PY_LANGUAGE);
                pythonRepository.done();

                // TypeScript
                final NewRepository tsRepository = context
                                .createRepository(REPOSITORY_TS, TS_LANGUAGE)
                                .setName("TypeScript - Find Hardcoded AWS Regions");

                createRule(tsRepository, RULE_TS_AWS_REGION,
                                "Hardcoded Region (Python)", TS_LANGUAGE);
                tsRepository.done();
        }

        private void createRule(final NewRepository repository,
                        final RuleKey key, final String name, final String language) {

                final NewRule rule = repository.createRule(key.rule())
                                .setName(name)
                                .setHtmlDescription(
                                                "<p>A string was found that looks like a hardcoded AWS region.</p>" +
                                                                "<p>Generates an issue for every found string.</p>")

                                // optional tags
                                .setTags(language, "hardcoded")

                                // optional status. Default value is READY.
                                .setStatus(RuleStatus.READY)

                                // default severity when the rule is activated on a Quality profile. Default
                                // value is MAJOR.
                                .setSeverity(Severity.MINOR);

                rule.setDebtRemediationFunction(
                                rule.debtRemediationFunctions().linearWithOffset("1h", "30min"));

        }
}