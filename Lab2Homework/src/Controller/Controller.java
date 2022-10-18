package Controller;
import Model.Fish;
import Model.Turtles;
import Repository.RepositoryException;
import Repository.iRepository;
import Model.iAquarium;

public class Controller {

    private iRepository repo;

    public Controller(iRepository repo){
        this.repo = repo;
    }

    public void add(String type, int age) throws RepositoryException {
        switch (type) {
            case "fish" -> {
                iAquarium a = new Fish(age);
                this.repo.add(a);
            }
            case "turtle" -> {
                iAquarium a = new Turtles(age);
                this.repo.add(a);
            }
        }
    }


    public void delete(String type, int age) throws RepositoryException {
        switch (type) {
            case "fish" -> {
                iAquarium a = new Fish(age);
                this.repo.delete(a);
            }
            case "turtle" -> {
                iAquarium a = new Turtles(age);
                this.repo.delete(a);
            }
        }
    }
    public void print_solution(int age){
        iAquarium[] data = this.repo.get_all();
        int i=-1;
        while(data[++i]!=null)
            if(data[i].older_than_one(age)) {
                System.out.print(data[i].toString());
                System.out.println("\n");
            }
    }

    public iAquarium[] get_all(){
        iAquarium [] a = this.repo.get_all();
        return a;
    }
}
