package Model;

public class Turtles implements iAquarium {

    private int age;

    public Turtles(int age) {
        this.age = age;
    }

    public String toString(){
        return "Turtle age: " + this.age;
    }

    public boolean equals(Object o){
        if (!(o instanceof Turtles t)) {
            return false;
        }
        return t.age==this.age;
    }


    @Override
    public Boolean older_than_one(int age) {
        return this.age > 1;
    }
}
