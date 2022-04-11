---
layout: page
title: William's Project Portfolio Page
---

### Project: AddresSoC

AddresSoC is a CLI desktop application for School of Computing students to keep track of their network with other students and schedule meetings. It does not handle communication between students.

Given below are my contributions to the project.

* **New Feature:** Implementation of the Storage component to save meetings and contacts.
  * What it does: Allows the contacts and meetings in the user's lists to be automatically saved after any changes made to the data.
  * Justification: This feature is essential to the application as it is part of the central feature allowing for management of contacts and meetings.
  * Highlights: Adding of this feature required understanding of Jackson and how the existing saving infrastructure of AB3 works.
    Moreover, it required awareness of the Model's implementation of Contact (by Rahul) and Meeting (by Thomas) as well as the interactions 
    of the Storage component with other components of the application. 
* **New Feature:** Finding a meeting by name, date and/or tag.
  * What it does: Allows the user to find meetings by name, date and/or tag.
  * Justification: This feature improves the product significantly because it helps the user in better managing their meeting schedule.
    For example, they can view how busy their schedule is on a particular day by searching by date, or recall when a particular meeting was scheduled
    by searching by name.
  * Highlights: This feature required understanding of Java `Predicates` and `FilteredList` / `ObservableList`. The key consideration when implementing this feature was the tradeoff between the testability of the feature vs the flexibility provided to the user, as elaborated in the DG. 
* **New Feature:** Automatically updating/deleting participants when the contact is updated/deleted.
  * What it does: Allows the meeting's participant list to automatically sync with any updates to the contact participating in the meeting.
  * Justification: This feature makes it significantly more convenient for the user when managing meeting participants, eg. if the delete a contact, 
    they do not have to manually delete this contact from the meetings that it participated in.
  * Highlights: It was challenging to consider how best to link Meetings and Participants to Contacts that exist in the Contact list in order to balance performance and testability, as explained in the DG.
* **Enhancement to existing feature**: Improved the GUI.
  * What it does: Allows the user to see both the meeting and contact lists on one screen. Also allows the user to read results from executing commands with minimal scrolling. 
  * Justification: Showing the meeting and contact lists on one screen rather than separate tabs allows users to more 
    easily manage their meetings and contact the participants of their meetings. Moreover, adjusting the window size and size of the 
    various components of the GUI so that command results can be viewed with minimal scrolling makes the application more optimised for a command-line interface.
  * Highlights: This enhancement required an understanding of the GUI framework. It also required the understanding of CSS, especially the precedence of various CSS rules and 
    the interaction between the styles of different components (eg. changing the padding of one object might affect the look of another, etc.)
    Since my previous experience with CSS was largely limited to copy-pasting without much understanding, I had to pick up the language on the fly while implementing this enhancement.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=cpwill01&breakdown=true)
* **Documentation**:
  * User Guide:
    * Updated the introduction, quick start & command format notes
    * Updated documentation for "Saving the data" and "Editing the data files" features
    * Added documentation for `findm` feature
    * Improved the overall structure of the User Guide (separation into sections & subsections)
  * Developer Guide:
    * Added target user profile, value proposition [\#35](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/35)
    * Periodically updated user stories [\#35](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/35), [\#85](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/85), [\#204](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/204)
    * Added details for how meeting participants are managed [\#111](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/111)
    * Added details for find meeting feature [\#204](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/204) 
    * Updated class diagrams for Storage and UI components [\#111](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/111), [\#204](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/204)
* **Contributions to team-based tasks**:
  * Set up the GitHub team org and part of the team repo (CodeCov integration)
  * Reviewed most PRs
  * Overall maintenance of issue tracker (creating and assigning issues, ensuring issues are linked to the relevant PRs, etc.)
  * Milestone management (creation, setting deadlines, release of JAR file, closing)
* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [\#70](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/70), [\#106](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/106), [\#139](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/139), etc.
