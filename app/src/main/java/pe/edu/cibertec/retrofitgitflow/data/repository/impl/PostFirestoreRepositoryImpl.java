package pe.edu.cibertec.retrofitgitflow.data.repository.impl;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostFirestoreRepository;
import pe.edu.cibertec.retrofitgitflow.presentation.utils.FirestoreConstants;

public class PostFirestoreRepositoryImpl implements IPostFirestoreRepository {

    private final FirebaseFirestore firestore;

    @Inject
    public PostFirestoreRepositoryImpl(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Query getAllPosts() {
        return firestore.collection(FirestoreConstants.COLLECTION_POSTS);
    }

    @Override
    public void createPost(NewPost post, OnCompleteListener<DocumentReference> onCompleteListener) {
        firestore.collection(FirestoreConstants.COLLECTION_POSTS)
                .add(post).addOnCompleteListener(onCompleteListener);
    }
}
