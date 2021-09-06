package com.rubikkube.composemovie.data.remote

data class DataSource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> empty(): DataSource<T> {
            return DataSource(Status.EMPTY, null, null)
        }

        fun <T> success(data: T?): DataSource<T> {
            return DataSource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): DataSource<T> {
            return DataSource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): DataSource<T> {
            return DataSource(Status.LOADING, data, null)
        }
    }
}