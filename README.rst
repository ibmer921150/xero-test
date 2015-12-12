Setup Guide
===========

Install prerequisites:
----------------------
- JDK (Required version 7 or above)
- Firefox (Required)
- Eclipse (Option)
- Maven (Option), which can help get below dependencies:
	- selenium-server-standalone-2.46.0.jar
	- junit-4.10.jar
	- hamcrest-core-1.1.jar
	- log4j-1.2.17.jar
	- apache-log4j-extras-1.2.17.jar

Project run in the Eclipse (Recommendation)
-------------------------------------------
Maven Eclipse plugin installation ::

	1. Open Eclipse IDE
	2. Click Help -> Install New Software...
	3. Click Add button at top right corner
	4. At pop up: fill up Name as "M2Eclipse" and Location
	   as "http://download.eclipse.org/technology/m2e/releases"
	5. Now click OK
	6. After that installation would be started.

Import "xero-test" project::

	1. Click "File" > "Import" > "Maven" > "Existing Maven Project"
	2. Select location of "xero-test" project 
	3. After the project imported, right-click project and select Maven > Update Project...

Run test cases::

	1. Update the Xero's user name and password in config.properties which under /resources
	2. Right click "xero-test" project and run as "Maven test"


Project run via command line
----------------------------
The files under xero-test/bin have already include all the dependencies. So after update the
Xero's user name and password in config.properties, then you can run the test by below steps.

On Linux:

.. code:: bash

	cd xero-test/bin
	./run_tests.sh

On Windows:

.. code:: bash

	cd xero-test/bin
	run_tests.bat

Test Results
------------
- Junit log - /xero-test/target/surefire-reports (run as Maven test)
- Test report - /xero-test/XeroTestReport_YYYY-MM-DD.html