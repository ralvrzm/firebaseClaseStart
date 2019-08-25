package pe.edu.cibertec.retrofitgitflow.domain.post_firestore_interactor;

import com.google.firebase.firestore.Query;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.repository.IPostFirestoreRepository;

public class postFirestoreInteractorImpl implements IPostFirestoreInteractor {

    private final IPostFirestoreRepository repository;

    @Inject
    public postFirestoreInteractorImpl(IPostFirestoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Query getAllPosts() {
        return repository.getAllPosts();
    }
}
