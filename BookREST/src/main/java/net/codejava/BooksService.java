package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BooksService {
    @Autowired
    private BooksRepository repo;

    public List<Books> listAll(){
        return repo.findAll();
    }

    public void save(Books books){
        repo.save(books);
    }

    public Books get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
}
