package io.bet.betzilla.common

import java.util.Properties
import java.io.FileInputStream
import java.io.File

abstract class ConfigBase(projectName: String) {
  private val prop = new Properties
  private val fullPath = new File(".").getCanonicalPath +
    s"/$projectName/src/main/scala/io/bet/betzilla/$projectName/app.config"

  prop load new FileInputStream(fullPath)
  protected def get(name: String) = prop getProperty name
}
