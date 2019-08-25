package pe.edu.cibertec.retrofitgitflow.domain.create_post_interactor;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentReference;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;

public interface ICreatePostInteractor {
    void createPost(NewPost post, OnCompleteListener<DocumentReference> onCompleteListener);
}
