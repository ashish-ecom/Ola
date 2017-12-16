package com.olacabs.play.ui.splash;

import android.util.Log;

import com.olacabs.play.repo.IDataManager;
import com.olacabs.play.ui.base.BaseViewModel;
import com.olacabs.play.utils.Constants;
import com.olacabs.play.utils.rx.ISchedulerProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Generated by Dipendra on 16/12/17
 */


public class SplashViewModel extends BaseViewModel<ISplashNavigator> {

    public SplashViewModel(IDataManager dataManager,
                           ISchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void waitOnScreen() {
        if (getDataManager().isMusicDownloaded()) {
            getCompositeDisposable().add(Observable.interval(1, TimeUnit.SECONDS)
                    .take(Constants.TIMER_VALUE)
                    .map(v -> Constants.TIMER_VALUE - v)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(onNext -> {
                            },
                            onError -> {
                            },
                            this::decideNextActivity,
                            onSubscribe -> {
                            }));
        } else {
            getCompositeDisposable().add(getDataManager().getMusicList()
                    .subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().io())
                    .subscribe(musicList -> {
                                if (getDataManager().saveMusicList(musicList)) {
                                    getDataManager().setMusicDownloaded(true);
                                }
                            }, throwable ->
                                    Log.e("api", throwable.getMessage())
                    )
            );
        }
    }

    private void decideNextActivity() {
        getNavigator().openMainActivity();
    }

}