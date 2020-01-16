package com.angelsit.kudagofeed.presenter

import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.data.dto.City
import com.angelsit.kudagofeed.data.sharedpreference.SharedPreferenceManager
import com.angelsit.kudagofeed.repo.EventsRepo
import com.angelsit.kudagofeed.view.FeedActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun Disposable.addTo(disposables: CompositeDisposable) = disposables.add(this)

class FeedPresenter(private val mView: FeedActivity) : MainContract.Presenter.GetEventsListener {

    private val disposables = CompositeDisposable()


    override fun onGetEventsFinish(result: List<EventsRepo.EventPreviewEntity>) {
        mView.showEvents(result)
    }

    override fun onGetEventsFailed(t: Throwable) {
        //To change body of created functions use File | Settings | File Templates.
    }

    fun onCitySelected(city: City) {
        mView.showLoading()
        SharedPreferenceManager.saveSelectedCity(mView, city)
        mView.updateSelectedCity(city)
        getEvents()
    }

    private fun getEvents(city: String = "msk") {
        EventsRepo.getEvents(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onGetEventsFinish(it)
                },
                {
                    onGetEventsFailed(it)
                }
            ).addTo(disposables)
    }

    fun onResume() {
        mView.showLoading()
        getEvents()
    }

    fun onUpdate() {
        getEvents()
    }

    fun onCreate() {
        val selectedCity = SharedPreferenceManager.getSelectedCity(mView)
        mView.initSelectedCity(selectedCity)
    }

    fun onDestroy() {
        disposables.clear()
    }
}