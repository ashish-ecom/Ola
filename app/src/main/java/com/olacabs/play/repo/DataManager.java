package com.olacabs.play.repo;

import android.content.Context;

import com.olacabs.play.repo.api.IRestApiHelper;
import com.olacabs.play.repo.db.IDBHelper;
import com.olacabs.play.repo.dir.IFileHelper;
import com.olacabs.play.repo.model.Music;
import com.olacabs.play.repo.pref.IPreferenceHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Generated by Dipendra on 16/12/17
 */

@Singleton
public class DataManager implements IDataManager {

    private final Context mContext;
    private final IDBHelper mDbHelper;
    private final IPreferenceHelper mPreferencesHelper;
    private final IRestApiHelper mApiHelper;
    private final IFileHelper mFileHelper;

    @Inject
    public DataManager(Context mContext,
                       IDBHelper mDbHelper,
                       IPreferenceHelper mPreferencesHelper,
                       IRestApiHelper mApiHelper,
                       IFileHelper mFileHelper) {
        this.mContext = mContext;
        this.mDbHelper = mDbHelper;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mApiHelper = mApiHelper;
        this.mFileHelper = mFileHelper;
    }


    @Override
    public boolean isMusicDownloaded() {
        return mPreferencesHelper.isMusicDownloaded();
    }

    @Override
    public void setMusicDownloaded(boolean isMusicDownloaded) {
        mPreferencesHelper.setMusicDownloaded(isMusicDownloaded);
    }

    @Override
    public Observable<List<Music>> getMusicList() {
        return mApiHelper.getMusicList();
    }

    @Override
    public Observable<List<Music>> getAllMusic() {
        return mDbHelper.getAllMusic();
    }

    @Override
    public Observable<Boolean> isMusicEmpty() {
        return mDbHelper.isMusicEmpty();
    }

    @Override
    public boolean saveMusic(Music music) {
        return mDbHelper.saveMusic(music);
    }

    @Override
    public boolean saveMusicList(List<Music> musicList) {
        return mDbHelper.saveMusicList(musicList);
    }
}
