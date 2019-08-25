package pe.edu.cibertec.retrofitgitflow.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostRepository;
import pe.edu.cibertec.retrofitgitflow.di.scope.PerActivity;
import pe.edu.cibertec.retrofitgitflow.domain.main_interactor.IMainInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.main_interactor.MainInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.IPostDetailInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.PostDetailInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;
import retrofit2.Retrofit;

@Module
public class PresentationModule {

    @PerActivity
    @Provides
    //IMainInteractor provideMainInteractor(JsonPlaceHolderApi jsonPlaceHolderApi,
    IMainInteractor provideMainInteractor(IPostRepository iPostRepository,
                                          @Named("ui_thread") Scheduler uiThread,
                                          @Named("executor_thread") Scheduler executorThread){
        //return new MainInteractorImpl(jsonPlaceHolderApi, uiThread, executorThread);
        return new MainInteractorImpl(iPostRepository, uiThread, executorThread);
    }

    @PerActivity
    @Provides
    IPostDetailInteractor providePostDetailInteractor(IPostRepository iPostRepository,
                                                      @Named("ui_thread") Scheduler uiThread,
                                                      @Named("executor_thread") Scheduler executorThread){
        return new PostDetailInteractorImpl(iPostRepository,uiThread,executorThread);
    }
}
