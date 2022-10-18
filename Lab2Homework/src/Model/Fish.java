package Model;


public class Fish implements iAquarium {

    private int age;

    public Fish(int age) {
        this.age = age;
    }

    public String toString(){
        return "Fish age: " + this.age;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Fish f)) {
            return false;
        }
        return f.age==this.age;
    }

    @Override
    public Boolean older_than_one(int age) {
        return this.age > 1;
    }
}
