package com.example.androidlibrary

import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.model.user.GithubUsersRepoImpl
import com.example.androidlibrary.mvp.model.user.IGitHubUsersRepo
import com.example.androidlibrary.mvp.model.user.IRoomGitHubUsersCache
import com.example.androidlibrary.mvp.model.user.RoomGithubUsersCacheImpl
import com.example.androidlibrary.mvp.network.INetworkStatus
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GitHubUserRepoTest {

    @Mock
    lateinit var repoUser: IGitHubUsersRepo

    @Mock
    lateinit var api: RetrofitAPI
    @Mock
    lateinit var networkStatus: INetworkStatus
    @Mock
    lateinit var db: IRoomGitHubUsersCache

    @Mock
    lateinit var singleUserList: Single<List<GithubUser>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getUserListSucceedsCall() {
        Mockito.doReturn(Single.just(Mockito.mock(GithubUser::class.java))).`when`(
                repoUser
        ).getUsersList()

        val testObserver: @NonNull TestObserver<List<GithubUser>>? =
                repoUser.getUsersList()
                .subscribeOn(TestScheduler())
                .observeOn(TestScheduler())
                .test()
        verify(repoUser, atLeastOnce()).getUsersList()
        testObserver?.dispose()
    }
}