package com.school;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    final private Task task1 = new Task("5186", "Do Laundry", "Wash clothes, dry clothes, and put away clothes.");
    final private Task task2 =  new Task("2186", "Go to the gym", "Go through workout routine at the gym.");
    final private Task newTaskWithTask = new Task(task2, "5186");

    @Test
    public void shouldCreateTaskObject() {
        assertEquals(Task.class, task1.getClass());
    }

    @Test
    public void shouldCreateTaskObjectWithIdAsStaticProperty() {
        assertEquals("1", new Task("0", "Fold laundry", "Fold all laundry in house").getId());
    }

    @Test
    public void shouldCreateTaskObjectWithTaskAndIdAsStaticProperty() {
        assertEquals("2", new Task(task1, "0").getId());
    }

    @Test
    public void shouldNotCreateTaskObjectWithNullId() {
        assertThrows(NullPointerException.class, () -> new Task(null, "Do Laundry", "Wash clothes, dry clothes, and put away clothes."));
    }

    @Test
    public void shouldNotCreateTaskWithNullName() {
        assertThrows(NullPointerException.class, () -> new Task("5186", null, "Wash clothes, dry clothes, and put away clothes."));
    }

    @Test
    public void shouldNotCreateTaskWithNullDescription() {
        assertThrows(NullPointerException.class, () -> new Task("5186", "Do Laundry", null));
    }

    @Test
    public void shouldNotCreateTaskObjectWithLongId() {
        assertThrows(IllegalArgumentException.class, () -> new Task("5156465415644165486", "Do Laundry", "Wash clothes, dry clothes, and put away clothes."));
    }

    @Test
    public void shouldNotCreateTaskWithLongName() {
        assertThrows(IllegalArgumentException.class, () -> new Task("5186", "Do Laundrl;kjsdfkmbvnsdl;kfgmnklasfgnjksdnfgh;lksnedrgjknsd;fjkgmnklaewrfgsfgjsdfgsrftgjsrtyhsrethy", "Wash clothes, dry clothes, and put away clothes."));
    }

    @Test
    public void shouldNotCreateTaskWithLongDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Task("5186", "Do Laundry", "Wash clothes, dry clothes;lkjasedgnsaedklgnj;klasgnkl;sdamtl;sangvkjdnkl;tfmnsdfjklgnask;jfnmkl;sadmfgnjk;wanebrgmlnasdgnkladfng kjsdnfgmnsdfklngnasedmkgnklsndfgfnmesdarg, and put away clothes."));
    }

    @Test
    public void shouldNotCreateTaskObjectWithLetteredId() {
        assertThrows(IllegalArgumentException.class, () -> new Task("5186a", "Do Laundry", "Wash clothes, dry clothes, and put away clothes."));
    }

    @Test
    public void createdTaskObjectBeginsWithCapitalLetterName() {
        assertEquals("Do Laundry", task1.getName());
    }

    @Test
    public void createdTaskObjectBeginsWithCapitalLetterDescription() {
        assertEquals("Wash clothes, dry clothes, and put away clothes.", task1.getDescription());
    }

    @Test
    public void createTaskWithTaskAndUniqueId() {
        assertEquals("5186", newTaskWithTask.getId());
    }

    @Test
    public void cannotCreateTaskWithNullId() {
        assertThrows(NullPointerException.class, () -> new Task(task1, null));
    }

    @Test
    public void cannotCreateTaskWithLongId() {
        assertThrows(IllegalArgumentException.class, () -> new Task(task1, "5165486484465489748"));
    }

    @Test
    public void canGetId() {
        assertEquals("2186", task2.getId());
    }

    @Test
    public void canGetName() {
        assertEquals("Go to the gym", task2.getName());
    }

    @Test
    public void canGetDescription() {
        assertEquals("Go through workout routine at the gym.", task2.getDescription());
    }

    @Test
    public void canSetName() {
        task1.setName("Wash dishes");
        assertEquals("Wash dishes", task1.getName());
    }

    @Test
    public void canSetDescription() {
        task2.setDescription("Go through workout routine.");
        assertEquals("Go through workout routine.", task2.getDescription());
    }
}