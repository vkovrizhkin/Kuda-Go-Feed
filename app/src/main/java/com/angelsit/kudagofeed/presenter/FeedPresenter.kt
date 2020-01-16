package com.angelsit.kudagofeed.presenter

import android.content.ContentValues
import com.angelsit.kudagofeed.MainContract
import com.angelsit.kudagofeed.data.api.Api
import com.angelsit.kudagofeed.data.contentprovider.EventsProvider
import com.angelsit.kudagofeed.data.dto.City
import com.angelsit.kudagofeed.data.dto.event.Event
import com.angelsit.kudagofeed.data.sharedpreference.SharedPreferenceManager
import com.angelsit.kudagofeed.repo.EventsRepo
import com.angelsit.kudagofeed.view.FeedActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun Disposable.addTo(disposables: CompositeDisposable) = disposables.add(this)

class FeedPresenter(private val mView: FeedActivity) : MainContract.Presenter.GetEventsListener {

    private val disposables = CompositeDisposable()
    override fun onGetEventsFinish(result: List<Event>) {
        mView.showEvents(result)
        val event = result[0]

        Completable.fromCallable {
            EventsRepo.getDataFromPersist(mView)
            /*val values = ContentValues()
            values.put(EventsProvider.id, event.id)
            values.put(EventsProvider.title, event.title)
            values.put(EventsProvider.description, event.description)
            values.put(EventsProvider.avatar, event.images?.get(0)?.image ?: "")
            mView.contentResolver.insert(EventsProvider.CONTENT_URI, values)*/
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val i = "успех"
                },
                {
                    val j = it
                }
            ).addTo(disposables)


    }

    override fun onGetEventsFailed(t: Throwable) {
        //To change body of created functions use File | Settings | File Templates.
    }

    fun onCitySelected(city: City) {
        mView.showLoading()
        SharedPreferenceManager.saveSelectedCity(mView, city)
        mView.updateSelectedCity(city)
        EventsRepo.getEvents(city.slug, this)
    }

    fun onResume() {
        mView.showLoading()
        EventsRepo.getEvents(mView.selectedCity!!.slug, this)
    }

    fun onUpdate() {
        EventsRepo.getEvents(mView.selectedCity!!.slug, this)
    }

    fun onCreate() {
        val selectedCity = SharedPreferenceManager.getSelectedCity(mView)
        mView.initSelectedCity(selectedCity)
    }
}