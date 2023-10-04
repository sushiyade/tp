---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AB-3 User Guide

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `tab contacts` : switches tab to the 'contacts' tab.
   
   * `list` : Lists the relevant information in the respective tabs.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Switching tabs : `tab`

Switch views to the specific tab

Format: `tab TAB_NAME`

Acceptable values for `TAB_NAME`:

* `contacts`

* `events`

* `finances`

* `dashboard`

| <span style ='color: green; font-weight: bold;'>Positive Examples</span> | <span style ='color: red; font-weight: bold;'>Negative Examples</span> | <span style ='color: darkred; font-weight: bold;'>Error Message</span>                                 |
|:------------------------------------------------------------------------:|:----------------------------------------------------------------------:|--------------------------------------------------------------------------------------------------------|
|                              `tab contacts`                              |                             `tab contact`                              | <span style ='color: darkred; text-decoration: underline;'>Unkown parameter<br>Invalid tab name</span> |
|                               `tab events`                               |                                 `tab`                                  | <span style ='color: darkred; text-decoration: underline;'>Missing parameter</span>                    |

> **RESULT:** Tab will be switched to Contacts on GUI

### Contact Management

To view contacts tab, either click on the “contacts” button, or use the command tab `contacts` to switch tabs.

#### Listing all contact: Contacts Tab → `list`

Shows a list of all contacts in the **Contacts** tab.

Format: `list`

| <span style ='color: green; font-weight: bold;'>Positive Examples</span> | <span style ='color: red; font-weight: bold;'>Negative Examples</span> | <span style ='color: darkred; font-weight: bold;'>Error Message</span>           |
|:------------------------------------------------------------------------:|:----------------------------------------------------------------------:|----------------------------------------------------------------------------------|
|                                  `list`                                  |                                 `lsit`                                 | <span style ='color: darkred; text-decoration: underline'>Invalid command</span> |

#### Finding contact: Contacts Tab → `find`

Shows a list of contacts that contains specific string

Format: `find KEYWORD [MORE_KEYWORDS]`

<box type="info" seamless>
    * Using partial keywords will be matched. e.g. 'ha' will match 'hans'
    * The search is case-insensitive. e.g `hAnS` will match `Hans`
    * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
    * Only the name is searched.
    * Persons matching at least one keyword will be returned (i.e. `OR` search).
      e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
</box>

| **Parameter** | **Format**                | **Examples** (#g#Valid##/#r#Invalid##) |
|:-------------:|---------------------------|----------------------------------------|
|   `KEYWORD`   | Text up to 256 characters | #g#Hans##<br>#g#3##                    |

| <span style ='color: green; font-weight: bold;'>Positive Examples</span> | <span style ='color: red; font-weight: bold;'>Negative Examples</span> | <span style ='color: darkred; font-weight: bold;'>Error Message</span>                                                     |
|:------------------------------------------------------------------------:|:----------------------------------------------------------------------:|----------------------------------------------------------------------------------------------------------------------------|
|                               `find hans`                                |                              `find Alex`                               | <span style ='color: darkred; text-decoration: underline'>Unknown Entry</span><br> No name in contacts with 'Alex'         |
|                              `find hAns Bo`                              |                                 `find`                                 | <span style ='color: darkred; text-decoration: underline'>Missing Parameter</span><br> Please add a KEYWORD to search with |

> **RESULT:** Shows names containing given KEYWORD(s) in Contacts tab

![result for 'find alex david'](images/findAlexDavidResult.png)

#### Adding a contact: Contacts Tab → add `add`

Adds a new contact into the **Contacts** tab.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [c/COMPANY] [t/TELEGRAM_NAME]`

<box type="warning" seamless>
    * `[a/ADDRESS]` should preferably be the company’s address
    * Note that each contact can have:
        * At most one `[a/ADDRESS]`
        * At most one `[c/COMPANY]` 
        * At most one `[t/TELEGRAM_NAME]`
</box>

|   **Parameter**   | **Format**                                                                                                     | **Examples** (#g#Valid##/#r#Invalid##)                                                               |
|:-----------------:|----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|
|      `NAME`       | Text up to 256 characters<br>Must be unique                                                                    | #g#Annie Dunkins##<br>#g#'Chewbaca' The 1st##                                                        |
|  `PHONE_NUMBER`   | Numeric values<br>(optional "+" prefix)                                                                        | #g#81234567##<br>#g#+6581234567##<br>#r#A0u38niufd##<br>#r#(phone number cannot contain alphabets)## |
|      `EMAIL`      | %%\[emailID]@[domainName\]%%<br>[Check email format here](https://www.site24x7.com/tools/email-validator.html) | #g#anniedun.kins@gmail.com##<br>#r#@gmail.com (no email ID)##                                        |
|    `[ADDRESS]`    | Text up to 256 characters                                                                                      | #g#5 Science Park Dr, Singapore 118265##                                                             |
|    `[COMPANY]`    | Text up to 256 characters                                                                                      | #g#Shopee##<br>#g#Sh0p33##                                                                           |
| `[TELEGRAM_NAME]` | Only a-z, 0-9, and underscores allowed                                                                         | #g#destiny_30##<br>#r#destiny.30##<br>#r#(Telegram don't accept'.' in their username format)##       |

|         <span style ='color: green; font-weight: bold;'>Positive Examples</span>         |                <span style ='color: red; font-weight: bold;'>Negative Examples</span>                | <span style ='color: darkred; font-weight: bold;'>Error Message</span>                                                                  |
|:----------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------:|-----------------------------------------------------------------------------------------------------------------------------------------|
|                `add n/‘Chewbaca’ The 1st p/+659123139 e/chewie@gmail.com`                |                               `add   p/+659832139 e/chewie@gmail.com`                                | <span style ='color: darkred; text-decoration: underline'>Missing Parameter</span><br> Name is missing                                  |
|                 `add n/Annie Dunkins p/+610489630614 e/ann1e@gmail.com`                  |                                                `add`                                                 | <span style ='color: darkred; text-decoration: underline'>Missing Parameter</span><br> Name, Phone number, and Email is missing         |
| `add n/Annie Dunkins p/+610489630614 e/ann1e@gmail.com a/Opera house c/NAB t/anniebirds` | `add n/Annie Dunkins p/+610489630614 e/ann1e@gmail.com a/Opera house c/NAB c/Atlassian t/anniebirds` | <span style ='color: darkred; text-decoration: underline'>Excessive number of Parameters</span><br> At most one company name is allowed |

> **RESULT:** Contact `{NAME}` added successfully!

#### Deleting a contact with index: Contact Tab → `delete`

Deletes the specified contact from the **Contacts** tab using index. Will ask the user to confirm deletion.

Format: `delete INDEX [MORE_INDEX]`

* Deletes a **contact entry** at specified `INDEX`
* Able to delete multiple contacts at once
  * Separate each `INDEX` by only a space “ “
* The index refers to the index number shown in the contact `list`
  * `INDEX` will not be accepted if not found within the contact `list`

|   **Parameter**   | **Format**                                                                                                     | **Examples** (#g#Valid##/#r#Invalid##)                                                               |
|:-----------------:|----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|
|      `INDEX`      | Positive integers                                                                                              | #g#1##<br>#g#123##<br>#r#-1 (must be positive number)##                                              |

| <span style ='color: green; font-weight: bold;'>Positive Examples</span> | <span style ='color: red; font-weight: bold;'>Negative Examples</span> | <span style ='color: darkred; font-weight: bold;'>Error Message</span>                                                                                   |
|:------------------------------------------------------------------------:|:----------------------------------------------------------------------:|----------------------------------------------------------------------------------------------------------------------------------------------------------|
|                                `delete 1`                                |                                `delete`                                | <span style ='color: darkred; text-decoration: underline'>Missing Parameter</span><br> Name is missing                                                   |
|                              `delete 1 2 3`                              |                            `delete 1, 2, 3`                            | <span style ='color: darkred; text-decoration: underline'>Invalid Format</span><br> Separate multiple index with space only                              |
|                                `delete 2`                                |                             `delete 2000`                              | <span style ='color: darkred; text-decoration: underline'>Out of Range</span><br> Index is out of range, choose an index that is within the contact list |
|                                `delete 3`                                |                       `delete -3`<br>`delete 0`                        | <span style ='color: darkred; text-decoration: underline'>Out of Range</span><br> Choose a positive index that is within contact list                    |

> **RESULT:** 
> Are you sure you want to delete contact `{NAME}` ?
> Y: Contact `{NAME}` at index `{INDEX}` deleted successfully.
> N: No deleting it is.

#### Deleting a contact with keyword: Contact Tab → `delete`

Deletes the specified contact from the **Contacts** tab using the keyword. Will ask the user to confirm deletion. Uses the same formatting as !!**find**!!.

Format: `delete KEYWORD [MORE_KEYWORDS]`

* Delete contact(s) that contains `KEYWORD`
* Able to delete multiple contacts at once containing `KEYWORD`(s)
* Using partial keywords will be matched. e.g. ha will match hans
> `ha` → 3. Hans Gruber
* The search is case-insensitive. e.g. `hAnS` will match `Hans`
> `hAnS` → 4. Hans Gruber
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang
> `Hans Bo` → 3. Hans Gruber
>             4. Bo Yang 
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
> `Bo Hans` → 3. Hans Gruber
>             4. Bo Yang
* Only the `NAME` of the contact is searched


| **Parameter** | **Format**                | **Examples** (#g#Valid##/#r#Invalid##) |
|:-------------:|---------------------------|----------------------------------------|
|   `KEYWORD`   | Text up to 256 characters | #g#Hans##<br>#g#3##                    |

| <span style ='color: green; font-weight: bold;'>Positive Examples</span> | <span style ='color: red; font-weight: bold;'>Negative Examples</span> | <span style ='color: darkred; font-weight: bold;'>Error Message</span>                                                                |
|:------------------------------------------------------------------------:|:----------------------------------------------------------------------:|---------------------------------------------------------------------------------------------------------------------------------------|
|                              `delete hans`                               |                             `delete Alex`                              | <span style ='color: darkred; text-decoration: underline'>Unknown Entry</span><br> No name in contacts with "Alex"                    |
|                             `delete hAns Bo`                             |                            `delete`                            | <span style ='color: darkred; text-decoration: underline'>Missing Parameter</span><br> Please add a KEYWORD to searcg and delete with |

> **RESULT:**
> Are you sure you want to delete contact `{NAME}` ?
> Y: Contact `{NAME}` at index `{INDEX}` deleted successfully.
> N: No deleting it is.

#### Editing a person : `edit`

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


### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                      |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                               |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**   | `list`                                                                                                                                                                |
| **Help**   | `help`                                                                                                                                                                |
