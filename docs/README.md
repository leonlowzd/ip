# Duke User Guide
Duke is a Java application that is a Personal Assistant Chat bot that helps manage users with their tasks.
```
____________________________________________________________ 
Hello! I'm Duke
What can I do for you?

____________________________________________________________ 
```

## Features 

Duke supports 3 tasks types:
1. Todo
2. Deadlines
3. Events

Deadlines and Events will require a date. Duke will convert date to Day-Month-Year format (for example: 02 October 2020) if user inputs in YYYY-MM-DD format, else the date will be recorded as per user input

### **Here is the full feature list of Duke:**

### **Command Features:**
#### ToDo: `todo`
Creates a todo task and adds it to the list

**Sample printout:**

```
todo print assignment
____________________________________________________________
Nice, I've added the following task to the list
[T][✘] print assignment
Now you have 1 in the list.

____________________________________________________________
```
#### Deadline: `deadline`
Creates a Deadline task and adds it to the list

**Sample printout:**

```
deadline CS2113 assignment /by 2020-10-02
____________________________________________________________
Nice, I've added the following task to the list
[D][✘] CS2113 assignment (by: 02 October 2020)
Now you have 2 in the list.

____________________________________________________________
```
#### Event: `event`
Creates an Event task and adds it to the list

**Sample printout:**

```
event family dinner /at This thursday 7:00pm
____________________________________________________________
Nice, I've added the following task to the list
[E][✘] family dinner (at: This thursday 7:00pm)
Now you have 3 in the list.

____________________________________________________________
```
#### List: `list`
List out all the tasks in the list

**Sample printout:**

```
list
____________________________________________________________
Here are the tasks in your list:
1. [T][✘] print assignment
2. [D][✘] CS2113 assignment (by: 02 October 2020)
3. [E][✘] family dinner (at: This thursday 7:00pm)

____________________________________________________________
```
#### Done: `done`
Marks a specific task as done

**Sample printout:**

```
done 1
____________________________________________________________
Nice! I've marked this task as done: 
[T][✓] print assignment
____________________________________________________________
```
#### Delete: `delete`
Deletes a specific task from the list

**Sample printout:**

```
delete 2
____________________________________________________________
Noted. I've removed this task: 
[D][✘] CS2113 assignment (by: 02 October 2020)
Now you have 2 in the list.
```
____________________________________________________________
### Find: `find`
Find keyword in task list

**Sample printout:**

```
find family
____________________________________________________________
Here are the tasks in your list:
1. [E][✘] family dinner (at: This thursday 7:00pm)

____________________________________________________________
```
### Bye: `bye`
Exits the program

**Sample printout:**

```
bye
____________________________________________________________
Good bye!
____________________________________________________________
```
### **Back End Features:**
#### Save (Write) 
Automatically saves all tasks into a text file at `~/Documents` whenever a new Task is added
#### Save (Read) 
At start of the program, Duke will load saved Tasks from `~/Documents`,
Duke will ensure that text file is not corrupted and is at the correct format 

## Usage

Command is **CASE SENSITIVE**


| Feature  | Usage | Example |
| ---------| ------ | ------- |
| ToDo |`todo TASK_DESCRIPTION` | `todo Send email to prof ` |
| Deadline | `deadline TASK_DESCRIPTION /by DATE` | `deadline CS2113 IP /by 10-2 before tutorial`  `event CS2113 IP /by 2020-10-2`|
| Event | `event TASK_DESCRIPTION /at DATE` | `event CS2113 tutorial /at 10-2 1300` or `event CS2113 tutorial /at 2020-10-2`|
| List |`list` | `list` |
| Done |`done INDEX`| `done 1` |
| Delete | `delete INDEX` | `delete 1` |
| Find |`find KEYWORD` | `find tutorial` |
| Bye |`bye` | `bye` |

## Error Messages

Duke is intelligent enough to handle most of the user input errors, the table below highlights the error messages and shows what they mean:

### Command Error Messages

| Message | Description |
|---------|-------------|
|`OOPS!!! I'm sorry, but I don't know what that means :-(` | An invalid Command is inputted, check case sensitivity|
|`OOPS!!! Illegal index, it does not exist in the list` | Specifically for `done` and `delete` command; Index inputted is: Out of the list or not an `int` character|
|`☹ OOPS!!! Illegal date format!! /at for events and /by for deadlines`| Specifically for `Event` and `Deadline` command; Either date is empty or wrong date identifier is used|
|`☹ OOPS!!! Empty date` | Date is empty|
| `OOPS!!! Empty description`| Task description is empty|

### File Errors Messages

| Message | Description |
| --------|-------------|
|`Unable to write file to text file.` | File directory cannot be found | 
|`Unable to open file from memory.`| File cannot be read from memory, task list will be resetted |
|`Text file is corrupted. Some of the pre-existing tasks might not be loaded.`| File is corrupted with invalid inputs |
