---
layout: page
title: Yi Fei's Project Portfolio Page
---

### Project: AddresSoC

AddresSoC is a CLI desktop application for School of Computing students to keep track of their network with other students and schedule meetings. It does not handle communication between students.

Given below are my contributions to the project.


  * **New Feature:** Implementation of the Logic component to allow users to add and delete meetings.
    * What it does: Allows the user to add and delete meetings from the meeting list.
    * Justification: These features are essential to the application as they are the central features allowing for management of meetings.
    * Highlights: Adding of this feature required understanding of the Logic component as well as the Model's implementation of Meeting done by my teammate Thomas.
    It was also challenging to consider and implement the most appropriate way to include Participants in a Meeting and link Participants to Contacts that exist in the Contact list.
  * **New Feature:** Added the ability to undo/redo previous commands.
    * What it does: Allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives and understanding of the Model component used by the app.
    The implementation too was challenging as it required changes to existing commands.
  * **New Feature:** Added the checking of clashes in meeting timings.
    * What it does: The app will check for clashes in meeting timings upon adding or editing of a meeting and notify the user.
    * Justification: As an app used to schedule and manage meetings, this feature improves the app significantly as the user will attempt to schedule many meetings and
    the app should notify the user if there are any clashes in meeting timings to help the user manage them properly.
    * Highlights: This enhancement required an understanding of the GUI framework to implement the pop up notification.
  * **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=yifei2&breakdown=true)
  * **Documentation**:
    * User Guide:
      * Added documentation for `deletem` feature
      * Added documentation for `undo` and `redo` features
      * Edited User Guide's overall language to be user-centric
    * Developer Guide:
      * Added use case for `deletem` feature
      * Added Non-functional requirements
      * Added implementation details for `addm` feature
      * Added implementation details for `undo` and `redo` features
  * **Contributions to team-based tasks**:
    * Hosted group meetings (i.e. start meetings, share screen, facilitate discussion, etc)
  * **Review/mentoring contributions**:
    * PRs reviewed (with non-trivial review comments): [#138](https://github.com/AY2122S2-CS2103T-W12-3/tp/pull/138)
