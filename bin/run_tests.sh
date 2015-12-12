#! /bin/sh
java -cp .:junit-4.10.jar:hamcrest-core-1.1.jar:selenium-server-standalone-2.46.0.jar:apache-log4j-extras-1.2.17.jar:log4j-1.2.17.jar org.junit.runner.JUnitCore com.xero.web.testcases.RepeatingTabTest

