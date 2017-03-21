package nz.co.cic.shouldertap

import android.content.Context
import nz.co.cic.ble.beacon.Beacon
import nz.co.cic.ble.beacon.BeaconManager
import nz.co.cic.wifi.Wifi


class Relay(private val mContext: Context){

    private var wifi: Wifi? = null
    private var manager: BeaconManager? = null

    init{
        this.wifi = Wifi(mContext)
        this.manager = BeaconManager(mContext)
    }

    fun start(){
        this.wifi?.station?.start()?.subscribe({
            stationInfo ->
            var rNetwork = RelayNetwork(stationInfo)
            println(rNetwork.ssid + " " + rNetwork.pass)
            var beacon = Beacon("Shouldertap-Gateway", rNetwork.toMap())
            this.manager?.start()?.subscribe({
                status ->
                if(status){
                    this.manager?.addBeacon(beacon)
                }else{
                    println("Failed to start advertising")
                }
            })
        })
    }

    fun stop(){
        this.manager?.stop()
        this.wifi?.station?.stop()?.subscribe({
            sub ->
        })

    }
}
