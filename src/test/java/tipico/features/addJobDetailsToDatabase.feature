Feature: I want to use this test for getting the available job openings from Tipico careers page and adding Job Title, Department and Location into the database

  Scenario Outline: Create Database if does not exist
    Given I want to create a new database with username <username>, password <password> and dbUrl <database url> and to create a database <database name> and a table <table name> if does not exist

    Examples: 
      | username | password      | database url            | database name | table name |
      | root     | Dmietr@442001 | jdbc:mysql://localhost/ | tipico        | openJobs   |

  Scenario Outline: Add all available Job details to database and Verify database updated with correct number of jobs
    Given I have landed on Tipico career page <landing Page>
    When I have added all avaliable job details into database <database name> table <table name> with username <username>, password <password> and dbUrl <database url>
    Then I have verify the available jobs should be equal to jobs added into database

    Examples: 
      | landing Page                            | database name | table name | username | password      | database url            |
      | https://www.tipico-careers.com/en/jobs/ | tipico        | openJobs   | root     | Dmietr@442001 | jdbc:mysql://localhost/ |
