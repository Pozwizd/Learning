package org.example.Java._1_GoFPatterns.Behavioral.Command;

interface Command2 {
    void execute();
}

class Database {
    void insert() {
        System.out.println("Inserting record...");
    }

    void update() {
        System.out.println("update record...");
    }

    void select() {
        System.out.println("select record...");
    }

    void delete() {
        System.out.println("delete record...");
    }

}

class InsertCommand implements Command2 {
    Database database;

    public InsertCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.insert();
    }
}

class UpdateCommand implements Command2 {
    Database database;

    public UpdateCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.update();
    }
}

class SelectCommand implements Command2 {
    Database database;

    public SelectCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.select();
    }
}

class DeleteCommand implements Command2 {
    Database database;

    public DeleteCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.delete();
    }
}

class Developer {
    Command2 insert;
    Command2 update;
    Command2 select;
    Command2 delete;

    public Developer(Command2 insert, Command2 update, Command2 select, Command2 delete) {
        this.insert = insert;
        this.update = update;
        this.select = select;
        this.delete = delete;
    }

    void insertRecord() {
        insert.execute();
    }

    void updateRecord() {
        update.execute();
    }

    void selectRecord() {
        select.execute();
    }

    void deleteRecord() {
        delete.execute();
    }


}

public class Example {
    public static void main(String[] args) {
        Database database = new Database();
        Developer developer = new Developer(
                new InsertCommand(database),
                new UpdateCommand(database),
                new SelectCommand(database),
                new DeleteCommand(database)
        );
        developer.insertRecord();
        developer.updateRecord();
        developer.selectRecord();
        developer.deleteRecord();
    }
}
