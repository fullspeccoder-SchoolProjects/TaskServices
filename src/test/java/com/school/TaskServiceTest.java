package com.school;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    TaskService service = new TaskService();
    Task task1 = new Task("2186", "Do Laundry", "Wash clothes, dry clothes, and fold clothes.");
    Task task2 = new Task("5186", "Go to the gym", "Create workout routine and workout.");
    Task task3 = new Task("4213", "Clean the kitchen", "Clean countertops & clean appliances");
    Task task4 = new Task("9467", "Take Kobe to the vet", "Take Kobe to get his shots");
    ArrayList<Task> tasks = new ArrayList<>();

    @Before
    public void setUp() {
        service.addTask(task1);
        service.addTask(task2);
        tasks.add(task3);
        tasks.add(task4);
        service.addTask("4567", "Car wash membership", "Get membership to car wash and get car washed");
    }

    // Single Add Tests
    @Test
    public void shouldAddSingleTask() {
        assertEquals("Do Laundry", service.getTask("2186").getName());
    }

    @Test
    public void shouldAddTaskWithIdNameDescription() {
        assertEquals("Car wash membership", service.getTask("4567").getName());
    }

    @Test
    public void shouldAddTaskWithTaskAndNewId() {
        service.addTask(task1, "5467");
        assertEquals("Do Laundry", service.getTask("5467").getName());
    }

    @Test
    public void shouldAddTaskWithNewStaticId() {
        service.addTask(task1, "4568");
        assertEquals("Do Laundry", service.getTask("4568").getName());
    }

    @Test
    public void shouldNotAddTaskWithSimilarId() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask(task1));
    }

    @Test
    public void shouldNotAddTaskWithSimilarIdNameDescription() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask("4567", "Car wash membership", "Get membership to car wash and get car washed"));
    }

    @Test
    public void shouldNotAddTaskWithAlphabeticId() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask("1234a", "Car wash membership", "Get membership to car wash and get car washed"));
    }

    @Test
    public void shouldNotAddTaskWithNullId() {
        assertThrows(NullPointerException.class, () -> service.addTask(null, "Car wash membership", "Get membership to car wash and get car washed"));
    }

    @Test
    public void shouldNotAddTaskWithNullName() {
        assertThrows(NullPointerException.class, () -> service.addTask("7645", null, "Get membership to car wash and get car washed"));
    }

    @Test
    public void shouldNotAddTaskWithNullDescription() {
        assertThrows(NullPointerException.class, () -> service.addTask("7645", "Car wash membership", null));
    }

    @Test
    public void shouldNotAddTaskWithLongId() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask("12354846518974458", "Car wash membership", "Get membership to car wash and get car washed"));
    }

    @Test
    public void shouldNotAddTaskWithLongName() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask("7645", "lkj;nasdkjvnaskljdfbgnljkhsdfjknakjgbjlkiasbndsfglkjnasdefg", "Get membership to car wash and get car washed"));
    }

    @Test
    public void shouldNotAddTaskWithLongDescription() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask("7645", "Car wash membership", ";oihasdkjhasdkjlgfhjaksfhjkasdhngjkahsjohjaws;jfgjkl;jsadngfkjhjasd;ljfhnas;kdjgjnl;kjasdgj;kjasdehf;kjawsedhgwbkjlashdnfjklahsdgadbsflkjbsadf"));
    }

    @Test
    public void shouldNotAddTaskWithTaskAndSimilarId() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask(task1, "5186"));
    }

    // Multiple Add Tests
    @Test
    public void shouldAddMultipleTasksOneAtATime() {
        assertEquals("Create workout routine and workout.", service.getTask("5186").getDescription());
    }

    @Test
    public void shouldAddMultipleTasksAtOnce() {
        service.addMultipleTasks(tasks);
        assertEquals("Take Kobe to the vet", service.getTask("9467").getName());
    }

    @Test
    public void shouldNotAddMultipleTasksWithSimilarId() {
        ArrayList<Task> newTasks = new ArrayList<>();
        newTasks.add(new Task("2186", "Go get groceries", "Go to WalMart and pickup groceries"));
        assertThrows(IllegalArgumentException.class, () -> service.addMultipleTasks(newTasks));
    }

    // Get Tests
    @Test
    public void shouldGetTaskBack() {
        assertInstanceOf(Task.class, service.getTask("2186"));
    }

    @Test
    public void shouldNotGetTaskBackWhenTaskNotFound() {
        assertThrows(IllegalArgumentException.class, () -> service.getTask("4312"));
    }

    @Test
    public void shouldNotGetTaskBackWhenTaskNotFoundInList() {
        service.addMultipleTasks(tasks);
        assertThrows(IllegalArgumentException.class, () -> service.getTask("1345"));
    }

    @Test
    public void shouldNotGetTaskBackWithNullId() {
        assertThrows(NullPointerException.class, () -> service.getTask(null));
    }

    @Test
    public void shouldNotGetTaskBackWithEmptyId() {
        assertThrows(NullPointerException.class, () -> service.getTask(""));
    }

    // Delete Tests
    @Test
    public void shouldDeleteTaskWithId() {
        service.deleteTask("2186");
        assertThrows(IllegalArgumentException.class, () -> service.getTask("2186"));
    }

    @Test
    public void shouldDeleteTaskFromTask() {
        service.deleteTask(task1);
        assertThrows(IllegalArgumentException.class, () -> service.getTask("2186"));
    }

    @Test
    public void shouldNotDeleteTaskWithIdWhenTaskNotFound() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteTask("4312"));
    }

    @Test
    public void shouldNotDeleteTaskWithIdWhenTaskIdIsNull() {
        assertThrows(NullPointerException.class, () -> service.deleteTask((String) null));
    }

    @Test
    public void shouldNotDeleteTaskWithIdWhenTaskIdIsEmptyString() {
        assertThrows(NullPointerException.class, () -> service.deleteTask(""));
    }

    @Test
    public void shouldNotDeleteTaskWhenTaskNotFound() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteTask(new Task("7648", "Go get groceries", "Go to WalMart and pickup groceries")));
    }

    // Update Tests
    @Test
    public void shouldUpdateNameInTask() {
        service.updateTask("2186", "Fold clothes", "Gather all laundry and fold clothes");
        assertEquals("Fold clothes", service.getTask("2186").getName());
    }

    @Test
    public void shouldUpdateDescriptionInTask() {
        service.updateTask("2186", "Fold clothes", "Gather all laundry and fold clothes");
        assertEquals("Gather all laundry and fold clothes", service.getTask("2186").getDescription());
    }

    @Test
    public void shouldUpdateNameInTaskWithTask() {
        service.updateTask(task1, "Fold clothes", "Gather all laundry and fold clothes");
        assertEquals("Fold clothes", service.getTask("2186").getName());
    }

    @Test
    public void shouldUpdateDescriptionInTaskWithTask() {
        service.updateTask(task1, "Fold clothes", "Gather all laundry and fold clothes");
        assertEquals("Gather all laundry and fold clothes", service.getTask("2186").getDescription());
    }

    @Test
    public void shouldNotUpdateWithEmptyId() {
        assertThrows(NullPointerException.class, () -> service.updateTask("", "Fold clothes", "Gather all laundry and fold clothes"));
    }

    @Test
    public void shouldNotUpdateWithNullId() {
        assertThrows(NullPointerException.class, () -> service.updateTask((String) null, "Fold clothes", "Gather all laundry and fold clothes"));
    }

    @Test
    public void shouldNotUpdateWithNullName() {
        assertThrows(NullPointerException.class, () -> service.updateTask("5186", null, "Gather all laundry and fold clothes"));
    }

    @Test
    public void shouldNotUpdateWithNullDescription() {
        assertThrows(NullPointerException.class, () -> service.updateTask("5186", "Fold clothes", null));
    }

    @Test
    public void shouldNotUpdateWithIdNotFound() {
        assertThrows(IllegalArgumentException.class, () -> service.updateTask("7648", "Fold clothes", "Gather all laundry and fold clothes"));
    }

    @Test
    public void shouldNotUpdateWithTaskIdNotFound() {
        Task newTask = new Task("7648", "Do laundry", "Gather all laundry and fold clothes");
        assertThrows(IllegalArgumentException.class, () -> service.updateTask(newTask, "Fold clothes", "Gather all laundry and fold clothes"));
    }
}