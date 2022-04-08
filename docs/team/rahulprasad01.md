---
layout: page
title: Rahul's Project Portfolio Page
 ---

### Project: AddresSoC

AddresSoC is a CLI desktop application for School of Computing students to keep track of their network with other students and schedule meetings. It does not handle communication between students.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=rahulprasad01&breakdown=true)
* **Enhancements implemented**:
    * Modify person to form contact. (PR #70)
    * Modify logic, model, storage, ui and update tests to support contact
    * What it does: Allows users to add all details for the contact of a person
    * Justification: These features are necessary for users to store important contact information.

* **New Features implemented**:
    * Sort contacts (PRs #70, #80, #81)
    * What it does: Allows users to sort contacts by name
    * Justification: This will enable users to order the contacts and will make finding contacts easier.

    * Reminder (PR #106)
    * Added tests (PR #134)
    * What it does: Reminds users of meeting upcoming in input number of days
    * Justification: This will allow user with busy schedules to be reminded of upcoming meetings

    * Sort meetings (PR #117)
    * What it does: Allows users to sort contacts by date and meetings on same day are sorted by start time.
    * Justification: This will enable users to order the meetings and will make locating meetings easier.

    * Archive meetings (PR #124)
    * What it does: Allows users to archive meeting
    * Justification: Using this users can archive meeting they do not want to appear in the meeting list, but want to save the data
  
    * Unarchive meeting (PR #127)
    * What it does: Allow users to unarchive meeting
    * Justification: Users can unarchive meeting they want back in the meeting list. This is used to revert the archive feature.

    * List archive (PR #124)
    * What it does: Allows users to list archived meeting
    * Justification: Users can view archived meeting and use the unarchive feature if necessary.
      

* **Documentation**:
    * User Guide: (PRs 132, 31)
        * Added documentation for Add contact
        * Added documentation for Delete contact
        * Added documentation for sort contact
        * Added documentation for sort meeting
        * Added documentation for reminder
        * Added documentation for archive
        * Added documentation for unarchive
        * Added documentation for archivelist
      
    * Developer Guide: (PRs #33, 34, 133, 190)
        * Added use case for add contact
        * Added use case for delete contact
        * Modify model class diagram
        * Added implementation details for reminder with a sequence diagram
        * Added implementation details for archive with a sequence and an activity diagram
        * Added implementation details for unarchive with a activity diagram.
      

* **Contributions to team-based tasks**:
    * Added and assigned issues, contributed to tutorial team tasks(practise demo), engaged in discussion
      during team meetings
* 
* **Review/mentoring contributions**:
    * PRs reviewed (with non-trivial review comments): (PR #191)
