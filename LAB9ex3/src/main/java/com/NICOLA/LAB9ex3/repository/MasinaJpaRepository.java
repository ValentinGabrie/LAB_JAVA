package com.NICOLA.LAB9ex3.repository;
import java.util.List;
import com.NICOLA.LAB9ex3.entity.Masina;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MasinaJpaRepository {
@PersistenceContext
EntityManager entityManager;

    public List<Masina> findAll(){
        TypedQuery<Masina> query=entityManager.createQuery("from Masina",Masina.class);
        return query.getResultList();
    }
    public Masina findById(String id){
        return entityManager.find(Masina.class, id);
    }
    public void deleteById(String id){
        Masina masini=findById(id);
        entityManager.remove(masini);
    }
    public Masina insert(Masina masini){
        return entityManager.merge(masini);
    }
    public Masina update(Masina masini){return entityManager.merge(masini);
    }



}
