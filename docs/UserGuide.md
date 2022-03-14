---
layout: page
title: User Guide
---

AddresSoC is a desktop app for School of Computing (SoC) students to **keep track of their student network and schedule meetings**.
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you're a student who can type fast, AddresSoC can get your contact and schedule management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressoc.jar` from [here](https://github.com/AY2122S2-CS2103T-W12-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddresSoC.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`addc`**` n/John Doe e/johnd@u.nus.edu p/98076034 th/@johnd` : Adds a contact named `John Doe` to the AddresSoC contact list

   * **`addm`**` n/SE Team Meeting d/23/03/2022 st/1415 et/1615` : Schedules a meeting called "SE Team Meeting" on 23 March 2022 from 1415hrs to 1615hrs

   * **`deletec`**`3` : Deletes the 3rd contact shown in the current contact list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add contact n/NAME`, `NAME` is a parameter which can be used as `add contact n/John Doe`.

* Items in square brackets are optional.<br>
  e.g.  `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Listing all persons : `list`

Shows a list of all persons in the contact list.

Format: `list`

### Adding a contact : `addc`

Adds the specified contact to the contact list.

Format: `addc n/NAME e/EMAIL p/PHONE_NUMBER th/TELEGRAM_HANDLE [t/TAGS]...`

* Adds a person into the contact list with given email, phone number, telegram handle.
* Optionally Tags can also be specified

Examples:
* `addc n/Alice Lee e/alice.lee@u.nus.edu p/786454454 th/theor9 t/database expert t/CS2103 teammate` adds the contact Alice Lee with the given email, phone, telegram and tags
* `addc n/Bob Tan p/91234567` gives an error message as e/EMAIL and th/TELEGRAM is not optional

### Deleting a contact : `deletec`

Removes the specified person from the contact list

Format: `deletec CONTACT_INDEX`

* Deletes the person at the specified CONTACT_INDEX
* The index refers to the index number shown in the displayed contact list
* The index must be a positive integer 1, 2, 3, …​

Examples:
*`deletec 2` deletes the 2nd person in the address book
*`deletec 0` returns an error for invalid input


### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Adding a meeting : `addm`

Adds a meeting to the meeting list.

Format: `addm n/NAME d/DATE st/START_TIME et/END_TIME [pt/PARTICIPANTS_INDEX]... [t/TAGS]...`

* Schedules a meeting with a specified date, start time and end time to the address book.
* Optionally, the people involved in the meeting can also be specified.
* `DATE` requires the format **DD/MM/YYYY** e.g. 20/02/2022
* `START_TIME` and `END_TIME` requires the format **hhmm** e.g. 2359

Examples:
* `addm n/CS2103 Project Discussion d/20/02/2022 st/1800 et/1930 pt/1 pt/2 pt/3 pt/4 pt/5`
  Adds the meeting "CS2103 Project Meeting" with the given date, time and contacts.
* `addm n/JAVA Workshop d/23/02/2022 st/1030 et/1230`
  Adds meeting "JAVA Workshop" with given date and time.
* `addm n/Job Interview st/1500 et/1700`
  Returns error message as d/DATE is missing.


### Deleting a meeting : `deletem`

Deletes the specified meeting from the displayed meeting list

Format: `deletem MEETING_INDEX`

* Deletes the meeting at the specified `MEETING_INDEX`.
* The index refers to the index number shown in the displayed meetings list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deletem 3` deletes the 3rd meeting in the displayed meeting list.
* `deletem -1` returns an error for invalid input.

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddresSoC data (both the contact list and the meeting list) is saved in the hard disk automatically after
any command that changes the data. There is no need to save manually.

### Editing the data file

Advanced users are welcome to update data directly by editing the JSON file where the data is saved. To do so, navigate to the
folder containing the `addressoc.jar` file to find a folder called `data`. Access the folder and edit the `addressoc.json` file found inside.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressSoC will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add contact** | `addc n/NAME e/EMAIL [p/PHONE_NUMBER] [th/TELEGRAM_HANDLE] [t/TAGS]...` <br> e.g., `addc n/Alice Lee e/alice.lee@u.nus.edu p/76054673 th/alicey76 t/database expert t/CS2103 teammate`
**Delete contact** | `deletec CONTACT_INDEX` <br> e.g., `deletec 2`
**Clear** | `clear`
**Add Meeting** | `addm n/NAME d/DATE st/START_TIME et/END_TIME [pt/PARTICPANTS_INDEX]...` <br>e.g., `addm n/CS2103 Project Discussion d/20/02/2022 st/1800 et/1930 pt/1 pt/2 pt/3`
**Delete Meeting** | `deletem MEETING_INDEX`<br> e.g., `deletem 2`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
