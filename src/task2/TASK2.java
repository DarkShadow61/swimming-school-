package task2;

import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

public class TASK2 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    initializeInstructors();
    students = initializeStudents(); 

        boolean exit = false;
        while (!exit) {
            printHeader("Welcome to the Swim School Management System");
            printMenu();
            
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewSwimStudentInformation();
                    break;
                case 2:
                    viewSwimLessonDetails();
                    break;
                case 3:
                    viewInstructorSchedule();
                    break; 
                case 4:
                   addNewSwimStudent();
                    break;
                case 5:
                    awardSwimQualification();
                    break;
                case 6:
                    moveSwimStudentFromWaitingList();
                    break;
                case 7:
                    exit = true;
                    System.out.println("\nExiting the system. Thank you!\n");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 7.\n");
            }
        }
        scanner.close();
    }

    private static void printHeader(String header) {
        System.out.println("========================================");
        System.out.println(header);
        System.out.println("========================================");
    }
    
    private static void printMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. View swim student information");
        System.out.println("2. View swim lesson details");
        System.out.println("3. View instructor schedule");
        System.out.println("4. Add new swim student");
        System.out.println("5. Award swim qualification");
        System.out.println("6. Move swim student from waiting list");
        System.out.println("7. Exit\n");
    }

private static List<SwimStudent> students = initializeStudents();

private static List<Instructor> instructors = new ArrayList<>();

private static void viewSwimStudentInformation() {
    Collections.sort(students, Comparator.comparing(student -> student.name));

    for (int i = 0; i < students.size(); i++) {
        System.out.println((i + 1) + ". " + students.get(i).name + " (" + students.get(i).level + ")");
    }

    System.out.print("Select a swim student by number: ");
    int index = scanner.nextInt();
    scanner.nextLine(); // Consume the newline

    if (index < 1 || index > students.size()) {
        System.out.println("Invalid selection. Please try again.");
        return;
    }

    SwimStudent selectedStudent = students.get(index - 1);

    if (selectedStudent.lesson != null) {
        System.out.println("Student Name: " + selectedStudent.name);
        System.out.println("Level: " + selectedStudent.level);
        System.out.println("Class Day: " + selectedStudent.lesson.day);
        System.out.println("Class Time: " + selectedStudent.lesson.time);
        System.out.println("Instructor: " + selectedStudent.lesson.instructor.name);
    } else {
        System.out.println("The student is on the waiting list.");
    }

    if (!selectedStudent.qualifications.isEmpty()) {
        System.out.println("Qualifications:");
        for (Qualification q : selectedStudent.qualifications) {
            System.out.println("- " + q.type + " " + q.level + " awarded by " + q.awardedBy.name);
        }
    }
}
private static List<SwimStudent> initializeStudents() {
    List<SwimStudent> dummyStudents = new ArrayList<>();
    

    Instructor instructor1 = new Instructor("Mr. alex");
    Instructor instructor2 = new Instructor("Ms. boby");
    

    SwimLesson lessonNovice = new SwimLesson("Monday", "17:00", instructor1);
    SwimLesson lessonImprover = new SwimLesson("Wednesday", "17:30", instructor2);
    SwimLesson lessonAdvanced = new SwimLesson("Friday", "18:00", instructor1); 

    SwimStudent student1 = new SwimStudent("Alice", "Novice");
    student1.lesson = lessonNovice;
    student1.addQualification(new Qualification("Distance Swim", "5m", instructor1));
    
    SwimStudent student2 = new SwimStudent("Bob", "Improver");
    student2.lesson = lessonImprover;
    student2.addQualification(new Qualification("Distance Swim", "100m", instructor2));
    student2.addQualification(new Qualification("Distance Swim", "200m", instructor2));
    
    SwimStudent student3 = new SwimStudent("Charlie", "Advanced");
  student3.lesson = lessonAdvanced; 
  
    student3.addQualification(new Qualification("Distance Swim", "800m", instructor1));
    student3.addQualification(new Qualification("Personal Survival", "Bronze", instructor1));
    
    SwimStudent student4 = new SwimStudent("Diana", "Novice");


    dummyStudents.add(student1);
    dummyStudents.add(student2);
    dummyStudents.add(student3);
    dummyStudents.add(student4);
    
    return dummyStudents;
}




private static void viewSwimLessonDetails() {
    System.out.println("Select the day of the swim class:");
    System.out.println("1. Monday");
    System.out.println("2. Wednesday");
    System.out.println("3. Friday");
    int dayChoice = scanner.nextInt();
    scanner.nextLine();
    
    String selectedDay = "";
    switch (dayChoice) {
        case 1:
            selectedDay = "Monday";
            break;
        case 2:
            selectedDay = "Wednesday";
            break;
        case 3:
            selectedDay = "Friday";
            break;
        default:
            System.out.println("Invalid day selection.");
            return;
    }
    
    System.out.println("Select the time of the swim class:");
    System.out.println("1. 17:00");
    System.out.println("2. 17:30");
    System.out.println("3. 18:00"); 
    int timeChoice = scanner.nextInt();
    scanner.nextLine(); 
    
    String selectedTime = "";
    switch (timeChoice) {
        case 1:
            selectedTime = "17:00";
            break;
        case 2:
            selectedTime = "17:30";
            break;
        case 3:
            selectedTime = "18:00";
            break;
        default:
            System.out.println("Invalid time selection.");
            return;
    }
    
    System.out.println("Select the level of the swim class:");
    System.out.println("1. Novice");
    System.out.println("2. Improver");
    System.out.println("3. Advanced");
    int levelChoice = scanner.nextInt();
    scanner.nextLine(); 
    
    String selectedLevel = "";
    switch (levelChoice) {
        case 1:
            selectedLevel = "Novice";
            break;
        case 2:
            selectedLevel = "Improver";
            break;
        case 3:
            selectedLevel = "Advanced";
            break;
        default:
            System.out.println("Invalid level selection.");
            return;
    }

    displayLessonDetails(selectedDay, selectedTime, selectedLevel);
}

private static void displayLessonDetails(String day, String time, String level) {
    for (SwimStudent student : students) {
        if (student.lesson != null && student.lesson.day.equals(day) && student.lesson.time.equals(time) && student.level.equals(level)) {
            System.out.println("Lesson Details:");
            System.out.println("Day: " + day + ", Time: " + time + ", Level: " + level);
            System.out.println("Instructor: " + student.lesson.instructor.name);
            

            long count = students.stream().filter(s -> s.lesson != null && s.lesson.day.equals(day) && s.lesson.time.equals(time) && s.level.equals(level)).count();
            System.out.println("Students in this class:");
            students.stream().filter(s -> s.lesson != null && s.lesson.day.equals(day) && s.lesson.time.equals(time) && s.level.equals(level)).forEach(s -> System.out.println("- " + s.name));
            System.out.println("Total students: " + count + " / 4");
            System.out.println("Spaces available: " + (4 - count));
            return;
        }
    }
    
    System.out.println("No swim lesson found for the selected day, time, and level.");
}






private static void viewInstructorSchedule() {
   
    List<Instructor> sortedInstructors = getSortedInstructors();
    if (sortedInstructors.isEmpty()) {
        System.out.println("No instructors found.");
        return;
    }

    for (int i = 0; i < sortedInstructors.size(); i++) {
        System.out.println((i + 1) + ". " + sortedInstructors.get(i).name);
    }

    System.out.print("Select an instructor by number: ");
    int instructorIndex = scanner.nextInt();
    scanner.nextLine();
    if (instructorIndex < 1 || instructorIndex > sortedInstructors.size()) {
        System.out.println("Invalid selection. Please try again.");
        return;
    }

    Instructor selectedInstructor = sortedInstructors.get(instructorIndex - 1);

    displayInstructorSchedule(selectedInstructor);
}

private static List<Instructor> getSortedInstructors() {
   
    Set<Instructor> instructors = new HashSet<>();
    for (SwimStudent student : students) {
        if (student.lesson != null) {
            instructors.add(student.lesson.instructor);
        }
    }
    List<Instructor> sortedInstructors = new ArrayList<>(instructors);
    sortedInstructors.sort(Comparator.comparing(instructor -> instructor.name));
    return sortedInstructors;
}

private static void displayInstructorSchedule(Instructor instructor) {
    System.out.println("Schedule for " + instructor.name + ":");
    boolean hasClasses = false;
    for (SwimStudent student : students) {
        if (student.lesson != null && student.lesson.instructor.equals(instructor)) {
            if (!hasClasses) {
                hasClasses = true;
                System.out.println("Allocated classes:");
            }
            System.out.println("- Day: " + student.lesson.day + ", Time: " + student.lesson.time + ", Level: " + student.level);
            System.out.println("  Students:");
            students.stream().filter(s -> s.lesson != null && s.lesson.instructor.equals(instructor) && s.lesson.day.equals(student.lesson.day) && s.lesson.time.equals(student.lesson.time)).forEach(s -> System.out.println("  - " + s.name));
        }
    }
    if (!hasClasses) {
        System.out.println("No allocated classes found.");
    }
}




private static void addNewSwimStudent() {
    System.out.print("Enter the name of the new student: ");
    String studentName = scanner.nextLine();

    System.out.println("Weekly schedule of novice classes:");
    List<SwimLesson> noviceLessons = students.stream()
                                             .filter(s -> s.lesson != null && s.level.equals("Novice"))
                                             .map(s -> s.lesson)
                                             .distinct()
                                             .collect(Collectors.toList());

    if (noviceLessons.isEmpty()) {
        System.out.println("There are currently no novice classes scheduled.");
        return;
    }

    noviceLessons.forEach(lesson -> {
        long enrolledStudents = students.stream()
                                        .filter(s -> s.lesson != null && s.lesson.equals(lesson))
                                        .count();
        System.out.println("- Day: " + lesson.day + ", Time: " + lesson.time + 
                           ", Instructor: " + lesson.instructor.name + 
                           ", Spaces available: " + Math.max(0, 4 - enrolledStudents));
    });

    System.out.println("Enter the day and time of the class you want to add the student to (e.g., Monday 17:00), or type 'wait' to add to the waiting list:");
    String selection = scanner.nextLine();

  if ("wait".equalsIgnoreCase(selection)) {
   
    SwimStudent newStudent = new SwimStudent(studentName, "Novice"); 
    students.add(newStudent); 
    System.out.println(studentName + " has been added to the waiting list.");



    } else {
        boolean added = false;
        for (SwimLesson lesson : noviceLessons) {
            if ((lesson.day + " " + lesson.time).equalsIgnoreCase(selection) &&
                students.stream().filter(s -> s.lesson != null && s.lesson.equals(lesson)).count() < 4) {
                SwimStudent newStudent = new SwimStudent(studentName, "Novice");
                newStudent.lesson = lesson;
                students.add(newStudent);
                System.out.println(studentName + " has been added to the class on " + selection);
                added = true;
                break;
            }
        }
        if (!added) {
            System.out.println("Invalid selection or class is full.");
        }
    }
}




private static void awardSwimQualification() {
    System.out.println("Select an instructor:");
    for (int i = 0; i < instructors.size(); i++) {
        System.out.println((i + 1) + ". " + instructors.get(i).name);
    }
    int instructorChoice = scanner.nextInt();
    scanner.nextLine();
    if (instructorChoice < 1 || instructorChoice > instructors.size()) {
        System.out.println("Invalid choice. Returning to main menu.");
        return;
    }
    Instructor instructor = instructors.get(instructorChoice - 1);

    System.out.println("Select a swim student:");
    for (int i = 0; i < students.size(); i++) {
        System.out.println((i + 1) + ". " + students.get(i).name + " - Level: " + students.get(i).level);
    }
    int studentChoice = scanner.nextInt();
    scanner.nextLine(); 
    if (studentChoice < 1 || studentChoice > students.size()) {
        System.out.println("Invalid choice. Returning to main menu.");
        return;
    }
    SwimStudent student = students.get(studentChoice - 1);

    System.out.println("Available qualifications to award:");
    
    List<String> qualifications = Arrays.asList("5m", "10m", "20m", "50m", "100m", "200m", "400m", "800m", "Personal Survival Bronze", "Personal Survival Silver", "Personal Survival Gold");
    for (int i = 0; i < qualifications.size(); i++) {
        System.out.println((i + 1) + ". " + qualifications.get(i));
    }
    int qualChoice = scanner.nextInt();
    scanner.nextLine(); 
    if (qualChoice < 1 || qualChoice > qualifications.size()) {
        System.out.println("Invalid choice. Returning to main menu.");
        return;
    }
    String qualAwarded = qualifications.get(qualChoice - 1);
    Qualification qualification = new Qualification(qualAwarded.contains("Survival") ? "Personal Survival" : "Distance Swim", qualAwarded, instructor);

    student.addQualification(qualification);
    System.out.println("Awarded " + qualAwarded + " to " + student.name + " by " + instructor.name);

   
    if ("20m".equals(qualAwarded) && "Novice".equals(student.level)) {
        student.level = "Improver";
        System.out.println(student.name + " has been upgraded to Improver level.");
    } else if ("400m".equals(qualAwarded) && "Improver".equals(student.level)) {
        student.level = "Advanced";
        System.out.println(student.name + " has been upgraded to Advanced level.");
    }
}


private static void initializeInstructors() {
  
    instructors.add(new Instructor("Mr. alex"));
    instructors.add(new Instructor("Ms. boby"));
    
}




private static void moveSwimStudentFromWaitingList() {
   
    List<SwimStudent> waitingList = students.stream()
                                            .filter(student -> student.lesson == null)
                                            .collect(Collectors.toList());
    
    if (waitingList.isEmpty()) {
        System.out.println("There are no students on the waiting list.");
        return;
    }

    System.out.println("Students on the waiting list:");
    for (int i = 0; i < waitingList.size(); i++) {
        System.out.println((i + 1) + ". " + waitingList.get(i).name);
    }

    System.out.print("Select a student to move (enter number): ");
    int choice = scanner.nextInt();
    scanner.nextLine(); 

    if (choice < 1 || choice > waitingList.size()) {
        System.out.println("Invalid selection.");
        return;
    }

    SwimStudent selectedStudent = waitingList.get(choice - 1);

   
    System.out.println("Available Novice Classes:");
    for (SwimLesson lesson : students.stream()
                                     .filter(s -> s.lesson != null && s.level.equals("Novice"))
                                     .map(s -> s.lesson)
                                     .distinct()
                                     .collect(Collectors.toList())) {
        long count = students.stream().filter(s -> s.lesson == lesson).count();
        if (count < 4) { 
            System.out.println("- Day: " + lesson.day + ", Time: " + lesson.time + ", Instructor: " + lesson.instructor.name + ", Open Slots: " + (4 - count));
        }
    }

 
    System.out.println("Enter the day and time of the class to add the student to (e.g., Monday 17:00), or type 'none' to cancel:");
    String classSelection = scanner.nextLine();

    if ("none".equalsIgnoreCase(classSelection)) {
        System.out.println("Operation cancelled.");
        return;
    }

    boolean classFound = false;
    for (SwimStudent student : students) {
        if (student.lesson != null && (student.lesson.day + " " + student.lesson.time).equalsIgnoreCase(classSelection) && student.level.equals("Novice")) {
            long enrolledCount = students.stream().filter(s -> s.lesson == student.lesson).count();
            if (enrolledCount < 4) { 
                selectedStudent.lesson = student.lesson; 
                selectedStudent.level = "Novice";
                classFound = true;
                System.out.println(selectedStudent.name + " has been moved to the Novice class on " + classSelection);
                break;
            }
        }
    }

    if (!classFound) {
        System.out.println("No available class found or invalid selection.");
    }
}

}

class SwimStudent {
    String name;
    String level;
    SwimLesson lesson;
    List<Qualification> qualifications;
    
    public SwimStudent(String name, String level) {
        this.name = name;
        this.level = level;
        this.qualifications = new ArrayList<>();
    }
    public void addQualification(Qualification qualification) {
        qualifications.add(qualification);
    }
}
class SwimLesson {
    String day;
    String time;
    Instructor instructor;

    public SwimLesson(String day, String time, Instructor instructor) {
        this.day = day;
        this.time = time;
        this.instructor = instructor;
    }
}

class Instructor {
    String name;
    
    public Instructor(String name) {
        this.name = name;
    }
}

class Qualification {
    String type;
    String level;
    Instructor awardedBy;

    public Qualification(String type, String level, Instructor awardedBy) {
        this.type = type;
        this.level = level;
        this.awardedBy = awardedBy;
    }
}