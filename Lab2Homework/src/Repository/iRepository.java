package Repository;
import Model.iAquarium;

public interface iRepository {

    public void add(iAquarium a) throws RepositoryException;

    public void delete(iAquarium a) throws RepositoryException;

    public iAquarium[] get_all();


}
