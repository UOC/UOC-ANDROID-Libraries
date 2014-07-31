package edu.uoc.openapi.uoclogin.controllers.modules;

import android.accounts.AccountManager;
import android.content.Context;
import com.path.android.jobqueue.JobManager;
import dagger.Module;
import dagger.Provides;
import edu.uoc.openapi.uoclogin.controllers.ConstantsInterface;
import edu.uoc.openapi.uoclogin.interactors.interfaces.ServerAuthenticator;
import edu.uoc.openapi.uoclogin.interactors.UocServerAuthenticator;
import edu.uoc.openapi.uoclogin.views.AbstractLoginActivity;
import javax.inject.Singleton;

@Module(
    injects = {
        AbstractLoginActivity.class,
    })

public class ServerAuthenticateModule {

    public Context context;

    public ServerAuthenticateModule(Context context) {
        this.context = context;
    }

    @Singleton @Provides public ServerAuthenticator provideServerAuthenticate(JobManager jobManager,
        ConstantsInterface constants, AccountManager accountManager) {
        return new UocServerAuthenticator(context, accountManager, jobManager, constants);
    }

    @Singleton @Provides public JobManager provideJobManager(Context context) {
        return new JobManager(context);
    }

    @Singleton @Provides public Context providesContext() {
        return context;
    }

    @Singleton @Provides public ConstantsInterface providesAbstractConstants() {
        return UocServerAuthenticator.constants;
    }

    @Singleton @Provides public AccountManager providesAccountManager(Context context) {
        return AccountManager.get(context);
    }
}
