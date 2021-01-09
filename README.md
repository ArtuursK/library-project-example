# 12-Practical-Project

Welcome to the **Practical Project**!

Now is the time to put into practice everything you've learned to far.

This is a summary of everything you have learned up to this point and,
if it will be done well enough, it could be your first publicly available project
that you will share with potential employers.


## Requirements

Before you start make sure you do a quick recap of the basic concepts learned during:
- Java Fundamentals & Advanced: how to model java classes and create instances; how to work with collections; how to use loops;
- Software Testing: how to apply basic assertions when testing a method
- SQL: how to model a database; how to map the different relationships between tables (one-to-many, many-to-many)
- Hibernate: how to map a database to hibernate entities; how to configure the Hibernate SessionFactory; how to execute the basic CRUD operations with the Hibernate Session
- Java FX: how to design a basic interface


## Methodology

- groups of 2 people(pair programming/individual taks) or individual projects are acceptable as well,
- start with an architecture of the solution,
- implementation of each layer (database, Hibernate, JavaFX),
- project stored in a remote Git repository (Github, Bitbucket, GitLab),
- code reviews,
- README.md file with a detailed instruction about the project (what is it about, 
installation, configuration, launching..)
- presentation of the solution after the project.


## Application

- **Book management system**: Java Desktop Application used for managing a library

1.  One window for CRUD operations on Author (firstname, lastname): allows the viewing of all the authors; adding a new author; deleting an existing author and updating an existing author
2.  One window for CRUD operations on Book (title, description, author): allows the viewing of all the books; adding a new book - and assigning one of the existing authors; deleting an existing book and updating an existing book
3.  One window for Reviews (book, score, comment): allows the viewing of all the reviews; adding a new review for one of the existing books

