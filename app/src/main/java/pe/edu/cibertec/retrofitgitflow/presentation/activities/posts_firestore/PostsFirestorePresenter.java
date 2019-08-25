package pe.edu.cibertec.retrofitgitflow.presentation.activities.posts_firestore;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;
import pe.edu.cibertec.retrofitgitflow.domain.post_firestore_interactor.IPostFirestoreInteractor;


public class PostsFirestorePresenter implements IPostsFirestoreContract.IPresenter {

    private IPostsFirestoreContract.IView view;
    private ListenerRegistration registration;
    private List<NewPost> postList;
    int index;

    @Inject
    protected IPostFirestoreInteractor interactor;

    @Inject
    public PostsFirestorePresenter() {
        postList = new ArrayList<>();
    }

    @Override
    public void attachView(IPostsFirestoreContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(registration != null){
            registration.remove();
        }
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getAllPost() {
        if(isViewAttached()) view.showProgressBar();
        registration = interactor.getAllPosts()
                .addSnapshotListener(((snapshots, e) -> {
                    view.hideProgressBar();
                    // validamos el error
                    if (e != null) {
                        // ocurrio un error
                        if (isViewAttached()) view.showError(e.getMessage());
                        return;
                    }
                    // validamos que haya data
                    if (snapshots.size() == 0) {
                        view.showNoPost();
                    } else {
                        view.hideNoPost();
                    }
                    // recorremos los snapshots - sirve para mostrar en real time los comentarios que se realizan en la BD
                    for (DocumentChange doc: snapshots.getDocumentChanges()){
                        NewPost post = doc.getDocument().toObject(NewPost.class);
                        post.setId(doc.getDocument().getId());
                        switch (doc.getType()) {
                            case ADDED:
                                postList.add(post);
                                if (isViewAttached()) view.refreshRecyclerView(postList);
                                break;
                            case MODIFIED:
                                index = getIndexPostList(post);
                                if (index > -1 && isViewAttached()){
                                    postList.set(index, post);
                                    view.refreshRecyclerView(postList);
                                }
                                break;
                            case REMOVED:
                                index = getIndexPostList(post);
                                if (index > -1 && isViewAttached()){
                                    postList.remove(index);
                                    view.refreshRecyclerView(postList);
                                }
                                break;
                        }
                    }


                }));
    }

    private  int getIndexPostList(NewPost post) {
        int index = 0;
        for (NewPost p:postList) {
            if (p.getId() == post.getId()) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
