package de.schauderhaft.dependencies

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.rogach.scallop.exceptions.UnknownOption

@RunWith(classOf[JUnitRunner])
class CommandLineParserTest extends FunSuite {
    import org.scalatest.matchers.ShouldMatchers._

    test("default outputfile is 'output.graphml'") {
        val config = CommandLineParser.parse(Array[String]())
        config.output() should be("output.graphml")
    }

    test("the string after -o is considered the output file name") {
        val config = CommandLineParser.parse(Array[String]("-o", "ExampleFile"))
        config.output() should be("ExampleFile")
    }

    test("default input is empty") {
        val config = CommandLineParser.parse(Array[String]())
        config.classpath() should be(".")
    }

    test("the string after -c is considered the input classpath") {
        val config = CommandLineParser.parse(Array[String]("-c", "input;blah.jar"))
        config.classpath() should be("input;blah.jar")
    }

    test("default exclude filter is empty") {
        val config = CommandLineParser.parse(Array[String]())
        config.excludeFilter() should be(List())
    }
    test("the strings after -e s are considered the exclude filter") {
        val config = CommandLineParser.parse(Array[String]("-e", "filter"))
        config.excludeFilter() should be(List("filter"))
    }

    test("default include filter is empty") {
        val config = CommandLineParser.parse(Array[String]())
        config.includeFilter() should be(List())
    }
    test("the strings after -i s are considered the exclude filter") {
        val config = CommandLineParser.parse(Array[String]("-i", "filter"))
        config.includeFilter() should be(List("filter"))
    }

    test("default groupings are empty") {
        val config = CommandLineParser.parse(Array[String]())
        config.groupings() should be('empty)
    }

    test("the strings after -g s are considered the groupings") {
        val config = CommandLineParser.parse(Array[String]("-g", "alpha", "beta", "gamma"))
        config.groupings() should be(List("alpha", "beta", "gamma"))
    }

}