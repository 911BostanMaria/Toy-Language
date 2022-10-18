package Repository;
import Model.iAquarium;
import Repository.RepositoryException;

public class Repository implements iRepository {

    public iAquarium aquarium[];
    private int current_size;

    public Repository(int max_size){
        this.current_size = 0;
        aquarium = new iAquarium[max_size];
    }

    @Override
    public void add(iAquarium a) throws RepositoryException{
        if(this.current_size == this.aquarium.length)
            throw new RepositoryException("Aquarium is full. Cannot add!\n");
        this.aquarium[current_size] = a;
        ++current_size;
    }

    @Override
    public void delete(iAquarium a) throws RepositoryException {
        if(this.current_size == 0)
            throw new RepositoryException("Aquarium is empty. Cannot delete!\n");
        int i=-1;
        while(this.aquarium[++i]!=null) {
            if (this.aquarium[i].equals(a)) {
                int j = i;
                while (this.aquarium[j] != null) {
                    this.aquarium[j] = this.aquarium[j + 1];
                    ++j;
                }
            }
        }
        this.current_size--;
    }

    @Override
    public iAquarium[] get_all(){
        return this.aquarium;
    }
}
