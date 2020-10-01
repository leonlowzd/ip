# Duke User Guide
Duke is a Java application that is a Personal Assistant Chat bot that helps manage users with their tasks.

## Features 

Duke supports 3 tasks types:
1. Todo
2. Deadlines (Will require a date), Date identifier: `/by`
3. Events (Will require a date), Date identifier: `/at`

### **Here is the full feature list of Duke:**

| Feature  |Description |
| ---------|------------|
| ToDo | Creates a ToDo task| 
| Deadline | Creates a Deadline task| 
| Event | Creates a Event  task|
| List | List out all of the tasks| 
| Done | Marks specific task as done|
| Delete | Deletes tasks from the list|
| Find | Find keyword in task list|
| Bye | Exits the program|


### **Back End Features:**
#### Save (Write) 
Automatically saves all tasks into a text file at `~/Documents` whenever a new Task is added
#### save (Read) 
At start of the program, Duke will load saved Tasks from `~/Documents`
Duke will ensure that text file is not corrupted and is at the correct format 

## Usage

Command is **CASE SENSITIVE**

| Feature  | Usage | Example |
| ---------| ------ | ------- |
| ToDo |`todo TASK_DESCRIPTION` | `todo Send email to prof ` |
| Deadline | `deadline TASK_DESCRIPTION /by DATE` | `deadline CS2113 IP /by 10-2 before tutorial`|
| Event | `event TASK_DESCRIPTION /at DATE` | `event CS2113 tutorial /at 10-2 1300` |
| List |`list` | `list` |
| Done |`done INDEX`| `done 1` |
| Delete | `delete INDEX` | `delete 1` |
| Find |`find KEYWORD` | `find tutorial` |
| Bye |`bye` | `bye` |

## Error Messages

Duke is intelligent enough to handle most of the user input errors, the table below highlights the error messages and shows what they mean:

### Command Errors
| Message | Description |
| --------|-------------|
|`OOPS!!! I'm sorry, but I don't know what that means :-(` | An invalid Command is inputted, check case sensitivity |
|`OOPS!!! Illegal index, it does not exist in the list` | Specifically for `done` and `delete` command; Index inputted is: Out of the list or not an `int` character
|`OOPS!!! Illegal date format!!`| Specifically for `Event` and `Deadline` command; Either date is empty or wrong date identifier is used
| `OOPS!!! Empty description`| Task description is empty

### File Errors

| Message | Description |
| --------|-------------|
|`Unable to write file to text file.` | File directory cannot be found | 
|`Unable to open file from memory.`| File cannot be read from memory, task list will be resetted |
|`Text file is corrupted. Some of the pre-existing tasks might not be loaded.`| File is corrupted with invalid inputs |