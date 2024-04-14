# DEF University Management System
From now on, we generate Class Diagram from code (IdeaJ) (because update class manually is time consuming).

When finish project, we draw class diagram generated from IdeaJ to LucidChart for prettier image, and explain each pattern for each part of classes.

After that, we explain what we do in slides.

Use Repository to integrate with Firebase (pass Class to them).

...


## Some Example Crud code:
- Create: test/java/.../StudentFireStoreTest.java

  https://firebase.google.com/docs/firestore/manage-data/add-data
- Read: account/Manager.java/login()

  https://firebase.google.com/docs/firestore/query-data/get-data
- Update: test/java/.../UpdateStudentTest.java

  https://firebase.google.com/docs/firestore/manage-data/add-data#update-data
- Delete: account/EducationalManager.java/deleteStudent()
  
  https://firebase.google.com/docs/firestore/manage-data/delete-data

## Work list:
- [ ] Refactor code firebase by Repository pattern (Doing for Scheduled class)
- [x] Connected to Firebase and Create first Student.
- [x] Course Create
- [x] Course Register
- [x] Class update
- [x] Create user by Factory Method pattern.
- [ ] Management course for Lecturer (implementing)
- [x] Course listener by Student.
- [x] Login feature by Singleton Pattern (not CLass diagram yet.)
- [ ] Complete Logic for 3 actor (Student, Lecturer, )
- [ ] Report
- [ ] Slide

## Class diagram : 
- https://lucid.app/lucidchart/e83a0b19-4a6e-48b1-b12f-4b665473e822/edit?invitationId=inv_1ec96244-71c6-4b27-89ec-7805fad875b6&fbclid=IwAR0x_2mFPRu5GPn9iVG93J9PoTp9vCGbsPZtW5cU-xWoNSZtB_SfPa0NRfs&page=0_0#

## Report: 
https://www.overleaf.com/8513712129rtnzjfrzdvpz#77ce3b

## Slide:
https://www.overleaf.com/4293843114kkppgjxvmhbd#bdf0f1

 
