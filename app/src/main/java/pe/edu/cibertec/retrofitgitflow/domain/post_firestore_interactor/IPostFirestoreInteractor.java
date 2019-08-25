package pe.edu.cibertec.retrofitgitflow.domain.post_firestore_interactor;

import com.google.firebase.firestore.Query;

public interface IPostFirestoreInteractor {
    Query getAllPosts();
}
