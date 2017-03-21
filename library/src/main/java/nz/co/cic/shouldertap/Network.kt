package nz.co.cic.shouldertap

import android.content.Context

class Network(private val mContext: Context){

    private var relay: Relay? = null
    private var client: Client? = null

    init {
    }

    fun startRelay(){
        this.relay = Relay(mContext)
        this.relay!!.start()
    }

    fun startClient(){
        this.client = Client(mContext)
        this.client!!.start()
    }
}
