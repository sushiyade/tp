---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# FreelanceBuddy Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ContactsTab`, `FinanceTab`, `EventsTab`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

Depending on the Tab currently selected by the user, the `MainWindow` will display the corresponding `PersonListPanel`, `FinanceListPanel` or `EventListPanel`.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component within a Tab:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.</br>
**Note:** This takes place within the ContactsTab. 

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteContactCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:
1. All three tabs have their own `Logic` objects. These `Logic` objects share the same `Model` and `Storage`, but have different `Parsers`
1. When `Logic` is called upon to execute a command, it is passed to that Tab's `Parser` (e.g. in our class diagram above the `ContactParser` receives the command). 
1. This parser then creates a parser that matches the command (e.g., `DeleteContactCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteContactCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a contact).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the respective Parsers (i.e. `ContactParser`, `FinanceParser`, `EventParser`) class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddContactCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddContactCommand`) which the `ContactParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddContactCommandParser`, `DeleteFinanceCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W09-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="600" />


The `Model` component,

* stores the address book data, event book data and finance book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable i.e., `ObservableList<Person>` where `Person` objects can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change. This is the same for `Event` and `Finance`.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other seven components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below.


**For AddressBook:**

<puml src="diagrams/BetterModelClassDiagramAddressBook.puml" width="450" />

**For EventsBook:** note that multiple `Person` objects are can be associated to an Event as clients

<puml src="diagrams/BetterModelClassDiagramEventsBook.puml" width="450" />

**For FinanceBook:**

<puml src="diagrams/BetterModelClassDiagramFinancesBook.puml" width="450" />


</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save the following data in JSON format, and read them back into corresponding objects.
  * address book data
  * events book data
  * finance book data
  * user preference data
* inherits from `AddressBookStorage`, `EventsBookStorage`, `FinanceBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**: Freelancers

| Characteristics                                   | What **FreelanceBuddy** offers                                                                                              |
|---------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| May have multiple concurrent clients and projects | One stop app for managing your contacts, finances and day-to-day events in a easy and structured way                        |
| Good at touch-typing, efficient with keyboard     | CLI interface enables quick input and retrieval of your contacts, finances and events without the need of moving your mouse |
| Potentially busy and want quick updates           | Users can get summary statistics, organise their data and much more all with just one command                               |

**Value proposition**: Manage all your freelancing needs in an app that is faster than your typical GUI driven apps.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

#### General

| Priority |  As a …​  | I want to …​                          | So that I can…​                                                                                   |
|:--------:|:---------:|---------------------------------------|---------------------------------------------------------------------------------------------------|
| `* * *`  | new user  | see usage instructions                | refer to instructions when I forget how to use the App                                            |
| `* * *`  |   user    | switch between the different tabs     | view Contacts, Events and Finances in their respective tabs                                       |
|  `* *`   |   user    | see an overview of all my tabs        | quickly get a look at the important details without needing to navigating into tabs unnecessarily |
|   `* `   | lazy user | FreelanceBuddy to predict my commands | quickly input commands without typing the entire command                                          |

#### For Client Contact Management

| Priority |     As a …​      | I want to …​                            | So that I can…​                                                     |
|:--------:|:----------------:|-----------------------------------------|---------------------------------------------------------------------|
| `* * *`  |       user       | add a new client contact                |                                                                     |
| `* * *`  |       user       | delete a client contact                 | remove entries that I no longer need                                |
| `* * *`  |       user       | view all my saved clients contact       | have an overview of all my clients contacts                         |
| `* * *`  |       user       | find a client contact by name           | locate clients by name without having to go through the entire list |
|  `* *`   |       user       | edit a saved client contact             | change any details that are wrong or have changed                   |
|  `* *`   | experienced user | see statistics of a client              | see how much money and time is spent on respective client projects  |
|  `* *`   |       user       | add favourite clients                   | see all important clients in the same place                         |
|   `*`    |  long-term user  | see if clients have been worth the time | better choose my clients for the future                             |
|   `*`    |  long-term user  | be able to manage client notes          | keep important notes about clients for future references            |
|   `*`    |       user       | create invoices with client details     | save time with manual inputting of client details                   |


#### For Events Management

| Priority |     As a …​      | I want to …​                       | So that I can…​                                          |
|:--------:|:----------------:|------------------------------------|----------------------------------------------------------|
| `* * *`  |       user       | add a new event                    |                                                          |
| `* * *`  |       user       | delete an old event                | remove entries that I no longer need                     |
| `* * *`  |       user       | view all my saved events           | have an overview of all my events sorted by date         |
| `* * *`  |       user       | see most urgent events             | know what is coming up soon or needs to be done urgently |
|  `* *`   |       user       | edit a saved event                 | change any details that are wrong or have changed        |
|  `* *`   | experienced user | tag clients to events              | see which client is involved in a particular event       |
|   `*`    |  forgetful user  | set recurring reminders for events | be alerted to task that I might forget                   |
|   `*`    |       user       | add location of events             | know where this event is taking place                    |

#### For Finance Management

> This covers both commission and expense. We will refer to both as C/E.

| Priority |        As a …​         | I want to …​                                    | So that I can…​                                           |
|:--------:|:----------------------:|-------------------------------------------------|-----------------------------------------------------------|
| `* * *`  |          user          | add a new C/E                                   |                                                           |
| `* * *`  |          user          | delete an old C/E                               | remove entries that I no longer need                      |
| `* * *`  |          user          | view all my saved C/E                           | have an overview of all my C/E                            |
|  `* *`   |          user          | filter by type (C/E)                            |                                                           |
|  `* *`   |          user          | edit a saved C/E                                | change any details that are wrong or have changed         |
|  `* *`   | user with many clients | tag clients to C/E                              | see which client is involved in a particular C/E          |
|  `* *`   | user with many clients | filter by tagged client                         |                                                           |
|  `* *`   | user with many clients | get a summary of total C/E by tagged client     | know how valuable a client is                             |
|  `* *`   | financially savvy user | add time due for C/E                            | know when to expect cash inflow/outflow                   |
|  `* *`   | financially savvy user | filter by a given day/month's C/E               |                                                           |
|  `* *`   | financially savvy user | get a summary of total C/E in a given day/month | get an idea of my financial situation for the time period |

### Use cases

#### Use Case: UC1 - Get help

**Precondition**: -

**MSS**

1. User requests for help.
2. FreelanceBuddy shows a pop-up with a link to the user guide.
3. User copies the URL and references the user guide.

   Use case ends.

#### Use Case: UC2 - Navigating between tabs

**Precondition**: User can be on **any** tab

**MSS**

1. FreelanceBuddy shows the current tab user is on.
2. User requests to switch to another tab (that is not the current one).
3. FreelanceBuddy switches to the specified tab by the user.
4. FreelanceBuddy shows the desired tab.

   Use case ends.

**Extensions**

* 2a. The user inputs an invalid syntax.
    * 2a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.
  
* 2b. User decides to stay on the current tab.

    User case resumes at step 4.

#### Use Case: UC3 - Add a client contact

**Precondition**: User is on **Contacts** tab

**MSS**

1. User requests to add a new contact with details.
2. FreelanceBuddy creates a new contact and shows it within the list.

   Use case ends.

**Extensions**
  
* 1a. The user inputs invalid syntax.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1. 
  

#### Use Case: UC4 - Delete a client contact

**Precondition**: User is on **Contacts** tab, **Contacts** list must have at least one entry

**MSS**

1. User requests to delete specific entry in the list.
2. FreelanceBuddy deletes the entry.

   Use case ends.

**Extensions**

* 1a. The user inputs with invalid syntax.
    * 1a1. FreelanceBuddy shows error message.

      Use case resumes at step 1. 

#### Use Case: UC5 - View all client contacts

**Precondition**: User is on **Contacts** tab, **Contacts** list must have at least one entry

**MSS**

1. User requests to view all Contacts.
2. FreelanceBuddy shows a list of all Contacts.

   Use case ends.

#### Use Case: UC6 - Find a specific client contact

**Precondition**: User is on **Contacts** tab, **Contacts** list must have at least one entry

**MSS**

1. User requests to find contacts using keywords.
2. FreelanceBuddy shows a list of Contacts that contains given keywords.

   Use case ends resumes at step 1.

**Extensions** 

* 1a. No contacts found that contains given keywords.
  * 1a1. FreelanceBuddy shows error message.

    Use case ends.

#### Use Case: UC7 - Add an event

**Precondition**: User is on **Events** tab

**MSS**

1. User requests to add a new event.
2. FreelanceBuddy creates the Event entry and shows it within the list.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid index.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC8 - Delete an event

**Precondition**: User is on **Events** tab, **Events** list must have at least one entry

**MSS**

1. User requests to delete a specific entry in the list.
2. FreelanceBuddy deletes the entry.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid index.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC9 - View all events

**Precondition**: User is on **Events** tab, **Events** list must have at least one entry

**MSS**

1. User requests to view all events.
2. FreelanceBuddy shows a list of all events.

Use case ends.

#### Use Case: UC10 - View most urgent events

**Precondition**: User is on **Events** tab, **Events** list must have at least one entry

**MSS**

1. User requests to view most urgent events.
2. FreelanceBuddy shows a list of most urgent events.

Use case ends.

#### Use Case: UC11 - Add a finance entry

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to add a new finance entry.
2. FreelanceBuddy adds the new finance entry to the top of the list.

    Use case ends.

**Extensions** 

* 1a. The user inputs an invalid syntax.
    * 1a1. FreelanceBuddy shows an error message.

        Use case resumes at step 1.

#### Use Case: UC12 - Delete a finance entry

**Precondition**: User is on **Finance** tab, **Finance** list must have at least one entry

**MSS**

1. User requests to delete a specific entry in the list.
2. FreelanceBuddy deletes the entry.

    Use case ends.

**Extensions**

* 1a. The user inputs an invalid index.
  * 1a1. FreelanceBuddy shows an error message.
    
    Use case resumes at step 1.

#### Use Case: UC13 - View all finance entries

**Precondition**: User is on **Finance** tab, **Finance** list must have at least one entry

**MSS**

1. User requests to view all finance entries.
2. FreelanceBuddy shows a list of all finance entries.

Use case ends.

#### Use Case: UC14 - Filter by finance entry type

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to filter finance entries by entry type.
2. FreelanceBuddy shows a list of finance entries of the given type.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid entry type.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC15 - Edit a finance entry

**Precondition**: User is on **Finance** tab, **Finance** list must have at least one entry

**MSS**

1. User requests to edit a specific entry in the list.
2. FreelanceBuddy edits the entry.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid index.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

* 1b. The user inputs an invalid syntax.
    * 1b1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC16 - Filter finance entries by client

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to filter finance entries by client.
2. FreelanceBuddy shows a list of finance entries tagged to the given client.

   Use case ends.

**Extensions**

* 1a. The user inputs a client that does not exist.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC17 - Get finance summary for tagged client

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to see finance summary for a client.
2. FreelanceBuddy shows the finance summary for the given client.

   Use case ends.

**Extensions**

* 1a. The user inputs a client that does not exist.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC18 - Filter finance entries by day/month

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to filter finance entries by day/month.
2. FreelanceBuddy shows a list of finance entries due in the given day/month.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid syntax.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC19 - Get finance summary for day/month

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to see finance summary for a day/month.
2. FreelanceBuddy shows the finance summary for the given day/month.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid syntax.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

### Non-Functional Requirements

|      Aspect       | Description                                                                                                                                                                                                                                                                                                                                     |
|:-----------------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Compatibility** | Should work on any _mainstream OS_ as long as it has Java `11` or above installed.                                                                                                                                                                                                                                                              |
|  **Performance**  | 1. Should be able to to hold up to 1000 contacts without much _performance degradation_.<br/><br/>2. Should respond to commands within 1s on average for any type of task.                                                                                                                                                                      |
|   **Usability**   | 1. User with above average typing speed should be able to accomplish most tasks faster than using mouse and GUIs.<br><br>2. CLI commands should be intuitive and easy for the user.<br></br>3. Error messages should be informative so users can troubleshoot effectively.<br><br>4. Clear and concise documentation available to assist users. |
|  **Reliability**  | 1. App should be robust and resilient to minimize any crashes or errors.<br><br> 2. App should have have regular automated backup procedures to ensure data is saved in the case of accidental shut down or crashes to the app.                                                                                                                 |

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Performance degradation**: Slowdown in performance, particularly in task execution times or data save/load times

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
