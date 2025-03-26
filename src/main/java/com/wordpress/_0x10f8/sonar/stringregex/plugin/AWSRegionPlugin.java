package com.wordpress._0x10f8.sonar.stringregex.plugin;

import org.sonar.api.Plugin;

import com.wordpress._0x10f8.sonar.stringregex.rules.AWSRegionRule;
import com.wordpress._0x10f8.sonar.stringregex.sensors.AWSRegionSensor;

public class AWSRegionPlugin implements Plugin {

  @Override
  public void define(final Context context) {
    context.addExtensions(AWSRegionRule.class, AWSRegionSensor.class);
  }
}