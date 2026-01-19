package com.enterprise.koinktor.network.exception

import android.content.Context
import com.enterprise.koinktor.R
import java.io.IOException


class NoInternetConnectionException(context: Context): IOException() {

    override val message: String? = context.getString(R.string.no_internet_connection_exception_message)

}