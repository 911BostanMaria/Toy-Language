package View;

import Controller.Controller;
import Model.Fish;
import Model.Turtles;
import Model.iAquarium;
import Repository.Repository;
import Repository.iRepository;
import Repository.RepositoryException;

import java.util.Scanner;

public class View {

    private final iRepository repo;
    private final Controller controller;

    View() {
        this.repo = new Repository(50);
        this.controller = new Controller(repo);
    }

    public void add_entities() throws RepositoryException {
        this.controller.add("fish", 1);
        this.controller.add("turtle", 22);
        this.controller.add("fish", 23);
        this.controller.add("turtle", 14);
        this.controller.add("fish", 5);
        this.controller.add("turtle", 96);
        this.controller.add("fish", 37);
        this.controller.add("turtle", 0);
    }

    public void add() {
        System.out.println("You can add to the aquarium: fish or turtle.");
        Scanner s = new Scanner(System.in);
        String type = s.nextLine().toLowerCase();
        System.out.println("Enter it's age.");
        String age = s.nextLine().toString();
        int age2 = Integer.parseInt(age);
        try {
            this.controller.add(type, age2);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {
        System.out.println("You delete from the aquarium: fish or turtle.");
        Scanner s = new Scanner(System.in);
        String type = s.nextLine().toLowerCase();
        System.out.println("Enter it's age.");
        String age = s.nextLine().toString();
        int age2 = Integer.parseInt(age);
        try {
            this.controller.delete(type, age2);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void show_all() {
        iAquarium[] data = this.controller.get_all();
        int i=-1;
        while(data[++i]!=null)
            System.out.println(data[i].toString());
    }

    public void printMenu(){
        System.out.println("1 for adding to the aquarium.");
        System.out.println("2 for deleting from the aquarium.");
        System.out.println("3 for showing animals older than one.");
        System.out.println("4 for showing all.");
        System.out.println("0 to exit the program.");
    }

    public void start(){
        Scanner s = new Scanner(System.in);
        this.printMenu();
        int option = s.nextInt();
        while (true){
            System.out.println("=====================");
            if(option == 1)
                this.add();
            else if(option == 2)
                this.delete();
            else if(option == 3)
                this.controller.print_solution(1);
            else if(option == 4)
                this.show_all();
            else if(option == 0)
                return;
            else
                System.out.println("Invalid command. Try again =)");
            System.out.println("=====================");
            this.printMenu();
            option = s.nextInt();
        }
    }

    public static void main(String[] args) throws RepositoryException {
        View v = new View();
        v.add_entities();
        v.start();

    }
}
